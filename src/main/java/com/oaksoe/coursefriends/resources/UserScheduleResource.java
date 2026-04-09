package com.oaksoe.coursefriends.resources;

import com.oaksoe.coursefriends.core.Course;
import com.oaksoe.coursefriends.db.ScheduleDAO;
import com.oaksoe.coursefriends.db.UserDAO;
import com.oaksoe.coursefriends.views.UserScheduleView;
import com.oaksoe.coursefriends.views.UserListView;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/user-schedule")
public class UserScheduleResource {

    private final  ScheduleDAO scheduleDAO;
    private final UserDAO userDAO;

    public UserScheduleResource(ScheduleDAO scheduleDAO, UserDAO userDAO){
        this.scheduleDAO = scheduleDAO;
        this.userDAO= userDAO;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response get(@QueryParam("username") String username){

        if(username == null || username.isBlank()){
            List<String> users = userDAO.getAllUsernames();
            return Response.ok(new UserListView(users)).build();
        }

        username = username.trim();
        List<Course> classes = scheduleDAO.getSchedule(username);
        return Response.ok(new UserScheduleView(username, classes)).build();
    }
}
