package com.dreamlost.sqservice.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author zgx
 **/
public class CollectionUtils {


    public static boolean isEmpty(ArrayList<Field> list) {
        return list == null || list.size() == 0;
    }
}
