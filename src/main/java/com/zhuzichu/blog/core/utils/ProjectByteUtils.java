package com.zhuzichu.blog.core.utils;

import java.io.UnsupportedEncodingException;

/**
 * 字节数组相关工具类
 *
 */
public class ProjectByteUtils {
    /**
     * 16进制单字节最大值
     */
    public static final int HEX_UNIT = 0xff;
    /**
     * 数字单字节所占的位数
     */
    private static final int NUM_BIT_UNIT = 8;

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * Hash 乘法因子
     */
    private static final int HASH_ELEMENT = 16777619;

    /**
     * int 数据转字节数组
     *
     * @param value
     *
     * @return
     */
    public static byte[] getBytes(int value) {
        int byteLength = 4;// int 4 字节
        byte[] data = new byte[byteLength];
        int index = 0;
        //处理 type
        for (int i = 0; i < byteLength; i++) {
            data[index++] = (byte) ((value >> (i * NUM_BIT_UNIT)) & HEX_UNIT);
        }
        return data;
    }

    //    /**
    //     * Integer 数据转字节数组
    //     *
    //     * @param value 不可为null
    //     *
    //     * @return
    //     */
    //    public static byte[] getBytesFromInt(Integer value) {
    //        if (value == null) {
    //            throw new IllegalArgumentException("Param value is null");
    //        }
    //        return getBytesFromInt(value.intValue());
    //    }

    /**
     * long 数据转字节数组
     *
     * @param value
     *
     * @return
     */
    public static byte[] getBytes(long value) {
        int byteLength = 8;// long 8 字节
        byte[] data = new byte[byteLength];
        int index = 0;
        //处理 type
        for (int i = 0; i < byteLength; i++) {
            data[index++] = (byte) ((value >> (i * NUM_BIT_UNIT)) & HEX_UNIT);
        }
        return data;
    }

    /**
     * double 数据转字节数组
     *
     * @param value
     *
     * @return
     */
    public static byte[] getBytes(double value) {
        long lonValue = Double.doubleToLongBits(value);
        return getBytes(lonValue);
    }

    /**
     * float 数据转字节数组
     *
     * @param value
     *
     * @return
     */
    public static byte[] getBytes(float value) {
        int intValue = Float.floatToIntBits(value);
        return getBytes(intValue);
    }

    /**
     * String 数据转字节数组
     *
     * @param value
     * @param charset
     *
     * @return
     */
    public static byte[] getBytes(String value, String charset) throws UnsupportedEncodingException {
        if (value == null) {
            throw new IllegalArgumentException("Param value is null");
        }
        return value.getBytes(charset);
    }

    /**
     * String 数据转字节数组(使用UTF-8字符编码集)
     *
     * @param value
     *
     * @return
     */
    public static byte[] getBytes(String value) throws UnsupportedEncodingException {
        return getBytes(value, DEFAULT_CHARSET);
    }


    /**
     * 计算hash值
     *
     * @param data
     *
     * @return
     */
    public static int computeHash(byte[] data) {
        //基准值 随意质数
        int hash = (int) 2166136261L;


        for (byte b : data) {
            hash = (hash ^ b) * HASH_ELEMENT;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        return hash;
    }


    /**
     * 计算hash值
     *
     * @param sourceDataArrays
     *
     * @return
     */
    public static int computeHash(byte[]... sourceDataArrays) {
        int totalLength = 0;
        for (byte[] source : sourceDataArrays) {
            totalLength += source.length;
        }
        if (totalLength == 0) {
            return 0;
        }

        byte[] data = new byte[totalLength];

        int index = 0;
        for (byte[] source : sourceDataArrays) {
            for (byte b : source) {
                data[index++] = b;
            }

        }
        return computeHash(data);
    }

}
