package org.jasig.cas.authentication.vo;

import java.io.Serializable;


/**
 * 此类描述的是：管理用户登录时连接其它引擎时的session管理器
 * @author: liuwenlong@hanhua.com
 * @version: 2013-3-22 上午9:19:09 
 */

public class BriefBeanManager implements Serializable{

	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * 未来添加从数据库中查询出来的用户实体
	 */
	private Object userDBProvide;
	/**
	 * 当用户登录系统后，添加用户登录工作流的session
	 */
	private Object bpmClientServiceSession;
	/**
	 * 
	 * 当用户登录系统后，添加用户登录工作流的CMSession
	 *
	 */
	private Object cmSession;
	/**
	 * vap系统外去实现组织机构的查询，并查询到实体放到orgDBProvider属性中
	 */
	private Object orgDBProvide;
	
	/**
	 * 子系统来实现添加授权的资源
	 */
	private Object resourceAuthor;
	
	public BriefBeanManager(){
		
	}
	

	public Object getUserDBProvide() {
		return userDBProvide;
	}

	/**
	 * 未来添加从数据库中查询出来的用户实体
	 * @param userProvide
	 */
	public void setUserDBProvide(Object userDBProvide) {
		this.userDBProvide = userDBProvide;
	}

	public Object getBpmClientServiceSession() {
		return bpmClientServiceSession;
	}

	/**
	 * <h1>当用户登录系统后，添加用户登录工作流的session</h1>
	 * @param clientService
	 */
	public void setBpmClientServiceSession(Object bpmClientServiceSession) {
		this.bpmClientServiceSession = bpmClientServiceSession;
	}

	public Object getCmSession() {
		return cmSession;
	}

	/**
	 * <h1>连接cm时，需要使用的session</h1>
	 * @param cmSession
	 */
	public void setCmSession(Object cmSession) {
		this.cmSession = cmSession;
	}


    public Object getOrgDBProvide() {
        return orgDBProvide;
    }


    /**
     * <h1>vap系统外去实现组织机构的查询，并查询到实体放到orgDBProvider属性中</h1>
     * @param orgDBProvide
     */
    public void setOrgDBProvide(Object orgDBProvide) {
        this.orgDBProvide = orgDBProvide;
    }


    public Object getResourceAuthor() {
        return resourceAuthor;
    }

    public void setResourceAuthor(Object resourceAuthor) {
        this.resourceAuthor = resourceAuthor;
    }
    
}
