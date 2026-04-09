package com.oaksoe.coursefriends.db;

import com.oaksoe.coursefriends.core.Course;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import java.util.List;

public interface ScheduleDAO {

    @SqlQuery("""
            SELECT c.code, c.title
            FROM classes c
            JOIN user_classes uc ON c.id = uc.class_id
            JOIN users u ON u.id = uc.user_id
            WHERE u.username = :username
            ORDER BY c.code
            """)
    @RegisterConstructorMapper(Course.class)
    List<Course> getSchedule(@Bind("username") String username);

    @SqlUpdate("""
            INSERT INTO user_classes (user_id, class_id)
            SELECT u.id, c.id FROM users u, classes c
            WHERE u.username = :username AND c.code = :courseCode
            ON CONFLICT DO NOTHING
            """)
    void addCourse(@Bind("username") String username, @Bind("courseCode") String courseCode);

    @SqlUpdate("""
            DELETE FROM user_classes
            WHERE user_id = ( SELECT id FROM users WHERE username = :username)
            AND class_id = ( SELECT ID FROM classes WHERE code = :courseCode)
            """)
    void removeCourse(@Bind("username") String username, @Bind("courseCode") String courseCode);
}
