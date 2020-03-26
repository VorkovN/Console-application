import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс команды CommandExecutor
 * @author VorkovN and Kekos
 * @version 2.1
 */

public class CommandExecutor {
    private static Map<String, Command> commands = new HashMap<>();
    static ArrayList<String> history = new ArrayList<>();

    /**
     * Метод создания истории ввода команд
     * @param command - имя введённой ранее комады
     */

    static public void historyList(String command){
        if(history.size() > 6) {
            history.remove(0);
        }
        history.add(command);
    }

    /**
     * Метод получения команды
     * @return возвращает объект (команду)
     */

    private static Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Метод добавления команды в HashMap
     * @param commandName имя команды
     * @param command Объект команды
     */

    public static void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Метод исполнения команды, подающейся на ввод
     */

    public static void execute(String action, MyCollection myCollection){

        String[] actionParts = action.split(" ");
        if (action.isEmpty()) {
            return;
        }
        if (actionParts.length == 1) {
            Command command = commands.get(actionParts[0]);
            if (command != null) {
                historyList(actionParts[0]);
                command.execute(null, myCollection);
            } else {
                System.out.println("Command doesn't exist");
            }
        } else if (actionParts.length == 2) {
            Command command = commands.get(actionParts[0]);
            String arg = actionParts[1];
            if (command != null) {
                historyList(actionParts[0]);
                command.execute(arg, myCollection);
            } else {
                System.out.println("Command doesn't exist");
            }
        } else {
            System.out.println("Wrong command input");
        }
    }

    public ArrayList<String> getHistory() {
        return history;
    }
}