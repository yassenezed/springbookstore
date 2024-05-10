package com.example.bookstorespringboot.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Collection;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @Email

    private String email;

    private String firstName;

    private String lastName;

   // @NotNull
   // @Past
   // @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;

    private String gender;

    private String address;

    private String phoneNumber;

    private Date registrationDate;

    private String role;

    @OneToMany(mappedBy = "userBook", fetch = FetchType.LAZY)
    private Collection<Book> books;

    @OneToMany(mappedBy = "userAuthor", fetch = FetchType.LAZY)
    private Collection<Author> authors;

    @OneToMany(mappedBy = "userReview", fetch = FetchType.LAZY)
    private Collection<Review> reviews;


}
