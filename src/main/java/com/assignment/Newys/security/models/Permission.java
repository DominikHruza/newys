package com.assignment.Newys.security.models;

public enum Permission {
    ARTICLE_READ("article:read"),
    ARTICLE_WRITE("article:write"),
    USER_ADD("user:add"),
    USER_DELETE("user:delete");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
