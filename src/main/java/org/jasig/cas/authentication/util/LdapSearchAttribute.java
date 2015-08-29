package org.jasig.cas.authentication.util;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import org.jasig.cas.authentication.constant.LdapConstant;
import org.jasig.cas.authentication.vo.LdapUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * search user attribute from ldap info
 * @author lewis
 *
 */
public class LdapSearchAttribute {

	private Logger logger = LoggerFactory.getLogger(LdapSearchAttribute.class);
	
	/**
	 * 分隔符
	 */
	private static String SPLITE_SYMBOL = "-";
	private static String HEX_0XF = "0";
	/**
	 * 读取applicationshiro.xml中的baseDN配置
	 */
	protected String baseDN;
	
	/**
	 * <p>获取Ldap上,认证用户的属性信息</p>
	 * @param baseDN
	 * @param ldapContext
	 * @throws NamingException
	 */
	public void filterLdapAttribute(String principal,LdapContext context,LdapUser userVo) throws NamingException{
		SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
		constraints.setReturningAttributes(LdapConstant.ATTRIDSTOSEARCH);
		String filter = LdapConstant.FILTER_SAMACCOUNTNAME;
		filter = String.format(filter, principal);
		
		NamingEnumeration<SearchResult> searchResult = context.search(baseDN.trim(), filter, constraints);
		if (searchResult != null) {
			while (searchResult.hasMore()) {
				Object result = searchResult.next();
				if (result instanceof SearchResult) {
					SearchResult rs = (SearchResult) result;
					copyLdapUserAttributeToUserVo(rs.getAttributes(), userVo,context);
				}
			}
		}
	}
	
	/**
	 * <p>拷贝ldap上的属性到自定义的UserVo中</p>
	 * @param baseDN
	 * @param ldapContext
	 * @throws NamingException
	 */
	private void copyLdapUserAttributeToUserVo(Attributes value,LdapUser userVo,DirContext ldapContext) throws NamingException{
		userVo.setDn((String) value.get(LdapConstant.DISTINGUISHEDNAME).get());
		Attribute givenName = value.get(LdapConstant.GIVENNAME);
		if(givenName!=null){
		    userVo.setGivenName((String) givenName.get());
		}
		Attribute cn = value.get(LdapConstant.DISPLAYNAME);
        if(cn!=null){
            userVo.setCn((String) cn.get());
        }
        Attribute sn = value.get(LdapConstant.SN);
        if(sn!=null){
            userVo.setSn((String) sn.get());
        }
        Attribute email = value.get(LdapConstant.USERPRINCIPALNAME);
        if(email!=null){
            userVo.setEmail((String) email.get());
        }
		try{
			byte[] objectGUID = (byte[])value.get(LdapConstant.OBJECTGUID).get();
			String stringGUID=convertToDashedString(objectGUID);
			if(logger.isDebugEnabled()){
				logger.debug("stringGUID:"+stringGUID);
			}
			userVo.setuId(stringGUID);
		}catch(NullPointerException e){
			logger.error(e.getMessage(), e);
		}
		userVo.setUserName((String) value.get(LdapConstant.SAMACCOUNTNAME).get());
		userVo.setEmail((String) value.get(LdapConstant.USERPRINCIPALNAME).get());
		provideSPI(userVo);
	}
	
	/**
	 * <p>把字节类型的guid转换为Stringe类型</p>
	 * @param objectGUID 字节类型的guid，即是用户在ldap的uid
	 * @return 返回String的uid
	 */
	private static String convertToDashedString(byte[] objectGUID) {
		StringBuilder displayStr = new StringBuilder();
		displayStr.append(prefixZeros((int) objectGUID[3] & 0xFF));
		displayStr.append(prefixZeros((int) objectGUID[2] & 0xFF));
		displayStr.append(prefixZeros((int) objectGUID[1] & 0xFF));
		displayStr.append(prefixZeros((int) objectGUID[0] & 0xFF));
		displayStr.append(SPLITE_SYMBOL);
		displayStr.append(prefixZeros((int) objectGUID[5] & 0xFF));
	    displayStr.append(prefixZeros((int) objectGUID[4] & 0xFF));
	    displayStr.append(SPLITE_SYMBOL);
	    displayStr.append(prefixZeros((int) objectGUID[7] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[6] & 0xFF));
        displayStr.append(SPLITE_SYMBOL);
        displayStr.append(prefixZeros((int) objectGUID[8] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[9] & 0xFF));
        displayStr.append(SPLITE_SYMBOL);
        displayStr.append(prefixZeros((int) objectGUID[10] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[11] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[12] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[13] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[14] & 0xFF));
        displayStr.append(prefixZeros((int) objectGUID[15] & 0xFF));
        return displayStr.toString();
	}
	
	/**
	 * <p>当前值如果小于16进制，则前面添加0</p>
	 * @param value 要添加或者转换int类型为String类型
	 * @return
	 */
	private static String prefixZeros(int value) {
		if (value <= 0xF) {
	        StringBuilder sb = new StringBuilder(HEX_0XF);
	        sb.append(Integer.toHexString(value));
	        return sb.toString();
	    }else {
	        return Integer.toHexString(value);
	    }
	}
	
	/**
	 * <h1>提供子类去实现，并添加所需要的数据到LdapUser中</h1>
	 * @param userVo LdapUser
	 */
	protected void provideSPI(LdapUser userVo){
		
	}

	public String getBaseDN() {
		return baseDN;
	}

	public void setBaseDN(String baseDN) {
		this.baseDN = baseDN;
	}
}
