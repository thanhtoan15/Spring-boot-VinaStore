package com.vinastore.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBodyServer {

    private Integer statusCode;

    private String message;

    private Object payload;
}
