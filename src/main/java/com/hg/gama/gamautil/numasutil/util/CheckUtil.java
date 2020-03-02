package com.hg.gama.gamautil.numasutil.util;

import com.hg.gama.gamautil.numasutil.constants.UtilCodeConstants;
import com.hg.gama.gamautil.numasutil.exception.GamaException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CheckUtil {

    /**
     * 字符串是否为空
     *
     * @param target 校验参数
     * @param args   参数名称
     */
    public static void checkBlank(String target, Object... args) {
        if (StringUtils.isBlank(target)) {
            throw new GamaException(UtilCodeConstants.PARAMETERS_CHECK_NULL_ERROR, args);
        }
    }

    /**
     * 对象是否为空
     */
    public static void checkNull(Object target, Object... args) {
        if (target == null) {
            throw new GamaException(UtilCodeConstants.PARAMETERS_CHECK_NULL_ERROR, args);
        }
    }

    /**
     * 集合是否为空
     */
    @SuppressWarnings("rawtypes")
    public static void checkEmpty(Collection target, Object... args) {
        if (CollectionUtils.isEmpty(target)) {
            throw new GamaException(UtilCodeConstants.PARAMETERS_CHECK_NULL_ERROR, args);
        }
    }

    /**
     * ID是否大于0
     */
    public static void checkID(Integer target) {
        if (target == null || target <= 0) {
            throw new GamaException(UtilCodeConstants.PARAMETERS_CHECK_ID_ERROR);
        }
    }

    /**
     * 整形数是否大于0
     */
    public static void checkLeZero(Integer target) {
        if (target == null || target <= 0) {
            throw new GamaException(UtilCodeConstants.PARAMETERS_CHECK_ID_ERROR);
        }
    }

    /**
     * 整形数是否大于等于0
     */
    public static void checkLZero(Integer target) {
        if (target == null || target < 0) {
            throw new GamaException(UtilCodeConstants.PARAMETERS_CHECK_ID_ERROR);
        }
    }

    /**
     * 查询结果是否存在
     */
    public static void checkExist(Object target, Object... args) {
        if (target == null) {
            throw new GamaException(UtilCodeConstants.OBJECT_UNEXIST, args);
        }
    }

    /**
     * 查询结果是否存在
     */
    @SuppressWarnings("rawtypes")
    public static void checkExist(Collection target, Object... args) {
        if (CollectionUtils.isEmpty(target)) {
            throw new GamaException(UtilCodeConstants.OBJECT_UNEXIST, args);
        }
    }

    /**
     * 参数均为null
     */
    public static void checkAllNull(Object... target) {
        for (Object o : target) {
            if (o != null) {
                return;
            }
        }
        throw new GamaException(UtilCodeConstants.PARAMETERS_ALL_NULL_ERROR);
    }

    /**
     * 校验大小，不能超过size
     */
    public static void checKMoreSize(Collection target, int size, Object... args) {
        if (target == null) {
            return;
        }
        if (target.size() > size) {
            throw new GamaException(Arrays.toString(args) + "列表长度不能超过" + size);
        }
        return;
    }

    /**
     * 校验大小，不能少于size
     */
    public static void checkLessSize(Collection target, int size, Object... args) {
        if (target == null) {
            return;
        }
        if (target.size() < size) {
            throw new GamaException(Arrays.toString(args) + "列表长度不能少于" + size);
        }
        return;
    }

    public static boolean isTotalHospital(Integer unitId) {
        return unitId == null || unitId == 0;
    }

    public static boolean isTotalHospital(List<Integer> unitIds) {
        if (unitIds == null || unitIds.isEmpty()) {
            return true;
        }
        return unitIds.size() == 1 && unitIds.get(0).equals(0);
    }

}
