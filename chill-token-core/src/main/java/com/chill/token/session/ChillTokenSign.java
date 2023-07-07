
package com.chill.token.session;

import java.io.Serializable;

/**
 * Token 签名 Model
 *
 * <p> 挂在到 ChillSession 上的 Token 签名，一般情况下，一个 ChillTokenSign 代表一个登录的会话。</p>
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenSign implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1406115065849845073L;

    /**
     * Token 值
     */
    private String value;

    /**
     * 所属设备类型
     */
    private String device;

    /**
     * 此客户端登录的挂载数据
     */
    private Object tag;

    /**
     * 构建一个
     */
    public ChillTokenSign() {
    }

    /**
     * 构建一个
     *
     * @param value  Token 值
     * @param device 所属设备类型
     * @param tag    此客户端登录的挂载数据
     */
    public ChillTokenSign(String value, String device, Object tag) {
        this.value = value;
        this.device = device;
        this.tag = tag;
    }

    /**
     * @return Token 值
     */
    public String getValue() {
        return value;
    }

    /**
     * @return 所属设备类型
     */
    public String getDevice() {
        return device;
    }

    /**
     * 写入 Token 值
     *
     * @param value /
     * @return 对象自身
     */
    public ChillTokenSign setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * 写入所属设备类型
     *
     * @param device /
     * @return 对象自身
     */
    public ChillTokenSign setDevice(String device) {
        this.device = device;
        return this;
    }

    /**
     * 获取 此客户端登录的挂载数据
     *
     * @return /
     */
    public Object getTag() {
        return this.tag;
    }

    /**
     * 设置 此客户端登录的挂载数据
     *
     * @param tag /
     * @return 对象自身
     */
    public ChillTokenSign setTag(Object tag) {
        this.tag = tag;
        return this;
    }

    //
    @Override
    public String toString() {
        return "ChillTokenSign [value=" + value + ", device=" + device + ", tag=" + tag + "]";
    }

}
