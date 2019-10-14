package com.haishu.common.enumeration;

/**
 * CommonStatus
 * @author zhb
 * @date 2019/07/24
 */
public enum CommonStatus {
    STATUS_0((short) 0, "无效"),
    STATUS_1((short) 1, "有效");

    public Short code;
    public String desc;

    CommonStatus(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 判断是否存在
     * @param code
     * @return
     */
    public static boolean contains(Short code){
        for (CommonStatus enu : CommonStatus.values()) {
            if (enu.code.equals(code)) {
                return true;
            }
        }
        return false;
    }
}