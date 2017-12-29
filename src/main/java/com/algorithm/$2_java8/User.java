package com.algorithm.$2_java8;

import com.algorithm.$5_json.JsonMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.Serializable;
import java.util.List;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/29.
 * @version:v1.0
 */

public class User implements Serializable{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static void main(String[] args) {
//        User user = new User();
//        user.setCode(1);
//
//        System.out.println(JsonMapper.toJson(user));
//
        String json = "{\"code\":\"0001\"}";
//        System.out.println(JsonMapper.getObjFromJson(json, new TypeReference<User>() {
//        }));
        User user = JSON.parseObject(json, new com.alibaba.fastjson.TypeReference<User>() {});
        System.out.println(JSON.toJSON(user));


    }
}
