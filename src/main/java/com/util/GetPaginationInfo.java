package com.util;

import net.sf.json.JSONArray;

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

    public static JSONArray getSubList(List list, int page, int num) {
        if ((page - 1) * num > list.size()) {
            return JSONArray.fromObject(list.subList((page - 1) * num, list.size()));
        } else {
            return JSONArray.fromObject(list.subList((page - 1) * num, page * num));
        }
    }

    public static JSONArray getSubList(JSONArray jsonArray, int page, int num) {
        if ((page - 1) * num > jsonArray.size()) {
            return JSONArray.fromObject(jsonArray.subList((page - 1) * num, jsonArray.size()));
        } else {
            return JSONArray.fromObject(jsonArray.subList((page - 1) * num, page * num));
        }
    }
}
