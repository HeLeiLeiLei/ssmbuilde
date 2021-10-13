package com.hl.utils;

import java.util.UUID;

public class UUID_Utils {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
