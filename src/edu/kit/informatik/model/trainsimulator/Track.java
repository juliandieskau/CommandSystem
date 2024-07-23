package edu.kit.informatik.model.trainsimulator;

public class Track {
    private final int x;
    private final int y;

    public Track(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.x, this.y);
    }

}
