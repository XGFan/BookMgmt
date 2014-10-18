package com.util;

import java.util.List;

public class GetPaginationInfo {
	/* 公用的业务方法，用于根据分页获取子数据*/
	public static List getSubList(List list, Pagination pagination) {
		pagination.setTotalRecord(list.size());
		if (pagination.getSize() < list.size()) {
			int range = pagination.getStart() + pagination.getSize();
			if (range < list.size()) {
				list = list.subList(pagination.getStart(),
						pagination.getStart() + pagination.getSize());
			} else {
				list = list.subList(pagination.getStart(), list.size());
			}
		}
		return list;
	}
}
