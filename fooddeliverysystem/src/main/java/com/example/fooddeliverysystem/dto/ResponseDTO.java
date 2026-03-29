package com.example.fooddeliverysystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private int status;
    private String message;
    private Object data;
}