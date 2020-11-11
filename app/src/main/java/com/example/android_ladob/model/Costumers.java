package com.example.android_ladob.model;

public class Costumers {

    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String password;

    public Costumers(String name, String email, String telephone, String password) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
