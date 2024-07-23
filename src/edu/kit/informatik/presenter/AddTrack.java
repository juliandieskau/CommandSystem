package edu.kit.informatik.presenter;

import edu.kit.informatik.model.trainsimulator.Track;
import edu.kit.informatik.model.exception.TrainsimulatorException;
import edu.kit.informatik.presenter.output.Errors;
import edu.kit.informatik.presenter.output.Messages;
import edu.kit.informatik.presenter.output.Result;
import edu.kit.informatik.presenter.output.Result.ResultType;
import edu.kit.informatik.view.CommandHandler;

public class AddTrack extends Command {

    public AddTrack(CommandHandler handler) {
        super(handler);
    }

    @Override
    public String getRegex() {
        return "addtrack";
    }

    @Override
    public Result execute(String[] input) {

        if (input.length != 3) {
            return new Result(String.format(Errors.INVALID_ARGUMENTS, 3, input.length), ResultType.FAILURE);
        }

        int x;
        int y;
        try {
            x = Integer.parseInt(input[1]);
            y = Integer.parseInt(input[2]);
        } catch (NumberFormatException e) {
            return new Result(e.getMessage(), ResultType.FAILURE);
        }
        try {
            Track t = handler.getTrainsimulator().addTrack(x, y);
            return new Result(String.format(Messages.TRACK_PLACED, t.toString()), ResultType.SUCCESS);
        } catch (TrainsimulatorException e) {
            return new Result(e.getMessage(), ResultType.FAILURE);
        }
    }
}
