package org.jasig.cas.authentication.constant;

public interface LdapConstant {

	public String USER_OBJECT_CLASS_FILTER = "(objectClass=person)";
	
	public String LDAP_BINARY = "java.naming.ldap.attributes.binary";
	
	public String OBJECT_GUID = "objectGUID";
	
	public String GROUP_OBJECT_CLASS_FILTER = "(objectClass=group)";
	
	public String FILTER_SAMACCOUNTNAME = "(&(objectClass=user)(sAMAccountName=%s))";
	
	public String SN = "sn";
	public String GIVENNAME = "givenName";
	public String DISPLAYNAME = "displayName";
	public String USERPRINCIPALNAME = "mail";
	public String OBJECTGUID = "objectGUID";
	public String SAMACCOUNTNAME = "sAMAccountName";
	public String DISTINGUISHEDNAME = "distinguishedName";
	public String CN = "cn";
	public String MEMBER_OF = "memberOf";
	public String[] ATTRIDSTOSEARCH = new String[] {
			CN,
			SN,GIVENNAME,DISPLAYNAME,USERPRINCIPALNAME,
			OBJECTGUID,SAMACCOUNTNAME,USERPRINCIPALNAME,
			DISTINGUISHEDNAME, MEMBER_OF };
	
}
