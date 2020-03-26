/**
 * Класс команды ClearCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

class ClearCommand implements Command {
    public ClearCommand() {
        CommandExecutor.addCommand("clear", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        myCollection.clear();
        System.out.println("List was cleared");
    }
}