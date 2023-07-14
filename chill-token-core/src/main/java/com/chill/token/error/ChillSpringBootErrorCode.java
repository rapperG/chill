package com.chill.token.error;

/**
 * 定义 chill-token-spring-boot-starter 所有异常细分状态码
 *
 * @author chill
 * @since 1.0
 */
public interface ChillSpringBootErrorCode {

	/** 企图在非 Web 上下文获取 Request、Response 等对象 */
	int CODE_20101 = 20101;

	/** 对象转 JSON 字符串失败 */
	int CODE_20103 = 20103;

	/** JSON 字符串转 Map 失败 */
	int CODE_20104 = 20104;

	/** 默认的 Filter 异常处理函数 */
	int CODE_20105 = 20105;

    /** 转发失败 */
    int CODE_20001 = 20001;

    /** 重定向失败 */
    int CODE_20002 = 20002;

}
