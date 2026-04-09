package com.oaksoe.coursefriends.resources;

import com.oaksoe.coursefriends.core.Course;
import com.oaksoe.coursefriends.core.User;
import com.oaksoe.coursefriends.db.ClassDAO;
import com.oaksoe.coursefriends.db.ScheduleDAO;
import com.oaksoe.coursefriends.db.UserDAO;
import com.oaksoe.coursefriends.views.MyScheduleView;
import com.oaksoe.coursefriends.views.LoginView;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/my-schedule")
public class MyScheduleResource {

    private final ScheduleDAO scheduleDAO;
    private final ClassDAO classDAO;
    private final UserDAO userDAO;

    public MyScheduleResource(ScheduleDAO scheduleDAO, ClassDAO classDAO, UserDAO userDAO){
        this.scheduleDAO = scheduleDAO;
        this.classDAO = classDAO;
        this.userDAO =userDAO;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response get(@QueryParam("username") String username,
                        @CookieParam("username") String cookieUsername){

        if(cookieUsername != null && !cookieUsername.isBlank()){
            username = cookieUsername;
        }
        if(username == null || username.isBlank()){
            return Response.ok(new LoginView()).build();
        }

        username = username.trim();
        userDAO.ensureUserExists(username);

        List<Course> myClasses = scheduleDAO.getSchedule(username);
        List<Course> allClasses = classDAO.getAllClasses();

        return Response.ok(new MyScheduleView(username, myClasses, allClasses))
                .cookie(new jakarta.ws.rs.core.NewCookie.Builder("username")
                        .value(username).path("/").build())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(@CookieParam("username") String username,
                         @FormParam("action") String action,
                         @FormParam("courseCode") String courseCode){

        if(username == null || username.isBlank()){
            return Response.seeOther(URI.create("/my-schedule")).build();
        }

        if(action != null && courseCode != null){
            if(action.equals("add")){
                scheduleDAO.addCourse(username,courseCode.trim());
            }else if(action.equals("remove")){
                scheduleDAO.removeCourse(username,courseCode.trim());
            }
        }
        return Response.seeOther(URI.create("/my-schedule")).build();
    }
}
