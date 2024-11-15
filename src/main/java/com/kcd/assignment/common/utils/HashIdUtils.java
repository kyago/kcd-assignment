package com.kcd.assignment.common.utils;

import org.hashids.Hashids;

public class HashIdUtils {

    private static final Hashids shortHashids = new Hashids("kcd_short", 8, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");

    public static String encodeShort(Integer value) {
        return shortHashids.encode(value);
    }
    public static String encodeShort(Long value) { return shortHashids.encode(value); }
    public static Long decodeShort(String value) {
        return (Long) shortHashids.decode(value)[0];
    }
}
