package ru.praktikum.s7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAListOfOrdersRequest {
    private Integer courierId;
    private String nearestStation;
    private Integer limit;
    private Integer page;
}
