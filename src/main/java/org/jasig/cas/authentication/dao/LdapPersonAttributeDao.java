package org.jasig.cas.authentication.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;

public class LdapPersonAttributeDao extends StubPersonAttributeDao {

	@Override
    public IPersonAttributes getPerson(String uid) {
        Map<String, List<Object>> attributes = new HashMap<String, List<Object>>();
        authorizationInfo(attributes);
        return new AttributeNamedPersonImpl(attributes);
    }
	
	/**
	 * 授权
	 * @param attributes
	 */
	public void authorizationInfo(Map<String, List<Object>> attributes){
		List<Object> l =new ArrayList<Object>();
		l.add("aa");
		attributes.put("aa", l);
	}
}
