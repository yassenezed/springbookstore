package com.example.bookstorespringboot.dao.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //so I should add a category to a book and that's it  Response ="yes"?
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Category> categories = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> users = new ArrayList<>();
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private String description;
    private float price;
    private double averageRating;

    private String amazonLink;
    @ManyToOne
    private Author author;
    @ManyToOne
    private User userBook;
    @OneToMany(mappedBy = "bookReview")
    private Collection<Review> reviews;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;

}
