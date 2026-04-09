package com.oaksoe.coursefriends.views;

import io.dropwizard.views.common.View;
import java.util.List;

public class UserListView extends View {
    private final List<String> users;

    public UserListView(List<String> users){
        super("userList.ftl");
        this.users = users;
    }

    public List<String> getUsers() { return users; }
}
