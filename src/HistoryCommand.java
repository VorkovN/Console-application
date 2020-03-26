/**
 * Класс команды HistoryCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class HistoryCommand implements Command {
    public HistoryCommand() {
        CommandExecutor.addCommand("history", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        myCollection.history();
    }
}
