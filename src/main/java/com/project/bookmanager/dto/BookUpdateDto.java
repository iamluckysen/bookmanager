package com.project.bookmanager.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class BookUpdateDto {

    String title;

    String author;

    @DecimalMin(value = "0.0", inclusive = true, message = "price must be at least 0")
    BigDecimal price;

    String category;

    @Min(value = 0, message = "stock quantity must be at least zero")
    Integer stockQuantity;

    @Size(max = 400)
    String description;

    String coverImageURL;
}