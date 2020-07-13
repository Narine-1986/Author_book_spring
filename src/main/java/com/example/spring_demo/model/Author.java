package com.example.spring_demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="author")

public class Author {

    @Id
    @GeneratedValue()
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String bio;
    @Column
    private String profilePic;
}
