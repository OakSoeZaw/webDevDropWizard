package com.oaksoe.coursefriends.views;

import com.oaksoe.coursefriends.core.Course;
import io.dropwizard.views.common.View;
import java.util.List;

public class UserScheduleView extends View{
    private final String username;
    private final List<Course> classes;

    public UserScheduleView(String username, List<Course> classes){
        super("userSchedule.ftl");
        this.username = username;
        this.classes = classes;
    }

    public String getUsername() { return username; }
    public List<Course> getClasses() { return classes; }
}
