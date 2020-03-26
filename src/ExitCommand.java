/**
 * Класс команды ExitCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class ExitCommand implements Command {
    public ExitCommand() {
        CommandExecutor.addCommand("exit", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        myCollection.exit();
    }
}
