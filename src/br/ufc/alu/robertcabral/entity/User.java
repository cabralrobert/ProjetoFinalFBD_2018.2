package br.ufc.alu.robertcabral.entity;

public class User {
 
    String user, password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }    
    
    public String getUser() {
        return user;
    }
    
    public String getPassword() {
        return password;
    }
    
}
