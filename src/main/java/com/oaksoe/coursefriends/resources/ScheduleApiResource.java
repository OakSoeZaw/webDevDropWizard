package com.oaksoe.coursefriends.resources;

import com.oaksoe.coursefriends.core.Course;
import com.oaksoe.coursefriends.db.ScheduleDAO;
import com.oaksoe.coursefriends.db.UserDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/api/schedule")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleApiResource {

    private final ScheduleDAO scheduleDAO;
    private final UserDAO userDAO;

    public ScheduleApiResource(ScheduleDAO scheduleDAO, UserDAO userDAO){
        this.scheduleDAO = scheduleDAO;
        this.userDAO = userDAO;
    }

    @GET
    public Response getSchedule(@QueryParam("username") String username){
        if(username == null || username.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "username is requred")).build();
        }
        List<Course> courses = scheduleDAO.getSchedule(username.trim());
        return Response.ok(Map.of("username", username.trim(), "courses", courses)).build();
    }

    @POST
    public Response addCourse(Map<String, String> body){
        String username = body.get("username");
        String courseCode = body.get("courseCode");
        if (username == null || courseCode == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity((Map.of("error", "username and courseCode are required"))).build();
        }
        userDAO.ensureUserExists(username.trim());
        scheduleDAO.addCourse(username, courseCode);
        return Response.status(Response.Status.CREATED)
                .entity(Map.of("message", "Course added", "username", username, "courseCode", courseCode)).build();
    }

    @PUT
    public Response updateCourse(Map<String, String> body){
        String username = body.get("username");
        String oldCourseCode = body.get("oldCourseCode");
        String newCourseCode = body.get("newCourseCode");
        if(username == null || oldCourseCode == null || newCourseCode == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "username, oldCourseCode and newCourseCode are required")).build();
        }
        scheduleDAO.removeCourse(username, oldCourseCode);
        scheduleDAO.addCourse(username, newCourseCode);
        return Response.ok(Map.of("message", "Schedule updated", "username", username, "newCourseCode", newCourseCode)).build();
    }

    @DELETE
    public Response removeCourse(Map<String, String> body){
        String username = body.get("username");
        String courseCode = body.get("courseCode");
        if(username == null || courseCode == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "username and courseCode are required")).build();
        }
        scheduleDAO.removeCourse(username, courseCode);
        return Response.ok(Map.of("message", "Course removed", "username", username, "courseCode", courseCode)).build();
    }
}
