/**
 * Класс команды ShowCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class ShowCommand implements Command {
    public ShowCommand() {
        CommandExecutor.addCommand("show", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        myCollection.show();
    }
}
