package com.oaksoe.coursefriends.views;

import com.oaksoe.coursefriends.core.Course;
import io.dropwizard.views.common.View;
import java.util.List;

public class MyScheduleView extends View {
    private final String username;
    private final List<Course> myClasses;
    private final List<Course> allClasses;

    public MyScheduleView(String username, List<Course> myClasses, List<Course> allClasses){
        super("mySchedule.ftl");
        this.username = username;
        this.myClasses = myClasses;
        this.allClasses = allClasses;
    }

    public String getUsername() { return username; }
    public List<Course> getMyClasses() { return myClasses; }
    public List<Course> getAllClasses() { return allClasses; }
}
