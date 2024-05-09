package com.example.bookstorespringboot.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name;
    private String biography;
    private Date birthDate;
    private String nationality;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Collection<Book> books;
    @ManyToOne
    private User userAuthor;
}
