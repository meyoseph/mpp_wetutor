package com.example.WeTutor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private String id;
    private String userName;
    private String password;
    private String email;

    @ManyToMany(mappedBy="users")
    @JsonManagedReference
    private List<Role> roles = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Profile profile;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name="feedback_id", referencedColumnName = "feedback_id")
    @JsonBackReference
    private Feedback parentFeedbacks;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name="feedback_id", referencedColumnName = "feedback_id")
    @JsonBackReference
    private Feedback tutorFeedbacks;

    public User(String userName, String password, String email,Role role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles.add(role);
        this.profile = null;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(roles, user.roles) && Objects.equals(profile, user.profile) && Objects.equals(parentFeedbacks, user.parentFeedbacks) && Objects.equals(tutorFeedbacks, user.tutorFeedbacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email, roles, profile, parentFeedbacks, tutorFeedbacks);
    }
}