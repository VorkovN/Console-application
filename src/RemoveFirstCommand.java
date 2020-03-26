/**
 * Класс команды RemoveFirstCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class RemoveFirstCommand implements Command {
    public RemoveFirstCommand() {
        CommandExecutor.addCommand("remove_first", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        try {
            myCollection.removeFirst();
        }catch (NonexistentArgumentException e){
          e.getMessage();
        }
        System.out.println("First element was removed");
    }
}
