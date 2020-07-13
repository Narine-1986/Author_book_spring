package com.example.spring_demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book")

public class Book {
    @Id
    @GeneratedValue()
    private int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private double price;
//    @Column
//    private int authorId;
    @ManyToOne           //(fetch = FetchType.LAZY)
    private Author author;

}
