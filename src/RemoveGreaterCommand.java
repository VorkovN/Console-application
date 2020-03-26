/**
 * Класс команды RemoveGreaterCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class RemoveGreaterCommand implements Command {
    public RemoveGreaterCommand() {
        CommandExecutor.addCommand("remove_greater", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        try{
            myCollection.removeGreater(arg);
            System.out.println("Removed");
        }catch (NonexistentArgumentException e){
            System.out.println("\nNonexistent argument id, please enter your command again!");
        }catch (NumberFormatException e) {
            System.out.println("\nWrong format of id, please enter your command again!");
        }
    }
}
