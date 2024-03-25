package com.javaspringjwt.javaspringjwt.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GenericException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    private ErrorCode errorCode;
}
