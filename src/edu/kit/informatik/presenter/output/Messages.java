package edu.kit.informatik.presenter.output;

public final class Messages {

    private Messages() {
        throw new IllegalAccessError();
    }

    /* Success message */
    public static final String SUCCESS = "OK";

    /*
     * message for add track command, uses {@link String#format} to replace %s with
     * the tracks name
     */
    public static final String TRACK_PLACED = "Track %s placed!";
}
