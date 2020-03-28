/**
 * Класс команды AddCommand
 * @author VorkovN and Kekos
 * @version 2.1
 */

public class AddCommand implements Command{
    public AddCommand() {
        CommandExecutor.addCommand("add", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        myCollection.add();
        System.out.println("Route was added to list");
    }
}
