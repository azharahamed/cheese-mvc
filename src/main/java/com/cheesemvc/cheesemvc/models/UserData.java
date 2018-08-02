package com.cheesemvc.cheesemvc.models;

import java.util.HashMap;

public class UserData {
    private static HashMap<Integer, User> users = new HashMap<>();
    private static int nextId = 1;

    public static void add(User user){
        user.setId(nextId);
        users.put(nextId, user);
        nextId++;
    }

    public static HashMap<Integer, User> getAll(){
        return users;
    }

    public static User getById(int id){
        return users.get(id);
    }

}
