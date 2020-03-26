/**
 * Класс команды CountLessThanDistanceCommand
 * @author VorkovN and Kekos
 * @version 2.1
 */

public class CountLessThanDistanceCommand implements Command {
    public CountLessThanDistanceCommand() {
        CommandExecutor.addCommand("count_less_than_distance", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection) {
        try{
            myCollection.countLessThanDistance(arg);
        }catch (NumberFormatException e) {
            System.out.println("\nWrong format of distance, please enter your command again!");
        }
    }
}
