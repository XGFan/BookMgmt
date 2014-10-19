package com.bean.bkpurchase;

import java.util.List;
/**教科书购买表DAO */
public interface BkpurchaseDAOInf {


    /**
     * 传入需要购买的教科书列表，清空原有数据，并保存新数据。
     * @param list 需要购买的教科书列表LIST
     */
	public void Bkpursave(List<Bkpurchase> list);

    /**
     * 保存一个需要购买的教科书对象
     * @param transientInstance 需要购买的教科书对象
     */
	public void save(Bkpurchase transientInstance);

    /**
     * 删除需要购买的教科书对象
     * @param persistentInstance 需要购买的教科书对象
     */
	public void delete(Bkpurchase persistentInstance);

    /**
     * 根据需要购买的教科书清单ID来查找需要购买的教科书对象
     * @param id 需要购买的教科书对象ID
     * @return 需要购买的教科书对象
     */
	public Bkpurchase findById(java.lang.Integer id);

    /**
     * 传入实例进行查找
     * @param instance 实例example
     * @return 对象列表
     */
	public List findByExample(Bkpurchase instance);

    /**
     * 返回所有需要购买的教科书的清单
     * @return 所有需要购买的教科书清单
     */
	public List findAll();

    /**
     * 根据指定的属性和值来查找需要购买的教科书清单
     * @param propertyName 属性名
     * @param value 属性值
     * @return 查找到的需要购买的教科书清单LIST
     */
	public List findByProperty(String propertyName, Object value);
}
