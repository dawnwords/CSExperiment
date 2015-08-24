package cn.edu.fudan.se.crowdservice.util;

/**
 * Created by Dawnwords on 2015/8/20.
 */
public interface Parser {
    Parser toDouble = new Parser() {
        @Override
        public Object parse(String s) {
            return Double.parseDouble(s);
        }
    }, toInt = new Parser() {
        @Override
        public Object parse(String s) {
            return Integer.parseInt(s);
        }
    }, toLong = new Parser() {
        @Override
        public Object parse(String s) {
            return Long.parseLong(s);
        }
    }, toBoolean = new Parser() {
        @Override
        public Object parse(String s) {
            return Boolean.valueOf(s);
        }
    };

    Object parse(String s);
}
