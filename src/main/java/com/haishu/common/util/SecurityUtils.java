package com.haishu.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * SecurityUtils
 * @author zhb
 * @date 2019/07/11
 */
public class SecurityUtils {
    public static String toMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String sha256Hex(String str) {
        return DigestUtils.sha256Hex(str);
    }
}
