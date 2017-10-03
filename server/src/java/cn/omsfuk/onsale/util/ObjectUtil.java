package cn.omsfuk.onsale.util;

import java.util.stream.Stream;

/**
 * Created by omsfuk on 2017/7/17.
 */
public final class ObjectUtil {

    public static boolean notNull(Object... objects) {
        return Stream.of(objects).allMatch(ObjectUtil::isNotNull);
    }

    private static boolean isNotNull(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof String) {
            if (((String) obj).length() == 0 || "".equals(obj)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isNil(Object obj) {
        return !isNotNull(obj);
    }

    public static boolean isNull(Object... objects) {
        return Stream.of(objects).allMatch(ObjectUtil::isNil);
    }
}
