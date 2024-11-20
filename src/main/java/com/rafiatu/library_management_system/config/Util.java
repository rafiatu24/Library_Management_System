package com.rafiatu.library_management_system.config;

import java.util.ArrayList;

public class Util {
    /**
     * maps ArrayList<ArrayList<String>> to String[][]
     *
     * @param list ArrayList<ArrayList<String>>
     * @return String[][]
     */
    public static String[][]  convert(ArrayList<ArrayList<String>> list) {
        if (list.isEmpty())
            return new String[][]{};

        int rows = list.size();
        int cols = list.getFirst().size();

        String[][] array = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = list.get(i).get(j);
            }
        }

        return array;
    }
}
