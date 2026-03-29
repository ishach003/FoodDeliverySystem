package com.example.fooddeliverysystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private int status;
    private String message;
    private Object data;
}