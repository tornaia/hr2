package hu.interconnect.hr.backend.api.enumeration;

public enum Locale {
	
	hu_HU("hu", "HU", new java.util.Locale("hu", "HU")),
	en_US("en", "US", new java.util.Locale("en", "US"));
	
	private String language;
	private String country;
	private java.util.Locale javaUtilLocale;
	
	Locale(String language, String country, java.util.Locale javaUtilLocale) {
		this.language = language;
		this.country = country;
		this.javaUtilLocale = javaUtilLocale;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getCountry() {
		return country;
	}

	public java.util.Locale getJavaUtilLocale() {
		return javaUtilLocale;
	}
}
