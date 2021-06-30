package com.example.WeTutor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "TUTOR_TBL")
public class Tutor extends Role{

    private String educationLevel;

    public Tutor(){
        this.roleName = "tutor";
        isActive = false;
    }

}
