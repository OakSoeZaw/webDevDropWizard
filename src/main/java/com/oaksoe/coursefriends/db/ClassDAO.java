package com.oaksoe.coursefriends.db;

import com.oaksoe.coursefriends.core.Course;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import java.util.List;

public interface ClassDAO {

    @SqlQuery("SELECT code, title FROM classes ORDER BY code")
    @RegisterConstructorMapper(Course.class)
    List<Course> getAllClasses();
}
