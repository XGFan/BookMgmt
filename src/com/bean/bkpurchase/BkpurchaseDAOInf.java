package com.bean.bkpurchase;

import java.util.List;

public interface BkpurchaseDAOInf {
	public void Bkpursave(List<Bkpurchase> list);

	public void save(Bkpurchase transientInstance);

	public void delete(Bkpurchase persistentInstance);

	public Bkpurchase findById(java.lang.Integer id);

	public List findByExample(Bkpurchase instance);

	public List findAll();

	public List findByProperty(String propertyName, Object value);
}
