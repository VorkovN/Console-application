/**
 * Класс команды SaveCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class SaveCommand implements Command {
    public SaveCommand() {
        CommandExecutor.addCommand("save", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        myCollection.save();
        System.out.println("Saved");
    }
}
