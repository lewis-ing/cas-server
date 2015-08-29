package org.jasig.cas.authentication.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author liuwenlong
 * <p>定义user bean来映射ldap中的人员信息</p>
 */
public class LdapUser implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 映射ldap中的objectGUID属性
	 */
	private String uId;
	/**
	 * 映射ldap中的distinguishedName属性
	 */
	private String dn;
	/**
	 * 映射ldap中的cn属性
	 */
	private String cn;
	/**
	 * 映射ldap中的userPrincipalName属性
	 */
	private String email;
	/**
	 * 映射ldap中的sex属性
	 */
	private String sex;
	private Integer age;
	private Date birthDay;
	private String picPath;
	private String telphone;
	private String mobilephone;
	private Boolean level;
	private Boolean isEnable = true;
	private Boolean isSystem = false;
	private String givenName;
	private String sn;
	private String userName;
	private String password;
	/**
	 * 管理用户登录时连接其它引擎时的session管理器
	 */
	private BriefBeanManager briefBeanManager;

	public LdapUser() {

	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public Boolean getLevel() {
		return level;
	}

	public void setLevel(Boolean level) {
		this.level = level;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BriefBeanManager getBriefBeanManager() {
		return briefBeanManager;
	}

	public void setBriefBeanManager(BriefBeanManager briefBeanManager) {
		this.briefBeanManager = briefBeanManager;
	}

	@Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
