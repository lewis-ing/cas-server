package org.jasig.cas.authentication.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.jasig.cas.authentication.constant.LdapConstant;
import org.jasig.cas.authentication.vo.LdapVo;
import org.ldaptive.LdapException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LdapAuthentication {

	private static Logger logger =LoggerFactory.getLogger(LdapAuthentication.class);
	
	private static final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private static final Control[] connCtls = null;

	public static LdapContext connectionLdap(LdapVo ldapVo) throws LdapException {
		LdapContext ctx = null;
		Hashtable<String, String> env = new Hashtable<String, String>(11);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, ldapVo.getUserName());
        env.put(Context.PROVIDER_URL, ldapVo.getUrl());
        env.put(Context.SECURITY_CREDENTIALS, ldapVo.getPassword());
        env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
        env.put("java.naming.ldap.attributes.binary", LdapConstant.OBJECTGUID);
        
		// 此处若不指定用户名和密码,则自动转换为匿名登录
		try {
			ctx = new InitialLdapContext(env, connCtls);
		} catch (javax.naming.AuthenticationException e) {
			logger.error("用户名和密码认证失败"+e.getMessage(),e);
		} catch (NamingException e) {
			logger.error("Ldap服务器无法连接,请检查是否连接成功!"+e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} /*finally {
			try {
				closeLdapContext(ctx);
			} catch (NamingException e) {
				logger.error("用户名和密码认证失败" + e.getMessage(),e);
				throw new LdapException("用户名和密码认证失败" + e.getMessage());
			}
		}*/
		return ctx;
	}

	public static void closeLdapContext(DirContext ctx) throws NamingException {
		if (null != ctx) {
			ctx.close();
			logger.info("关闭连接");
		}
	}
}
