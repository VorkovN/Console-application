/**
 * Класс команды FilterGreaterThanDistanceCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class FilterGreaterThanDistanceCommand implements Command {
    public FilterGreaterThanDistanceCommand() {
        CommandExecutor.addCommand("filter_greater_than_distance", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        try{
            myCollection.filterGreaterThanDistance(arg);
        }catch (NumberFormatException e) {
            System.out.println("\nWrong format of distance, please enter your command again!");
        }
    }
}
