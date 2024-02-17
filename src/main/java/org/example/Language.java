package org.example;

public enum Language {
    KOREAN("ko"),
    ENGLISH("en"),
    CHINESE_CN("zh-CN");

    private final String str;

    Language(String str) {
        this.str = str;
    }

    public String toString() {
        return str;
    }
}