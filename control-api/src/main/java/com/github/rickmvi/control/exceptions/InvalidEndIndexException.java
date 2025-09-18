package com.github.rickmvi.control.exceptions;

import org.jetbrains.annotations.NotNull;

public class InvalidEndIndexException extends RuntimeException {

    public InvalidEndIndexException() {
        super(ErrorMessage.END_GREATER_THAN_LENGTH.display());
    }

    public InvalidEndIndexException(
            int startIndex,
            int endIndex
    ) {
        super(ErrorMessage.END_LESS_THAN_START.format(endIndex, startIndex));
    }

    public InvalidEndIndexException(
            int startIndex,
            int endIndex,
            @NotNull ErrorMessage errorMessage
    ) {
        super(errorMessage.format(endIndex, startIndex));
    }

    public InvalidEndIndexException(int index) {
        super(ErrorMessage.END_GREATER_THAN_LENGTH.format(index));
    }
}
