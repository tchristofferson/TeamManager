package com.tchristofferson.teammanager.models;

import java.util.List;

public class IndexUtil {

    public static boolean isInvalidIndex(List<?> list, int index) {
        return index < 0 || list.size() <= index;
    }

}
