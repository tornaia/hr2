package hu.interconnect.hr.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.WebUtils;

import com.google.common.collect.Lists;

@Configuration
@EnableWebSecurity
public class HRWebAppSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	private static final Log LOGGER = LogFactory.getLog(HRWebAppSecurityConfigurer.class);

	@Value("${allowcorsfilter}")
	private boolean allowcorsfilter;
	
	@Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
	
	@Autowired
	public void configureContentNegotiatingViewResolver(ContentNegotiatingViewResolver contentNegotiatingViewResolver, InternalResourceViewResolver jspViewResolver) {
		contentNegotiatingViewResolver.setViewResolvers(Lists.newArrayList(jspViewResolver));
	}
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/").permitAll()
		.and()
		.formLogin().loginPage("/").loginProcessingUrl("/loginprocess").successHandler(new UserAuthenticationSuccessHandlerWithHashbang())
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and()
		.authorizeRequests().antMatchers("/app/**/*").fullyAuthenticated()
		.and()
		.authorizeRequests().antMatchers("/api/**/*").fullyAuthenticated();
		
		if (allowcorsfilter) {
			LOGGER.warn("AllowCORSFilter enabled. Never use this in production!");
			http.addFilterBefore(new AllowCORSFilter(), CsrfFilter.class);
		}
		
		http.csrf().csrfTokenRepository(csrfTokenRepository());
		http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}
	
	private static CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	public static final class CsrfHeaderFilter extends OncePerRequestFilter {
		
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			if (csrf != null) {
				Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
				String token = csrf.getToken();
				if (cookie == null || token != null && !token.equals(cookie.getValue())) {
					cookie = new Cookie("XSRF-TOKEN", token);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			filterChain.doFilter(request, response);
		}
	}
	
	public static final class AllowCORSFilter extends OncePerRequestFilter {

		@Override
		protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
			String origin = req.getHeader("origin");
			res.setHeader("Access-Control-Allow-Origin", origin);
			res.setHeader("Access-Control-Allow-Credentials", "true");
			chain.doFilter(req, res);
		}
	}
	
	public static final class UserAuthenticationSuccessHandlerWithHashbang extends SimpleUrlAuthenticationSuccessHandler {

		private static final String REDIRECT_TO_PARAMETER = "redirectTo";
		private static final String DEFAULT_REDIRECT_TO = "/app/index.html";
		
		protected final Log logger = LogFactory.getLog(this.getClass());

		private RequestCache requestCache = new HttpSessionRequestCache();

		public UserAuthenticationSuccessHandlerWithHashbang() {
			setTargetUrlParameter(REDIRECT_TO_PARAMETER);
			setDefaultTargetUrl(DEFAULT_REDIRECT_TO);
		}
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
			SavedRequest savedRequest = requestCache.getRequest(request, response);

			if (savedRequest == null || isAlwaysUseDefaultTargetUrl()) {
				superOnAuthenticationSuccess(request, response, authentication);
				return;
			}

			clearAuthenticationAttributes(request);

			// Use the DefaultSavedRequest URL
			String targetUrl = savedRequest.getRedirectUrl();

			String redirectTo = request.getParameter(getTargetUrlParameter());
			if (StringUtils.hasText(redirectTo)) {
				targetUrl = targetUrl + "#" + redirectTo;
			}

			getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}
		
		public void setRequestCache(RequestCache requestCache) {
			this.requestCache = requestCache;
		}
		
		private void superOnAuthenticationSuccess(HttpServletRequest request,
				HttpServletResponse response, Authentication authentication)
				throws ServletException, IOException {
			SavedRequest savedRequest = requestCache.getRequest(request, response);

			if (savedRequest == null) {
				super.onAuthenticationSuccess(request, response, authentication);

				return;
			}
			String targetUrlParameter = getTargetUrlParameter();
			if (isAlwaysUseDefaultTargetUrl()
					|| (targetUrlParameter != null && StringUtils.hasText(request
							.getParameter(targetUrlParameter)))) {
				requestCache.removeRequest(request, response);
				super.onAuthenticationSuccess(request, response, authentication);

				return;
			}

			clearAuthenticationAttributes(request);

			// Use the DefaultSavedRequest URL
			String targetUrl = savedRequest.getRedirectUrl();
			logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
			getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}
	}
}
