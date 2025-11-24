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
    private String name;
    private int price;
    private int quantity;
    private Country manufacturer;
}
