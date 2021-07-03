package com.example.WeTutor.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "LOCATION_TBL")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", unique = true, nullable = false)
    private String location_id;
    private String name;
}
