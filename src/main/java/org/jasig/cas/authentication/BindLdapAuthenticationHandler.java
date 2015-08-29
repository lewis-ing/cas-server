package org.jasig.cas.authentication;

import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import org.jasig.cas.Message;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.authentication.util.LdapAuthentication;
import org.jasig.cas.authentication.util.LdapSearchAttribute;
import org.jasig.cas.authentication.vo.LdapUser;
import org.jasig.cas.authentication.vo.LdapVo;
import org.ldaptive.LdapException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BindLdapAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler{
	
	private Logger logger =LoggerFactory.getLogger(LdapAuthentication.class);

	private String url;
	
	private String userDnTemplate;
	
	private List<String> searchBase;

	@Override
	protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential upc)
			throws GeneralSecurityException, PreventedException {
		try {
			return credentials(upc);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		throw new FailedLoginException("用户登录失败");
	}
	
	public HandlerResult credentials(UsernamePasswordCredential credentials) throws Exception{
		List<Message> messageList = Collections.emptyList();
		HandlerResult handlerResult = null;
		LdapContext ldapContext = null;
		LdapVo ldapVo = new LdapVo();
		try {
			ldapVo.setUserName(MessageFormat.format(userDnTemplate, credentials.getUsername()));
			ldapVo.setUrl(url);
			ldapVo.setPassword(credentials.getPassword());
			for(String baseDN:searchBase){
				ldapVo.setBaseDN(baseDN);
				ldapContext = LdapAuthentication.connectionLdap(ldapVo);
				if(ldapContext!=null){
					try {
						handlerResult = createHandlerResult(credentials, createPrincipal(credentials.getUsername(),ldapContext,baseDN), messageList);
					} catch (LoginException | NamingException e) {
						e.printStackTrace();
						logger.error(e.getMessage(), e);
						throw e;
					}finally{
						try {
							LdapAuthentication.closeLdapContext(ldapContext);
						} catch (NamingException e) {
							logger.error("用户名和密码认证失败" + e.getMessage(),e);
						}
					}
					break;
				}
			}
		} catch (LdapException e1) {
			try {
				throw e1;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return handlerResult;
	}
	
	protected Principal createPrincipal(final String username,LdapContext ldapContext,String baseDN) throws LoginException, NamingException {
		LdapUser userVo =new LdapUser();
        Map<String, Object> attributeMap = new LinkedHashMap<String, Object>();
        LdapSearchAttribute attribute=new LdapSearchAttribute();
        attribute.setBaseDN(baseDN);
        attribute.filterLdapAttribute(username, ldapContext, userVo);
        List<LdapUser> userVos = new ArrayList<LdapUser>();
        userVos.add(userVo);
        attributeMap.put(username, userVos);
        return new SimplePrincipal(username, attributeMap);
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserDnTemplate() {
		return userDnTemplate;
	}

	public void setUserDnTemplate(String userDnTemplate) {
		this.userDnTemplate = userDnTemplate;
	}

	public List<String> getSearchBase() {
		return searchBase;
	}

	public void setSearchBase(List<String> searchBase) {
		this.searchBase = searchBase;
	}
}
