package com.tanaka.rest.webservices.restfulwebservices.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;
  
  //"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbjI4bWludXRlcyIsImV4cCI6MTU3OTk5NjE5NCwiaWF0IjoxNTc5MzkxMzk0fQ.pxyOUMJOzWY7DaO__uWFZofzRsh9y3NDAVGL9J68hkNb0Kk7tPEJ1i2JBOzjmEe1drR5HujryTGO8RWFPYRqbQ"

  private String username;
    private String password;

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

