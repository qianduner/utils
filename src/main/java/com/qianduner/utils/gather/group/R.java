package com.qianduner.utils.gather.group;


import com.qianduner.utils.constant.UConstant;
import com.qianduner.utils.typewrap.Dto;
import com.qianduner.utils.typewrap.impl.HashDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
    }

    public static R error() {
        return error(500, UConstant.SYS_ERROR_MESSAGE);
    }

    public static R error(String message) {
        return error(500, message);
    }

    public static R error(int code, String message) {
        R r = new R();
        r.put("code", code);
        r.put("message", message);
        return r;
    }

    public static R error(Object obj) {
        R r = new R();
        r.put("code", 500);
        r.put("message", "操作失败");
        r.put("data", obj);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("message", msg);
        return r;
    }

    public static R ok(List list) {
        R r = new R();
        r.put("message", "操作成功");
        r.put("data", list);
        return r;
    }

    public static R ok(Object obj) {
        R r = new R();
        r.put("message", "操作成功");
        r.put("data", obj);
        return r;
    }

    public static R ok(List<? extends Object> pList, Integer total) {
        Dto tempDto = new HashDto();
        tempDto.put(YmCons.READER_ROOT_PROPERTY, pList);
        tempDto.put(YmCons.READER_TOTAL_PROPERTY, total);
        return R.ok(tempDto);
    }


    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok(Dto map) {
        R r = new R();
        r.put("data", map);
        return r;
    }

    public static R ok() {
        R r = new R();
        r.put("code", 0);
        r.put("message", "操作成功");
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
