package com.util;

import java.util.List;

public class GetPaginationInfo {
    /**
     * 分页处理的一个函数
     *
     * @param pagination 分页信息
     * @param list       需要处理的信息
     * @return 处理好的一页信息
     */
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
