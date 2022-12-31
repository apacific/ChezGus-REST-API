package io.catalyte.apacific.chezgus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Content not found")
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() {
    }
    public ResourceNotFound(String message) {
        super(message);
    }
}
