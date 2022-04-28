package com.knagmed.clinic.appuser.exceptions;

public class PasswordAreDifferentException extends RuntimeException {
  public PasswordAreDifferentException(String message) {
    super(message);
  }
}
