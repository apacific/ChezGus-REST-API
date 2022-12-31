package io.catalyte.apacific.chezgus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad data")
public class BadDataResponse extends RuntimeException {

    public BadDataResponse() {
    }

    public BadDataResponse(String message) {
        super(message);
    }
}
