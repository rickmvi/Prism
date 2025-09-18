package com.github.rickmvi.control.exceptions;

public class InvalidStartIndexException extends RuntimeException {

    public InvalidStartIndexException() {
        super(ErrorMessage.START_NEGATIVE.display());
    }

    public InvalidStartIndexException(String message) {
        super(message);
    }

    public InvalidStartIndexException(int index) {
        super(ErrorMessage.INDEX_NEGATIVE.format(index));
    }
}
