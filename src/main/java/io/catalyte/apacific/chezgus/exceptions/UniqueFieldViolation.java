package io.catalyte.apacific.chezgus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Data already in use")
public class UniqueFieldViolation extends RuntimeException {

    public UniqueFieldViolation() {
    }

    public UniqueFieldViolation(String message) {
        super(message);
    }
}

