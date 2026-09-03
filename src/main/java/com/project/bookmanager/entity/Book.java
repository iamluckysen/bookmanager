package com.project.bookmanager.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor

public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private String author;

        @Column(nullable = false, unique = true)
        private String isbn;

        @Column(nullable = false, precision = 10, scale = 2)
        private BigDecimal price;

        @Column(nullable = false)
        private Integer stockQuantity;

        @Column(length = 400)
        private String description;

        private String coverImageURL;

        @Column(nullable = false)
        private String category;






}
