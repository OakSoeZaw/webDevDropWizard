package com.oaksoe.coursefriends.resources;

import com.oaksoe.coursefriends.db.UserDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

    private final UserDAO userDAO;

    public UsersResource(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GET
    public Response getUsers(){
        List<String> users = userDAO.getAllUsernames();
        return Response.ok(Map.of("users", users)).build();
    }

    @POST
    public Response createUser(Map<String, String> body){
        String username = body.get("username");
        if(username == null || username.trim().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "username is required")).build();
        }
        userDAO.ensureUserExists(username.trim());
        return Response.status(Response.Status.CREATED)
                .entity(Map.of("message", "User created", "username", username.trim())).build();
    }
}
