package com.zhuzichu.blog.core.utils;



import com.zhuzichu.blog.core.exception.ServiceException;
import com.zhuzichu.blog.core.result.ResultCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 参数格式校验工具类
 * Created by Jimersy Lee
 */
public class ProjectVerifyUtils {
    private static final Logger logger = LoggerFactory.getLogger(ProjectVerifyUtils.class);

    /**
     * 正则表达式 数字+英文字母
     */
    public static final String REGX_ALPHABETA_NUM = "^[a-zA-Z0-9]$";

    /**
     * 中国大陆手机号
     */
    public static final String REGX_CN_MOBILE = "^(0086|[+]86)?1\\d{10}";

    /**
     * 邮编
     */
    public static final String REGEX_POSTCODE = "^[0-9]{6}$";

    /**
     * 用于校验 0, 1
     */
    public static final String PEGEX_YES_OR_NO = "^[0-1]{1}$";


    public static boolean verifyPhoneNo(String phoneNo, boolean throwException, String fieldName) throws ServiceException {
        notNull(phoneNo, fieldName, true);
        if (phoneNo.matches("^1[0-9]{10}")) {
            return true;
        }
        if (throwException) {
            throw new ServiceException(ResultCode.INVALID_PARAM, fieldName);
        }
        return false;
    }


    public static boolean notBlank(String value, boolean throwException) throws ServiceException {
        return notBlank(value, "", throwException);
    }

    /**
     * 非空白判断
     *
     * @param value
     * @param paramName
     * @param throwException
     * @return
     * @throws ServiceException
     */
    public static boolean notBlank(String value, String paramName, boolean throwException) throws ServiceException {
        boolean result = StringUtils.isNotBlank(value);
        if (result) {
            return true;
        }
        if (throwException) {
            throw new ServiceException(ResultCode.INVALID_PARAM, paramName);
        } else {
            return false;
        }
    }

    public static boolean greaterThan(int value, int compareValue, String paramName, boolean throwException)
            throws ServiceException {
        boolean result = value > compareValue;
        if (result) {
            return true;
        }
        if (throwException) {
            throw new ServiceException(ResultCode.INVALID_PARAM, paramName + "=" + value);
        }
        return false;
    }

    public static boolean greaterThan(long value, long compareValue, String paramName, boolean throwException)
            throws ServiceException {
        boolean result = value > compareValue;
        if (result) {
            return true;
        }
        if (throwException) {
            throw new ServiceException(ResultCode.INVALID_PARAM, paramName + "=" + value);
        }
        return false;
    }

    public static boolean greaterThan(double value, double compareValue, String paramName, boolean throwException)
            throws ServiceException {
        boolean result = value > compareValue;
        if (result) {
            return true;
        }
        if (throwException) {
            throw new ServiceException(ResultCode.INVALID_PARAM, paramName + "=" + value);
        }
        return false;
    }

    public static boolean maxStringLength(String value, int maxLength, String paramName, boolean allowBlank,
                                          boolean throwException) throws ServiceException {
        //非空校验
        boolean isNotBlank = notBlank(value, false);
        if (!isNotBlank) {
            if (allowBlank) {
                return true;
            }
            if (throwException) {
                throw new ServiceException(paramName + "=" + value);
            }
            return false;
        }
        //字符串长度校验
        if (value.length() > maxLength) {
            if (throwException) {
                throw new ServiceException(paramName + "=" + value);
            }
            return false;
        }
        return true;
    }

    /**
     * 检查数据最小字符串长度
     *
     * @param value
     * @param minLength
     * @param paramName
     * @param allowBlank
     * @param throwException
     * @return
     * @throws ServiceException
     */
    public static boolean minStringLength(String value, int minLength, String paramName, boolean allowBlank,
                                          boolean throwException) throws ServiceException {
        //非空校验
        if (!notBlank(value, false)) {
            if (allowBlank) {
                return true;
            }
            if (throwException) {
                throw new ServiceException(paramName + "=" + value);
            }
            return false;
        }

        //字符串长度校验
        if (value.length() < minLength) {
            if (throwException) {
                throw new ServiceException(paramName + "=" + value);
            }
            return false;
        }
        return true;
    }


    public static boolean contains(int value, int[] container, String paramName, boolean throwException)
            throws ServiceException {
        if (container == null) {
            logger.error("Param container is null");
            throw new ServiceException("container");
        }

        boolean result = ArrayUtils.contains(container, value);
        if (result) {
            return true;
        }
        if (throwException) {
            throw new ServiceException(paramName + "=" + value);
        }
        return false;
    }

    /**
     * 非空判断
     *
     * @param obj
     * @param throwException
     * @return
     * @throws ServiceException
     */
    public static boolean notNull(Object obj, String paramName, boolean throwException) throws ServiceException {
        if (obj == null) {
            if (throwException) {
                throw new ServiceException(paramName + " is null");
            }
            return false;
        }
        return true;
    }


    /**
     * 非空白判断
     *
     * @param field
     * @param throwException
     * @param fieldName
     * @return
     * @throws ServiceException
     */
    public static boolean notBlank(String field, boolean throwException, String fieldName) throws ServiceException {
        if (StringUtils.isNotBlank(field)) {
            return true;
        }
        if (throwException) {
            throw new ServiceException(ResultCode.INVALID_PARAM, fieldName + " 不能为空白");
        }
        return false;
    }

    /**
     * 根据正则表达式判断参数合法性
     *
     * @param field
     * @param regex
     * @param throwException
     * @param fieldName
     * @return
     * @throws ServiceException
     */
    public static boolean verifyByRegex(String field, String regex, boolean throwException, String fieldName)
            throws ServiceException {
        notNull(field, fieldName, true);
        if (field.matches(regex)) {
            return true;
        }
        if (throwException) {
            //            logger.warn(fieldName + " is not match regex[" + regex + "]");
            throw new ServiceException(fieldName + "=" + field);
        }
        return false;
    }

    /**
     * 日期字符串校验
     *
     * @param value
     * @param format
     * @param fieldName
     * @param throwException
     * @return
     * @throws ServiceException
     */
    public static boolean verifyDate(String value, String format, String fieldName, boolean throwException)
            throws ServiceException {
        boolean result = notNull(value, fieldName, throwException);
        if (!result) {
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(value);
        } catch (ParseException e) {
            if (throwException) {
                throw new ServiceException(ResultCode.INVALID_PARAM, fieldName + "=" + value);
            } else {
                return false;
            }
        }
        String actValue = sdf.format(date);
        if (StringUtils.equals(value, actValue)) {
            return true;
        }
        if (throwException) {
            //            logger.warn(fieldName + " is not match regex[" + regex + "]");
            throw new ServiceException(ResultCode.INVALID_PARAM, fieldName + "=" + value);
        }
        return false;
    }

    public static int getVerifyCode4(){
        return (int)((Math.random()*9+1)*1000);
    }

    public static int getVerifyCode5(){
        return (int)((Math.random()*9+1)*10000);
    }
    public static int getVerifyCode6(){
        return (int)((Math.random()*9+1)*100000);
    }
}
