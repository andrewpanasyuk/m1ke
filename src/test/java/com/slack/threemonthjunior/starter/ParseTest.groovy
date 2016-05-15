package com.slack.threemonthjunior.starter

import spock.lang.Specification;

/**
 * Created by al on 14.05.16.
 */
public class ParseTest extends Specification {

    def "should parse arg to [OPTION...] "() { // not available then excite help of applications or error
        given:
//        def parse = new Parse();
        def String[] args = ["init"];
//        def options;

        when:
        def parse = new Parse(args);

        then:
        parse.getOptions(0) == args[0];

    }

}
