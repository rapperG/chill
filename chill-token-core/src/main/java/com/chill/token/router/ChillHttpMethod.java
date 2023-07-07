
package com.chill.token.router;

import com.chill.token.error.ChillErrorCode;
import com.chill.token.exception.basic.TokenException;

import java.util.HashMap;
import java.util.Map;

/**
 * Http 请求各种请求类型的枚举表示
 *
 * <p> 参考：Spring - HttpMethod
 *
 * @author chill
 * @since 1.0
 */
public enum ChillHttpMethod {

    /**
     * HTTP method
     */
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE, CONNECT,

    /**
     * 代表全部请求方式
     */
    ALL;

    private static final Map<String, ChillHttpMethod> map = new HashMap<>();

    static {
        for (ChillHttpMethod reqMethod : values()) {
            map.put(reqMethod.name(), reqMethod);
        }
    }

    /**
     * String 转 enum
     *
     * @param method 请求类型
     * @return ChillHttpMethod 对象
     */
    public static ChillHttpMethod toEnum(String method) {
        if (method == null) {
            throw new TokenException("Method 不可以是 null").setCode(ChillErrorCode.CODE_10321);
        }
        ChillHttpMethod reqMethod = map.get(method.toUpperCase());
        if (reqMethod == null) {
            throw new TokenException("无效Method：" + method).setCode(ChillErrorCode.CODE_10321);
        }
        return reqMethod;
    }

    /**
     * String[] 转 enum[]
     *
     * @param methods 请求类型数组
     * @return ChillHttpMethod 数组
     */
    public static ChillHttpMethod[] toEnumArray(String... methods) {
        ChillHttpMethod[] arr = new ChillHttpMethod[methods.length];
        for (int i = 0; i < methods.length; i++) {
            arr[i] = ChillHttpMethod.toEnum(methods[i]);
        }
        return arr;
    }

}
