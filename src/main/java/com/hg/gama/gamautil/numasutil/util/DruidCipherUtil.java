package com.hg.gama.gamautil.numasutil.util;

import com.alibaba.druid.filter.config.ConfigTools;

public class DruidCipherUtil {
    public static String encrypt(String plainText) {
        try {
            return ConfigTools.encrypt(plainText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String cipherText) {
        try {
            return ConfigTools.decrypt(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(encrypt("numas@2016"));
        System.out.println(decrypt("hW9a9YPQbSg71gj9VxgVke/1AIYjXmm3aFAtddbfCwRAVbI3bbpzzB0qRq2jHw6wr4qDYdGuXm8UXeKunDuR4g=="));
    }
}
