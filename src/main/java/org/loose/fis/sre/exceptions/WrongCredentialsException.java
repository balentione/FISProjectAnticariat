package org.loose.fis.sre.exceptions;

public class WrongCredentialsException extends Exception{
    String username;
    String password;
    String role;

    public WrongCredentialsException(String username, String password, String role){
        super(String.format("Provided credentials are wrong!"));
        this.username=username;
        this.password=password;
        this.role=role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole() {
        return role;
    }
}
