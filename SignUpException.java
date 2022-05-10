package org.loose.fis.sre.exceptions;

public class SignUpException extends Exception{
    private String username;
    private String password;
    private String role;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;

    public SignUpException(String username, String password, String role, String first_name, String last_name, String email, String phone) {
        super(String.format("Please fill in all the informations!"));
        this.username = username;
        this.password = password;
        this.role = role;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
    }
}
