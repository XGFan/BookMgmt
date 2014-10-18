package com.bean.bookcorsup;

import java.util.List;
import java.util.Set;

public interface BookcorsupDAOInf {
	public List findByExample(Bookcorsup instance);

	public List findAll();

	public List findByProperty(String propertyName, Object value);

	public List findbklist(String str);

	public List findcorlistbyidbk(String idbk);
}
