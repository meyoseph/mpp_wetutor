package com.example.WeTutor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private String user_id;
    private String userName;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    @ManyToMany(mappedBy="users")
    @JsonManagedReference
    private List<Role> roles = new ArrayList<>();

    public User(String userName, String password, String email,Role role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(Role role) {
        this.roles.add(role);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }

//    public List<Rating> getRatings() {
//        return ratings;
//    }

//    public void setRatings(List<Rating> ratings) {
//        this.ratings = ratings;
//    }

//
//    public void setShow(Show show) {
//        this.show.add(show);
//    }
}