package com;

public class Main {

    public static int add(int a, int b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException();
        }
        return a * b;
    }

    public String getConfigFile(String filename) {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return "C:\\" + filename;
        }
        if (osName.contains("mac") || osName.contains("linux") || osName.contains("unix")) {
            return "/user/local/" + filename;
        }
        throw new UnsupportedOperationException();
    }

    public String sayHello(String lang) {
        return "Hello" + lang;
    }

    public String capitalize(String s) {
        if (s.length() == 0) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }
}