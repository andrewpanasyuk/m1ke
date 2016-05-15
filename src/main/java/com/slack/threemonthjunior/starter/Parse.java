package com.slack.threemonthjunior.starter;

/**
 * Created by al on 14.05.16.
 */
class Parse {

    private String[] options;
    public Parse(String ... args) {
        options = args;
//        return args(0);
    }

    String getOptions(int num) {
        String option = options[num];
        option.toLowerCase();
        return option;
    }
}
