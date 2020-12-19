package com.hemanth.springsecurity.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SpringSecurityException extends RuntimeException {
    public SpringSecurityException(String message) {
        super(message);
    }
}
