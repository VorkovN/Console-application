/**
 * Класс команды ExecuteScriptCommand
 * @author VorkovN and Kekos
 * @version 2.0
 */

public class ExecuteScriptCommand implements Command {
    public ExecuteScriptCommand() {
        CommandExecutor.addCommand("execute_script", this);
    }

    /**
     * Метод исполнения консольной команды через обращение к CommandExecutor
     */

    public void execute(String arg, MyCollection myCollection){
        try {
            myCollection.executeScript(arg);
        }catch (LoopException e){
            System.out.println("\nInvalid command in file");
        }
    }
}
