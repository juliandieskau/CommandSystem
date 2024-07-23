package edu.kit.informatik.presenter;

import edu.kit.informatik.presenter.output.Result;
import edu.kit.informatik.view.CommandHandler;

public abstract class Command {
    protected final CommandHandler handler;

    protected Command(CommandHandler handler) {
        this.handler = handler;
    }

    public abstract String getRegex();

    public abstract Result execute(String[] input);
}
