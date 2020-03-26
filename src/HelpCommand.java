/**
 * Класс команды HelpCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class HelpCommand implements Command {

    public HelpCommand() {
        CommandExecutor.addCommand("help", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        myCollection.help();
    }
}