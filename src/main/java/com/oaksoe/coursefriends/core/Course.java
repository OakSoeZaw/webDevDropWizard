package com.oaksoe.coursefriends.core;

import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Course {
    private String code;
    private String title;

    @JdbiConstructor
    public Course(@ColumnName("code") String code, @ColumnName("title") String title){
        this.code = code;
        this.title = title;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
}
