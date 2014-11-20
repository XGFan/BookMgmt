package com.priInfo.cls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.bean.cls.ClassDAO;
import com.bean.cls.Class;
import com.bean.college.College;
import com.util.GetPaginationInfo;
import com.util.Pagination;

public class ClassServiceImpl implements ClassService {

	public ClassDAO classDAO;

	public ClassDAO getClassDAO() {
		return classDAO;
	}

	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}

	public List findAll() {
		List list = classDAO.findAll();
		return this.class2List(list);
	}

    /**
     * 将List转换成map类型
     * @param l list
     * @return map类型的list
     */
	public static List class2List(List l) {
		Iterator it = l.iterator();
		List clslist = new ArrayList();
		while (it.hasNext()) {
			Map map = new HashMap();
			Class cls = (Class) it.next();
			map.put("col", cls.getCollege().getCol());
			map.put("major", cls.getCollege().getMajor());
			map.put("idcm", cls.getCollege().getIdcm());
			map.put("semester", cls.getSemester());
			map.put("campus", cls.getCampus());
			map.put("clsno", cls.getClsno());
			map.put("grade", cls.getGrade());
			map.put("idcls", cls.getIdcls());
			map.put("clsno", cls.getClsno());
			map.put("stunum", cls.getStunum());
			clslist.add(map);
		}
        System.out.println(clslist);
		return clslist;
	}

	public List fuzzyFind(String condition, Pagination pagination) {
		return GetPaginationInfo.getSubList(class2List(classDAO.getClassFuzzy(condition)),pagination);
	}

	public List findAllByPagination(Pagination pagination) {
		return GetPaginationInfo.getSubList(class2List(classDAO.findAll()),pagination);
	}

    /**
     * 分页处理的一个函数
     * @param pagination 分页信息
     * @param list 需要处理的信息
     * @return 处理好的一页信息
     * @deprecated
     */
	public List dealToList(Pagination pagination, List list) {
        /*设置分页的总共记录条数为list的条数*/
		pagination.setTotalRecord(list.size());
        /*如果分页的单页条数小于list条数*/
		if (pagination.getSize() < list.size()) {
            /*当前页起始位置+一页的条数，即当前页的最大条目值，略微拗口*/
			int range = pagination.getStart() + pagination.getSize();
            /*如果range依旧小于list条数*/
			if (range < list.size()) {
                /*从list中截取1页作为新的list*/
				list = list.subList(pagination.getStart(),
						pagination.getStart() + pagination.getSize());
			} else {
				list = list.subList(pagination.getStart(), list.size());
			}
		}
		return list;
	}

	public List accurateQuery(String col, String major, String grade,String campus, Pagination pagination) {
        return GetPaginationInfo.getSubList(class2List(classDAO.getClassByGradeCampusColMajor(col, major, grade, campus)),pagination);
    }

	public List getAllCampus() {
		return classDAO.getAllCampus();
	}


	public List getAllGrade() {
		return classDAO.getAllGrade();
	}


	public boolean deleteClasses(String[] idclses) {
		Class cls = new Class();
		for (String idcls : idclses) {
			cls = classDAO.findById(idcls);
			classDAO.delete(cls);
		}
		return true;
	}


	public boolean addClasses(String campus, String grade, int clsnum,
			College college) {
		Class cls = new Class();
		Integer j = classDAO.getClsNum(grade, college.getIdcm()) + 1;// 班级表此时班号最大值
		String idcls = "";
		for (int i = classDAO.getClsNum(grade, college.getIdcm()) + 1; i < j
				+ clsnum; i++) {
			if (i < 10) {
				idcls = grade + college.getIdcm() + "0" + i;
			} else {
				idcls = grade + college.getIdcm() + i;
			}
			cls.setIdcls(idcls);
			idcls = "";
			cls.setCollege(college);
			cls.setCampus(campus);
			cls.setGrade(grade);
			cls.setSemester("1");
			cls.setClsno(i);
			cls.setStunum(50);
			//System.out.println(cls);
			classDAO.save(cls);
		}
		return true;
	}

	public boolean editClasses(Class cls) {
		classDAO.saveOrUpdate(cls);
		return true;
	}

	public Class findById(String idcls) {
		return classDAO.findById(idcls);
	}

}
