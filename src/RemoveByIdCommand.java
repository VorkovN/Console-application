/**
 * Класс команды RemoveByIdCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class RemoveByIdCommand implements Command {
    public RemoveByIdCommand() {
        CommandExecutor.addCommand("remove_by_id", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        try{
            myCollection.removeById(arg);
            System.out.println("Element was removed");
        }catch (NonexistentArgumentException e){
            System.out.println("\nNonexistent argument id, please enter your command again!");
        }catch (NumberFormatException e) {
            System.out.println("\nWrong format of id, please enter your command again!");
        }
    }
}
