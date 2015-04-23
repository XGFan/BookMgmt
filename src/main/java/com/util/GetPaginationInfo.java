package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List getSubList(List list, int page, int num) {
        if ((page - 1) * num > list.size()) {
            return list.subList((page - 1) * num, list.size());
        } else {
            return list.subList((page - 1) * num, page * num);
        }
    }

    public static Map getSubMap(List list, int page, int num) {
        int size = list.size();
        HashMap<String,Object> ans = new HashMap<String, Object>();
        num = num>size?size:num;
        if ((page - 1) * num > size) {
            ans.put("rows", list.subList((page - 1) * num, size));
            ans.put("total", Integer.toString(size));
        } else {
            ans.put("rows", list.subList((page - 1) * num, size > page * num ? page * num : size));
            ans.put("total", Integer.toString(size));
        }
        return ans;
    }

}
