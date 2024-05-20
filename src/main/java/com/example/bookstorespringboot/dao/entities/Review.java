    package com.example.bookstorespringboot.dao.entities;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.Date;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Review {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private int rating;

        private String comment;

        private Date timestamp;

        @ManyToOne
        private User userReview;

        @ManyToOne
        private Book bookReview;


        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;
        @Temporal(TemporalType.TIMESTAMP)
        private Date modifiedAt;
    }
