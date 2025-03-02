package com.eager2code.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {


    private Long id;

    private String name;

    private String description;

    private int price;

    private int durationInMinutes;

    private  Long categoryId;

    private Long salonId;

    private String image;
}
