package com.oaksoe.coursefriends.resources;

import com.oaksoe.coursefriends.core.Course;
import com.oaksoe.coursefriends.db.ClassDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/api/classes")
@Produces(MediaType.APPLICATION_JSON)
public class ClassesResources {
    private final ClassDAO classDAO;

    public ClassesResources(ClassDAO classDAO){
        this.classDAO = classDAO;
    }

    @GET
    public Response getClasses(){
        List<Course> classes = classDAO.getAllClasses();
        return Response.ok(classes).build();
    }
}
