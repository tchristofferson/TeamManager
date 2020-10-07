package com.tchristofferson.teammanager.models;

import java.util.List;

public class IndexUtil {

    //Basic utility method checking if an index is invalid given a List
    public static boolean isInvalidIndex(List<?> list, int index) {
        return index < 0 || list.size() <= index;
    }

}
