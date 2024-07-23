package edu.kit.informatik.model.trainsimulator;

import edu.kit.informatik.model.exception.TrainsimulatorException;
import edu.kit.informatik.presenter.output.Errors;

public final class Trainsimulator {

    public Track addTrack(int x, int y) {
        if (x < 0 || y < 0) {
            throw new TrainsimulatorException(Errors.BELOW_ZERO_ERROR);
        }
        // ...
        return new Track(x, y);
    }
}
