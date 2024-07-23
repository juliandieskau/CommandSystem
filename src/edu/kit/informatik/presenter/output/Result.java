package edu.kit.informatik.presenter.output;

public class Result {
    private final String output;
    private final ResultType type;

    public Result(String output, ResultType type) {
        this.output = output;
        this.type = type;
    }

    public boolean wasSuccess() {
        return ResultType.SUCCESS.equals(type);
    }

    public String getOutput() {
        return this.output;
    }

    public ResultType getType() {
        return this.type;
    }

    public enum ResultType {
        FAILURE, SUCCESS
    }
}
