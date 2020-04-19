package com.zgds.xfyj.util;

import java.util.UUID;

/**
 *
 */
public class UUIDUtils {

    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}