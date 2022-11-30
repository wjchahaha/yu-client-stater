package com.yupi.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
* @program: yupi-interface2
*
* @description: 
*
* @author: Mr.Wang
*
* @create: 2022-11-30 21:46
**/
public class SignUtil {

    public static String getSign(String body,String accessKeySerret){
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String content = body + '.'+accessKeySerret;
        return digester.digestHex(content);
    }
}
