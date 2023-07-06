
package com.chill.token.router;

import com.chill.token.error.SaErrorCode;
import com.chill.token.exception.SaTokenException;

import java.util.HashMap;
import java.util.Map;

/**
 * Http 请求各种请求类型的枚举表示
 *
 * <p> 参考：Spring - HttpMethod
 *
 * @author chill
 * @since 1.27.0
 */
public enum SaHttpMethod {

    /**
     * HTTP method
     */
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE, CONNECT,

    /**
     * 代表全部请求方式
     */
    ALL;

    private static final Map<String, SaHttpMethod> map = new HashMap<>();

    static {
        for (SaHttpMethod reqMethod : values()) {
            map.put(reqMethod.name(), reqMethod);
        }
    }

    /**
     * String 转 enum
     *
     * @param method 请求类型
     * @return SaHttpMethod 对象
     */
    public static SaHttpMethod toEnum(String method) {
        if (method == null) {
            throw new SaTokenException("Method 不可以是 null").setCode(SaErrorCode.CODE_10321);
        }
        SaHttpMethod reqMethod = map.get(method.toUpperCase());
        if (reqMethod == null) {
            throw new SaTokenException("无效Method：" + method).setCode(SaErrorCode.CODE_10321);
        }
        return reqMethod;
    }

    /**
     * String[] 转 enum[]
     *
     * @param methods 请求类型数组
     * @return SaHttpMethod 数组
     */
    public static SaHttpMethod[] toEnumArray(String... methods) {
        SaHttpMethod[] arr = new SaHttpMethod[methods.length];
        for (int i = 0; i < methods.length; i++) {
            arr[i] = SaHttpMethod.toEnum(methods[i]);
        }
        return arr;
    }

}
