package com.zhuzichu.blog.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 平台流水生成器(单机)
 *
 */
public class ProjectPlatTraceUtils {
    /**
     * 默认完整时间格式 yyyyMMddHHmmssS 17位
     */
    public static String DEFAULT_FULL_DATE_FMT = "yyyyMMddHHmmssS";

    private static final int LIMIT = 1000000;
    private static final int START = 1;
    private static int seqHouse = START;

    /**
     * 生成平台流水
     *
     * @param dateFormat  日期格式
     * @param busiFlag    业务标识
     * @param totalLength 流水字符串长度，若该长度小于dateFormat.length()+busiFlag.lenght()，则从左到右依次截取
     *
     * @return
     */
    public static String genTrace(String dateFormat, String busiFlag, int totalLength) {
        if (StringUtils.isBlank(dateFormat)) {
            throw new IllegalArgumentException("Param dateFormat  should not be null.");
        }
        if (totalLength < 1) {
            throw new IllegalArgumentException("Param total length should be greater than 0.");
        }
        if (StringUtils.isBlank(busiFlag)) {
            busiFlag = "";
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        StringBuilder builder = new StringBuilder(sdf.format(date)).append(busiFlag);
        if (totalLength == builder.length()) {
            return builder.toString();
        }

        if (totalLength < builder.length()) {
            return builder.substring(0, totalLength);
        }

        //拼接流水
        String currentSeq = "";
        synchronized (ProjectPlatTraceUtils.class) {
            if (seqHouse >= LIMIT) {
                seqHouse = START;
            }
            currentSeq = "" + seqHouse;
            seqHouse++;
        }

        return builder.append(formatNum(currentSeq, totalLength - builder.length())).toString();
    }


    /**
     * 生成平台流水
     *
     * @param dateFormat  日期格式
     * @param busiFlag    业务标识
     * @param seqSuffix   末尾流水号
     * @param totalLength 流水字符串长度，若该长度小于dateFormat.length()+busiFlag.lenght()，则从左到右依次截取
     * @param totalLength
     * @return
     */
    public static String genTrace(String dateFormat, String busiFlag, String seqSuffix, int totalLength) {
        if (StringUtils.isBlank(dateFormat)) {
            throw new IllegalArgumentException("Param dateFormat  should not be null.");
        }
        if (totalLength < 1) {
            throw new IllegalArgumentException("Param total length should be greater than 0.");
        }
        if (StringUtils.isBlank(busiFlag)) {
            busiFlag = "";
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        StringBuilder builder = new StringBuilder(busiFlag).append(sdf.format(date));
        if (totalLength == builder.length()) {
            return builder.toString();
        }

        if (totalLength < builder.length()) {
            return builder.substring(0, totalLength);
        }


        return builder.append(formatNum(seqSuffix, totalLength - builder.length())).toString();
    }


    /**
     * 生成平台流水
     *
     * @param dateFormat  日期格式
     * @param totalLength 流水字符串长度，若该长度小于dateFormat.length()+busiFlag.lenght()，则从左到右依次截取
     *
     * @return
     */
    public static String genTrace(String dateFormat, int totalLength) {
        return genTrace(dateFormat, "", totalLength);
    }

    /**
     * 生成平台流水30位 含busiFlag长度
     *
     * @param busiFlag 业务标识
     *
     * @return
     */
    public static String genTrace30(String busiFlag) {
        return genTrace(DEFAULT_FULL_DATE_FMT, busiFlag, 30);
    }

    /**
     * 生成平台流水27位 含busiFlag长度
     *
     * @param busiFlag 业务标识
     *
     * @return
     */
    public static String genTrace27(String busiFlag) {
        return genTrace(DEFAULT_FULL_DATE_FMT, busiFlag, 27);
    }


    /**
     * 格式化数字到指定长度。若length>numStr.lenght(), 则前补0；否则从右到左截取
     *
     * @param numStr
     * @param length
     *
     * @return
     */
    public static String formatNum(String numStr, int length) {
        if (numStr.length() == length) {
            return numStr;
        }
        if (numStr.length() > length) {
            return numStr.substring(numStr.length() - length, numStr.length());
        }
        int delta = length - numStr.length();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < delta; i++) {
            builder.append(0);
        }

        return builder.append(numStr).toString();
    }

}
