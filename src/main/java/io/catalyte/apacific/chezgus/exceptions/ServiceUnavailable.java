package io.catalyte.apacific.chezgus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Service unavailable")
public class ServiceUnavailable extends RuntimeException {

    public ServiceUnavailable() {
    }

    public ServiceUnavailable(String message) {
        super(message);
    }

    public ServiceUnavailable(Exception e) {
        super(e.getCause());
    }
}