/**
 * Класс команды UpdateCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class UpdateCommand implements Command {
    public UpdateCommand() {
        CommandExecutor.addCommand("update", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        try{
            myCollection.update(arg);
        }catch (NonexistentArgumentException e){
            System.out.println("\nNonexistent argument id, please enter your command again!");
        }catch (NumberFormatException e) {
            System.out.println("\nWrong format of id, please enter your command again!");
        }
    }
}
