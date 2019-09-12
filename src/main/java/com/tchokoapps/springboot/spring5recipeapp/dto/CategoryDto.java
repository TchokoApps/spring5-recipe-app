package com.tchokoapps.springboot.spring5recipeapp.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String description;
}
