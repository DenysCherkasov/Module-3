package com.cherkasov.utils;

import com.cherkasov.action.Actions;

import java.util.Random;

public class Util {

    private final static Random RANDOM = new Random();

    public static String getRandomString() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = RANDOM.nextInt(5, 10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(52);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String[] mapActionToName(final Actions[] values) {
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getName();
        }
        return names;
    }


}
