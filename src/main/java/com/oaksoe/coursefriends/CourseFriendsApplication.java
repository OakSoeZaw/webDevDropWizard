package com.oaksoe.coursefriends;

import com.oaksoe.coursefriends.db.ClassDAO;
import com.oaksoe.coursefriends.db.ScheduleDAO;
import com.oaksoe.coursefriends.db.UserDAO;
import com.oaksoe.coursefriends.resources.*;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.views.common.ViewBundle;
import org.jdbi.v3.core.Jdbi;

public class CourseFriendsApplication extends Application<CourseFriendsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CourseFriendsApplication().run(args);
    }

    @Override
    public String getName() {
        return "CourseFriends";
    }

    @Override
    public void initialize(final Bootstrap<CourseFriendsConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(final CourseFriendsConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDatabase(),"postgresql");


        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final ScheduleDAO scheduleDAO = jdbi.onDemand(ScheduleDAO.class);
        final ClassDAO classDAO = jdbi.onDemand(ClassDAO.class);

        environment.jersey().register(new ClassesResources(classDAO));
        environment.jersey().register(new UsersResource(userDAO));
        environment.jersey().register(new ScheduleApiResource(scheduleDAO, userDAO));
        environment.jersey().register(new MyScheduleResource(scheduleDAO, classDAO, userDAO));
        environment.jersey().register(new UserScheduleResource(scheduleDAO, userDAO));
    }

}
