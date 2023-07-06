
package com.chill.token.json;

import com.chill.token.error.SaErrorCode;
import com.chill.token.exception.NotImplException;

import java.util.Map;

/**
 * JSON 转换器，默认实现类
 *
 * <p> 如果代码断点走到了此默认实现类，说明框架没有注入有效的 JSON 转换器，需要开发者自行实现并注入 </p>
 *
 * @author chill
 * @since 1.30.0
 */
public class SaJsonTemplateDefaultImpl implements SaJsonTemplate {

    public static final String ERROR_MESSAGE = "未实现具体的 json 转换器";

    @Override
    public String toJsonString(Object obj) {
        throw new NotImplException(ERROR_MESSAGE).setCode(SaErrorCode.CODE_10003);
    }

    @Override
    public Map<String, Object> parseJsonToMap(String jsonStr) {
        throw new NotImplException(ERROR_MESSAGE).setCode(SaErrorCode.CODE_10003);
    }

}
