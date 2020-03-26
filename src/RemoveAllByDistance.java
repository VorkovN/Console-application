/**
 * Класс команды RemoveAllByDistance
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class RemoveAllByDistance implements Command {
    public RemoveAllByDistance() {
        CommandExecutor.addCommand("remove_all_by_distance", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        try{
            myCollection.removeAllByDistance(arg);
            System.out.println("Removed");
        }catch (NumberFormatException e) {
            System.out.println("\nWrong format of distance, please enter your command again!");
        }
    }
}
