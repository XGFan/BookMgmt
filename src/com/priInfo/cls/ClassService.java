package com.priInfo.cls;

import java.util.List;
import com.bean.cls.Class;
import com.bean.college.College;
import com.util.Pagination;

public interface ClassService {
	/*查询所有班级*/
	public List findAll();
	/*通过班级编号查询班级信息*/
	public Class findById(String idcls);  
	/*根据条件进行模糊查询，并进行分页操作*/
	public List fuzzyFind(String condition,Pagination pagination);
   /*分页操作*/
	public List findAllByPagination(Pagination pagination);
  /*根据学院，专业，年级，校区精确查询班级信息*/
	public List accurateQuery(String col, String major, String grade,
			String campus, Pagination pagination);
   /*获取所有校区*/
	public List getAllCampus();
   /*获取所有年级*/
	public List getAllGrade();
	
	public boolean  deleteClasses(String[] idclses);// 批量删除
	
	public boolean addClasses(String campus,String grade,int clsnnum,College college);//批量添加班级
	
	public boolean  editClasses(Class cls);//针对校区和人数的修改 
}
