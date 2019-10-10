package com.liuht777.project.template.config.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 加密工具
 *
 * @author liuht
 * @create 2019-09-19 15:09
 */
public final class ShiroUtil {

    /**
     * hash算法名称 MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512、etc。
     */
    public static final String HASH_ALGORITHM = "SHA-256";

    /**
     * Hash 迭代的次数
     */
    public static final int HASH_ITERATIONS = 1;

    /**
     * 获取加密后的密码
     *
     * @param password 需要加密的密码
     * @param salt     盐
     * @return 加密后的密码
     */
    public static String encryptPassword(String password, String salt) {
        SimpleHash hash = new SimpleHash(HASH_ALGORITHM, password, salt, HASH_ITERATIONS);
        return hash.toString();
    }
}
