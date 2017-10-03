package cn.omsfuk.onsale.util;

import cn.omsfuk.onsale.vo.UserVO;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Created by omsfuk on 2017/7/17.
 */
public class SessionUtil {

    public static UserVO user() {
        return (UserVO) RequestContextHolder.getRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
    }

    public static Object getAttribue(String key) {
        return RequestContextHolder.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_SESSION);
    }

    public static void setAttribute(String key, Object value) {
        RequestContextHolder.getRequestAttributes().setAttribute(key, value, RequestAttributes.SCOPE_SESSION);
    }
}
