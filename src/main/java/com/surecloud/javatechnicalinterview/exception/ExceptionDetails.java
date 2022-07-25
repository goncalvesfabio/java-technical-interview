package com.surecloud.javatechnicalinterview.exception;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionDetails {

    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
}
