package com.priInfo.corplan;

import java.util.List;
import com.bean.corplan.Corplan;
import com.bean.corplan.Courplan;

public interface CorplanService {
	/**
	 * 查询所有的corplan记录
	 * 
	 * @return List
	 */
	public List<Object> findAll();
	
	/**
	 * 按col模糊查询所有的corplan记录
	 * 
	 * @return List
	 */
	public List findCorplanByCol(String col);
	
	/**
	 * 按col，major查询所有的corplan记录
	 * 
	 * @return List
	 */
	public List findCorplanByColMajor(String col, String major);

	/**
	 * 按col，major，semester查询所有的corplan记录
	 * 
	 * @return List
	 */
	
	public List findCorplanByColMajorSem(String col, String major,
			String semester);

	/**
	 * 按col或major查询所有的corplan记录
	 * 
	 * @return List
	 */
	public List findCorplanByColOrMajor(String condition);
	
	/**
	 * 按col或major或semester 模糊查询所有的corplan记录
	 * 
	 * @return List
	 */
	public List fuzzyFind(String condition);
	public List fuzzyQuery(String col,String major,String corname);
	/**
	 * 添加corplan记录
	 * 
	 * @return boolean
	 */
	public boolean saveCorplan(Corplan corplan);
	
	/**
	 * 批量添加corplan记录
	 * 
	 * @return boolean
	 */
	public boolean saveCorplans(Corplan[] corplan);
	
	/**
	 * 更新corplan记录
	 * 
	 * @return boolean
	 */
	public boolean updateCorplan(Corplan corplan);
	public boolean updateCorplan(String col,String major,String corname,String semester);
	
	/**
	 * 删除corplan记录
	 * 
	 * @return boolean
	 */
	public boolean deleteById(String idCorSem);
	public boolean deleteCorplan(String col, String major, String semester,
			String idcor, String corname);
	//public boolean delete(String corname); 
	/*初始化教学计划表*/
	public void initCorplan();
	
	/*清空教学计划表*/
	public void dropAllCorplan();

}
