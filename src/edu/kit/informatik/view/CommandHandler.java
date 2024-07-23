package edu.kit.informatik.view;

import edu.kit.informatik.core.ErrorOutput;
import edu.kit.informatik.core.Input;
import edu.kit.informatik.core.Output;
import edu.kit.informatik.model.trainsimulator.Trainsimulator;
import edu.kit.informatik.presenter.AddTrack;
import edu.kit.informatik.presenter.Command;
import edu.kit.informatik.presenter.Quit;
import edu.kit.informatik.presenter.output.Errors;
import edu.kit.informatik.presenter.output.Result;
import edu.kit.informatik.presenter.output.Result.ResultType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandHandler {
    private final List<Command> commands;
    private final Trainsimulator trainsimulator;
    private final Output output;
    private final Input input;
    private final ErrorOutput error;
    private boolean isRunning;

    public CommandHandler(Trainsimulator trainsimulator, Input input, Output output, ErrorOutput error) {
        commands = new ArrayList<>();

        this.trainsimulator = trainsimulator;

        isRunning = true;

        // hier werden dann alle commands registriert!:w
        commands.addAll(List.of(new Quit(this), new AddTrack(this)));

        this.output = output;
        this.input = input;
        this.error = error;
    }

    public void sessionStart() {
        while (isRunning) {
            String userInput = input.read();

            String[] splittedUserInput = userInput.split(" ", -1);

            if (splittedUserInput.length < 1) {
                error.error(Errors.INVALID_ARGUMENTS);
            }

            /*
             * 
             * Hier gibt es jetzt mehrere Möglichkeiten.
             * 
             * Einerseits kann man wie gewohnt mit null arbeiten und dann einfach überprüfen
             * ob null zurückgegeben wurde. Aber null ist eben sehr Fehleranfällig und führt
             * gerne zur NullpointerException wenn man nicht aufpasst
             * 
             * 
             * Eine andere Möglichkeit ist Optional<E> aus java.util
             * 
             * Hierbei wird einfach unsere Object in unser Optional-Object verpackt und so
             * verhindert, dass wir ausversehen auf null referenzieren.
             */

            // unsere Ausgabe
            Result r = null;

            // Das hier ist die gewohne Methode mit null
            Command command = getCommand(splittedUserInput[0]);

            if (command != null) {
                r = command.execute(splittedUserInput);
            } else {
                r = new Result(Errors.COMMAND_NOT_FOUND, ResultType.FAILURE);
            }

            // und das hier ist die Methode mit Optional

            // jetzt kurz tief luft holen, dass sieht komplizierter aus als es ist.

            // wir holen uns den Optional<Command>, bis jetzt wissen wir noch nicht ob ein
            // command gefunden wurde
            r = getCommandAsOptional(splittedUserInput[0])
                    // mit map können wir jetzt unseren command, wenn einer gefunden wurde, in etwas
                    // "umwandeln".
                    // in unserem Fall wollen wir einfach den command ausführen, damit wir unseren
                    // {@link Result} bekommen
                    .map(cmd -> cmd.execute(splittedUserInput))
                    // jetzt müssen wir nur noch den Fall betrachten wenn eben kein Command gefunden
                    // wurde.
                    // in unserem Fall wollen wir einfach ein Result erstellen, der uns einfach
                    // mitteilt, dass kein Command gefunden wurde
                    .orElse(new Result(Errors.COMMAND_NOT_FOUND, ResultType.FAILURE));

            // jetzt können wir einfach unser Result ausgeben

            if (r.wasSuccess()) {
                output.print(r.getOutput());
            } else {
                error.error(r.getOutput());
            }

        }
    }

    private Command getCommand(String name) {
        for (Command c : commands) {
            if (c.getRegex().matches(name)) {
                return c;
            }
        }
        return null;
    }

    private Optional<Command> getCommandAsOptional(String name) {
        for (Command c : commands) {
            if (c.getRegex().matches(name)) {
                // wenn wir einen command finden, verpacken wir ihn als optional
                return Optional.of(c);
            }
        }
        // sonst geben wir einfach einen empty optional zurück
        // so haben wir uns eine exception gespart und geben auch kein gefährliches null
        // zurück
        return Optional.empty();
    }

    public void quit() {
        this.isRunning = false;
    }

    public Trainsimulator getTrainsimulator() {
        return this.trainsimulator;
    }
}
