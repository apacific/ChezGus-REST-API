package io.catalyte.apacific.chezgus.exceptions;

import java.util.Date;
import java.util.List;

/**
 * This class extends the ExceptionResponse to include a list of validation errors.
 */

public class ValidationExceptionResponse extends ExceptionResponse {

    public List<String> validationErrors;

    public ValidationExceptionResponse(String error, Date timestamp, String errorMessage,
                                       List<String> validationErrors) {
        super(error, timestamp, errorMessage);
        this.validationErrors = validationErrors;
    }
}
