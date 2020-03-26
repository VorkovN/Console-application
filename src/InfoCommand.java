/**
 * Класс команды InfoCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class InfoCommand implements Command {
    public InfoCommand() {
        CommandExecutor.addCommand("info", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        try {
            myCollection.info();
        }catch (IndexOutOfBoundsException e){
            System.out.println("List doesn't have any elements");
        }
    }
}
