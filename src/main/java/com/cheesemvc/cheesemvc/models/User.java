package com.cheesemvc.cheesemvc.models;

public class User {
    private String username;
    private String email;
    private String password;
    private int id;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verifyPassword(String verifyPassword){
        return ((!verifyPassword.equals(this.password)) && !(password == null));
    }

    public boolean validUsername(){
        if(this.username.length() < 5 && this.username.length() > 10) return false;
        else if(!(this.username.equals(this.username.replaceAll(" ","")))) return false;
        return true;
    }

    public boolean verifyEmail(){
        return (email != null);
    }
}
