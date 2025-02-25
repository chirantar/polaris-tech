package com.example.polaris.models;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderedItem {
    private Long foodItemId;
    private String itemName;
    private Integer quantity;
    private Double price;
}
