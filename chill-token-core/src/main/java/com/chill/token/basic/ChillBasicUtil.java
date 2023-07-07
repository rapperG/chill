
package com.chill.token.basic;

/**
 * Chill-Token Http Basic 认证模块，Util 工具类
 *
 * @author chill
 * @since 1.0
 */
public class ChillBasicUtil {

    private ChillBasicUtil() {
    }

    /**
     * 底层使用的 ChillBasicTemplate 对象
     */
    public static ChillBasicTemplate chillBasicTemplate = new ChillBasicTemplate();

    /**
     * 获取浏览器提交的 Basic 参数 （裁剪掉前缀并解码）
     *
     * @return 值
     */
    public static String getAuthorizationValue() {
        return chillBasicTemplate.getAuthorizationValue();
    }

    /**
     * 对当前会话进行 Basic 校验（使用全局配置的账号密码），校验不通过则抛出异常
     */
    public static void check() {
        chillBasicTemplate.check();
    }

    /**
     * 对当前会话进行 Basic 校验（手动设置账号密码），校验不通过则抛出异常
     *
     * @param account 账号（格式为 user:password）
     */
    public static void check(String account) {
        chillBasicTemplate.check(account);
    }

    /**
     * 对当前会话进行 Basic 校验（手动设置 Realm 和 账号密码），校验不通过则抛出异常
     *
     * @param realm   领域
     * @param account 账号（格式为 user:password）
     */
    public static void check(String realm, String account) {
        chillBasicTemplate.check(realm, account);
    }

}
