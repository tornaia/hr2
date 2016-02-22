package hu.interconnect.hr.security;

public final class AuthorityConstants {

	private static final String HAS_ROLE_PREFIX = " hasRole('";
	private static final String HAS_ROLE_POSTFIX = "') ";
	
	public static final String PERMIT_ALL = "permitAll()";
	public static final String ROLE_PREFIX = "ROLE_";
	public static final String ROLE_NEM_VEDETT = ROLE_PREFIX + "NEM_VEDETT";
	public static final String ROLE_DOLGOZO = ROLE_PREFIX + "DOLGOZO";
	public static final String ROLE_BETEKINTO = ROLE_PREFIX + "BETEKINTO";
	public static final String ROLE_ADMINISTRATOR = ROLE_PREFIX + "ADMINISTRATOR";
	
	public static final String HAS_ROLE_NEM_VEDETT = HAS_ROLE_PREFIX + ROLE_NEM_VEDETT + HAS_ROLE_POSTFIX;
	public static final String HAS_ROLE_DOLGOZO = HAS_ROLE_PREFIX + ROLE_DOLGOZO + HAS_ROLE_POSTFIX;
	public static final String HAS_ROLE_BETEKINTO = HAS_ROLE_PREFIX + ROLE_BETEKINTO + HAS_ROLE_POSTFIX;
	public static final String HAS_ROLE_ADMINISTRATOR = HAS_ROLE_PREFIX + ROLE_ADMINISTRATOR + HAS_ROLE_POSTFIX;
	
	public static final String PERSONAL_DATA_ID_EQUALS_TO = " authentication.principal.szemelyitorzs != null and authentication.principal.szemelyitorzs.tsz != null and authentication.principal.szemelyitorzs.tsz == ";
	public static final String OR = " or ";
	
	private AuthorityConstants() {
	}
}
