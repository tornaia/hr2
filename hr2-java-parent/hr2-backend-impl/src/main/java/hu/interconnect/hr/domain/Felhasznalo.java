package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.hr.backend.api.enumeration.Szerep.ADMINISTRATOR;
import static hu.interconnect.hr.backend.api.enumeration.Szerep.BETEKINTO;
import static hu.interconnect.hr.backend.api.enumeration.Szerep.DOLGOZO;
import static hu.interconnect.hr.backend.api.enumeration.Szerep.NEM_VEDETT;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.dto.FelhasznaloEditDTO;
import hu.interconnect.hr.backend.api.dto.ProfileEditDTO;
import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;
import hu.interconnect.hr.security.AuthorityConstants;

@Entity
@Table(name="FELHASZNALO")
public class Felhasznalo extends AbstractEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NEV")
	private String nev;

	@Column(name="JELSZO")
	private String jelszo;

	@Column(name="SZEREP")
	@Enumerated(EnumType.STRING)
	private Szerep szerep;

	@OneToOne
	@JoinColumn(name="SZEMELYITORZS")
	private Szemelyitorzs szemelyitorzs;

	@Column(name="ENABLED")
	private boolean enabled;

	@Column(name="LOCALE")
	@Enumerated(EnumType.STRING)
	private Locale locale;

	public Felhasznalo(String nev, Szerep szerep, String jelszo, Szemelyitorzs szemelyitorzs, boolean enabled, Locale locale) {
		checkArgument(isNotEmpty(nev));
		checkArgument(nonNull(szerep));
		checkArgument(isNotEmpty(jelszo));
		checkArgument(nonNull(locale));
		checkArgument(equalsAndNotNull(szerep, Szerep.NEM_VEDETT) && isNull(szemelyitorzs) || equalsAndNotNull(szerep, Szerep.DOLGOZO) && nonNull(szemelyitorzs) || ((equalsAndNotNull(szerep, Szerep.ADMINISTRATOR) || equalsAndNotNull(szerep, Szerep.BETEKINTO)) && isNull(szemelyitorzs)));
		
		this.nev = nev;
		this.jelszo = jelszo;
		this.szerep = szerep;
		this.szemelyitorzs = szemelyitorzs;
		this.enabled = enabled;
		this.locale = locale;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void merge(FelhasznaloEditDTO dto) {
		checkArgument(equalsAndNotNull(dto.getSzerep(), Szerep.DOLGOZO) && nonNull(dto.getTsz()) || ((equalsAndNotNull(dto.getSzerep(), Szerep.ADMINISTRATOR) || equalsAndNotNull(dto.getSzerep(), Szerep.BETEKINTO)) && isNull(dto.getTsz())));
		checkArgument(equalsAndNotNull(nev, dto.getNev()));

		szerep = dto.getSzerep();
		if (!isEmpty(dto.getJelszo())) {
			jelszo = dto.getJelszo();
		}
		szemelyitorzs = dto.getTsz() != null ? new Szemelyitorzs(dto.getTsz(), null, null, null, null, null, null) : null;
		enabled = dto.isEnabled();
		locale = dto.getLocale();
	}
	
	public void merge(ProfileEditDTO dto) {
		checkArgument(nonNull(dto.getLocale()));
		
		if (!isEmpty(dto.getJelszo())) {
			jelszo = dto.getJelszo();
		}
		locale = dto.getLocale();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		switch (szerep) {
		case NEM_VEDETT : return toGrantedAuthorities(NEM_VEDETT);
		case DOLGOZO : return toGrantedAuthorities(DOLGOZO, NEM_VEDETT);
		case BETEKINTO : return toGrantedAuthorities(BETEKINTO, NEM_VEDETT);
		case ADMINISTRATOR : return toGrantedAuthorities(ADMINISTRATOR, BETEKINTO, DOLGOZO, NEM_VEDETT);
		default : throw new ProgramozasiHiba("Nem lekezelt szerep! " + this);
		}
	}
	
	@Override
	public String getUsername() {
		return nev;
	}

	@Override
	public String getPassword() {
		return jelszo;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getNev() {
		return nev;
	}

	public boolean isAdministrator() {
		return getAuthorities().stream().anyMatch(p -> Objects.equals(AuthorityConstants.ROLE_ADMINISTRATOR, p.getAuthority()));
	}

	public String getJelszo() {
		return jelszo;
	}
	
	public Szerep getSzerep() {
		return szerep;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	private static Set<GrantedAuthority> toGrantedAuthorities(Szerep... szerepek) {
		return asList(szerepek).stream().map(sz -> new SimpleGrantedAuthority(AuthorityConstants.ROLE_PREFIX + sz)).collect(Collectors.toSet());
	}
	
	@SuppressWarnings("unused")
	private Felhasznalo() {
	}
}