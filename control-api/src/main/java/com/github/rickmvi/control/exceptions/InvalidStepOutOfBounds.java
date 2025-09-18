package com.github.rickmvi.control.exceptions;

public class InvalidStepOutOfBounds extends RuntimeException {

    public InvalidStepOutOfBounds() {
        super(ErrorMessage.STEP_OUT_OF_BOUNDS.display());
    }

    public InvalidStepOutOfBounds(String message) {
        super(message);
    }

    public InvalidStepOutOfBounds(Throwable cause) {
        super(cause);
    }

    public InvalidStepOutOfBounds(String message, Throwable cause) {
        super(message, cause);
    }
}
