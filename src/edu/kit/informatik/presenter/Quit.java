package edu.kit.informatik.presenter;

import edu.kit.informatik.presenter.output.Errors;
import edu.kit.informatik.presenter.output.Messages;
import edu.kit.informatik.presenter.output.Result;
import edu.kit.informatik.presenter.output.Result.ResultType;
import edu.kit.informatik.view.CommandHandler;

public class Quit extends Command {
    private static final String REGEX = "quit";

    public Quit(CommandHandler handler) {
        super(handler);
    }

    @Override
    public String getRegex() {
        return Quit.REGEX;
    }

    @Override
    public Result execute(String[] input) {
        if (input.length != 1) {
            return new Result(String.format(Errors.INVALID_ARGUMENTS, 1, input.length), ResultType.FAILURE);
        }
        handler.quit();
        return new Result(Messages.SUCCESS, ResultType.SUCCESS);
    }
}
