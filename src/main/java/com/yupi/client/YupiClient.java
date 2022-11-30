package com.yupi.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.entity.User;
import com.yupi.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
* @program: yupi-interface2
*
* @description: 
*
* @author: Mr.Wang
*
* @create: 2022-11-29 19:26
**/
public class YupiClient {
    private String accessKey;
    private String accessSecret;

    public YupiClient(String accessKey, String accessSecret) {
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
    }

    public String getNameByGet(String name){
        String res = HttpUtil.get("http://localhost:9000/api/name/get?name="+name);
        return "Get 方式获取"+res;
    }
    public String getNameByPost(String name){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",name);
        String post = HttpUtil.post("http://localhost:9000/api/name/post", map);
        return "Post 方式获取"+post;
    }
    public String getUserNameByPst(User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse execute = HttpRequest.post("http://localhost:9000/api/name/user").
                addHeaders(getHeaderMap(json)).
                body(json).
                execute();
        return "user方式获取"+execute.body();
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String,String> map = new HashMap<>();
        map.put("accessKey",accessKey);
        //防重放
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("body",body);
        //校验有效期
        map.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        //body + accessSecret生成的签名。
        //服务端从数据库中拿到,和body再去生成一次 如果一样的话就有权限。
        map.put("sign", SignUtil.getSign(body,accessSecret));
        return map;
    }
}
