package com.project.bookmanager.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Getter
@Setter

public class BookAddDto {
    @NotBlank(message = "title cannot be blank")
    String title;

    @NotBlank(message = "author cannot be blank")
    String author;

    @NotBlank(message = "ISBN cannot be blank")
    String isbn;

    @NotNull(message = "price cannot be blank")
    @DecimalMin(value = "0.0", inclusive = true, message = "price must be at least 0")
    BigDecimal price;

    @NotBlank(message = "category cannot be blank")
    String category;

    @NotNull(message = "stock quantity cannot be blank")
    @Min(value =  0, message = "stock quantity must be at least zero")
    int stockQuantity;

    @NotBlank(message = "description cannot be blank")
    @Size(max = 400)
    String description;

    String  coverImageURL;

}
