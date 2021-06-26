package com.example.WeTutor.entities;

import javax.persistence.*;

public class Parent extends Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id", unique = true, nullable = false)
    private String parent_id;
    private String locationRadius;

    public Parent(){
        roleName = "parent";
    }
}
