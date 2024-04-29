package com.example.bookstorespringboot.dao.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String genre;
    private String author;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private String description;
    private String price;
}
