package com.haishu.common.util;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil
 * @author zhb
 * @date 2019/07/08
 */
@Service
public class StringUtil {
    private final static Pattern PHONE_PATTERN = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$");
    private final static Pattern IP_PATTERN = Pattern.compile("^((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))$");


    /**
     * 手机号验证
     * @param phone
     * @return 验证通过返回true
     */
    public static boolean isMobile(String phone) {
        if (phone == null) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * IP验证
     * @param ip
     * @return 验证通过返回true
     */
    public static boolean isIpAddress(String ip) {
        if (ip == null) {
            return false;
        }
        return IP_PATTERN.matcher(ip).matches();
    }

    public static void main(String[] args) {
        System.out.println(isIpAddress("127.0.0.1"));
        System.out.println(isIpAddress("127.0.0.0"));
        System.out.println(isIpAddress("127.0.0.999"));
        System.out.println(isIpAddress("12dfs"));
        System.out.println(isIpAddress(""));
        System.out.println(isIpAddress(""));
    }
}
