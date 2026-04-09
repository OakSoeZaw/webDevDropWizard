package com.oaksoe.coursefriends.db;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.customizer.Bind;
import java.util.List;

public interface UserDAO {

    @SqlQuery("SELECT username FROM users ORDER BY username")
    List<String> getAllUsernames();

    @SqlQuery("SELECT id FROM users WHERE username = :username")
    Integer findUserId(@Bind("username") String username);

    @SqlUpdate("INSERT INTO users (username) VALUES (:username)")
    void insertUser(@Bind("username") String username);

    default void ensureUserExists(String username){
        if(findUserId(username) == null){
            insertUser(username);
        }
    }

}
