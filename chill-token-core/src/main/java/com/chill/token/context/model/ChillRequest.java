
package com.chill.token.context.model;

import com.chill.token.error.ChillErrorCode;
import com.chill.token.exception.basic.TokenException;
import com.chill.token.util.ChillFoxUtil;

import java.util.List;
import java.util.Map;

/**
 * Request 请求对象 包装类
 *
 * @author chill
 * @since 1.0
 */
public interface ChillRequest {

    /**
     * 获取底层被包装的源对象
     *
     * @return /
     */
    Object getSource();

    /**
     * 在 [ 请求体 ] 里获取一个参数值
     *
     * @param name 键
     * @return 值
     */
    String getParam(String name);

    /**
     * 在 [ 请求体 ] 里获取一个参数值，值为空时返回默认值
     *
     * @param name         键
     * @param defaultValue 值为空时的默认值
     * @return 值
     */
    default String getParam(String name, String defaultValue) {
        String value = getParam(name);
        if (ChillFoxUtil.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 在 [ 请求体 ] 里检测提供的参数是否为指定值
     *
     * @param name  键
     * @param value 值
     * @return 是否相等
     */
    default boolean isParam(String name, String value) {
        String paramValue = getParam(name);
        return ChillFoxUtil.isNotEmpty(paramValue) && paramValue.equals(value);
    }

    /**
     * 在 [ 请求体 ] 里检测请求是否提供了指定参数
     *
     * @param name 参数名称
     * @return 是否提供
     */
    default boolean hasParam(String name) {
        return ChillFoxUtil.isNotEmpty(getParam(name));
    }

    /**
     * 在 [ 请求体 ] 里获取一个值 （此值必须存在，否则抛出异常 ）
     *
     * @param name 键
     * @return 参数值
     */
    default String getParamNotNull(String name) {
        String paramValue = getParam(name);
        if (ChillFoxUtil.isEmpty(paramValue)) {
            throw new TokenException("缺少参数：" + name).setCode(ChillErrorCode.CODE_12001);
        }
        return paramValue;
    }

    /**
     * 获取 [ 请求体 ] 里提交的所有参数名称
     *
     * @return 参数名称列表
     */
    List<String> getParamNames();

    /**
     * 获取 [ 请求体 ] 里提交的所有参数
     *
     * @return 参数列表
     */
    Map<String, String> getParamMap();

    /**
     * 在 [ 请求头 ] 里获取一个值
     *
     * @param name 键
     * @return 值
     */
    String getHeader(String name);

    /**
     * 在 [ 请求头 ] 里获取一个值
     *
     * @param name         键
     * @param defaultValue 值为空时的默认值
     * @return 值
     */
    default String getHeader(String name, String defaultValue) {
        String value = getHeader(name);
        if (ChillFoxUtil.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 在 [ Cookie作用域 ] 里获取一个值
     *
     * @param name 键
     * @return 值
     */
    String getCookieValue(String name);

    /**
     * 返回当前请求path (不包括上下文名称)
     *
     * @return /
     */
    String getRequestPath();

    /**
     * 返回当前请求 path 是否为指定值
     *
     * @param path path
     * @return /
     */
    default boolean isPath(String path) {
        return getRequestPath().equals(path);
    }

    /**
     * 返回当前请求的url，不带query参数，例：http://xxx.com/test
     *
     * @return /
     */
    String getUrl();

    /**
     * 返回当前请求的类型
     *
     * @return /
     */
    String getMethod();

    /**
     * 判断此请求是否为 Ajax 异步请求
     *
     * @return /
     */
    default boolean isAjax() {
        return getHeader("X-Requested-With") != null;
    }

    /**
     * 转发请求
     *
     * @param path 转发地址
     * @return 任意值
     */
    Object forward(String path);

}
