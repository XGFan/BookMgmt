package com.bean.courclass;

import java.util.List;

public interface CourclassDAOInf {
	public List findByExample(Courclass instance);
	public List findAll();
	public List findByProperty(String propertyName, Object value);
	public List findByCorSem(String idcor, String semester);
}
