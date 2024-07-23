package edu.kit.informatik.presenter.output;

public final class Errors {

    private Errors() {
        throw new IllegalAccessError();
    }

    /* If command has not enough arguments */
    public static final String NOT_ENOUGH_ARGUMENTS = "Not enough arguments";
    /* if track coordinates are below 0 */
    public static final String BELOW_ZERO_ERROR = "Can't place track at positions below 0";
    /* if no command was found */
    public static final String COMMAND_NOT_FOUND = "Unknown command!";
    public static final String INVALID_ARGUMENTS = "Invalid amount of arguements. Expected %d, but got %d";
}
