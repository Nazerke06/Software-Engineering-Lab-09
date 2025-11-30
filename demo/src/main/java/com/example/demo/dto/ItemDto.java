package com.example.demo.dto;


import com.example.demo.entity.Country;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDto {

    @Id
    private Long id;
    private String itemName;
    private int itemPrice;
    private int quantity;
//    private Country manufacturer;
}
