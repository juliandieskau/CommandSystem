package edu.kit.informatik;

import edu.kit.informatik.core.ErrorOutput;
import edu.kit.informatik.core.Input;
import edu.kit.informatik.core.Output;
import edu.kit.informatik.model.trainsimulator.Trainsimulator;
import edu.kit.informatik.view.CommandHandler;

/**
 * 
 * Dieses Programm ist im Tutorium 13. am 15.Februar entstanden.
 * 
 * Ich garantiere nicht für die Richtigkeit/Fehlerfreiheit des Programms.
 * Für entstanden Schaden übernehme ich keine Verantwortung.
 * Das implementieren und verwenden geschieht nach eigenem Ermessen.
 * 
 * 
 */
public final class Application {

    private static final Output OUTPUT = new Output(){
        @Override
        public void print(String message) {
            Terminal.printLine(message);
        }
    };

    private static final Input INPUT = new Input() {
        @Override
        public String read() {
            return Terminal.readLine();
        }
    };

    private static final ErrorOutput ERROR = new ErrorOutput() {
        @Override
        public void error(String message) {
            Terminal.printError(message);
        }
    };

    private Application() {
        throw new IllegalAccessError();
    }

    public static void main(String[] args) {
        Trainsimulator t = new Trainsimulator();
        CommandHandler handler = new CommandHandler(t, INPUT, OUTPUT, ERROR);
        handler.sessionStart();
    }
}
