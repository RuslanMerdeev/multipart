package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class UnimplementedOperationException extends RuntimeException {
  public UnimplementedOperationException() {
  }

  public UnimplementedOperationException(String message) {
    super(message);
  }

  public UnimplementedOperationException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnimplementedOperationException(Throwable cause) {
    super(cause);
  }

  public UnimplementedOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
