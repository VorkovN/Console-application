import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main класс программы
 * @author VrkovN and Kekos
 * @version 2.0
 */

public class Main {

    public static void main(String[] args) throws IOException {

        /**
         * Создание эказемпляра коллекции, адресация к меоду execute
         */

        MyCollection myCollection = new MyCollection();

        HelpCommand helpCommand = new HelpCommand();
        InfoCommand infoCommand = new InfoCommand();
        ExitCommand exitCommand = new ExitCommand();
        ShowCommand showCommand = new ShowCommand();
        SaveCommand saveCommand = new SaveCommand();
        AddCommand addCommand = new AddCommand();
        UpdateCommand updateCommand = new UpdateCommand();
        RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand();
        ClearCommand clearCommand = new ClearCommand();
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand();
        RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand();
        CountLessThanDistanceCommand countLessThanDistance = new CountLessThanDistanceCommand();
        FilterGreaterThanDistanceCommand filterGreaterThanDistance = new FilterGreaterThanDistanceCommand();
        HistoryCommand historyCommand = new HistoryCommand();
        RemoveAllByDistance removeAllByDistance = new RemoveAllByDistance();
        RemoveFirstCommand removeFirstCommand = new RemoveFirstCommand();

        /**
         * Цикл работы ввода команд
         */

        while (true) {
            System.out.println("Enter you action, use \"help\" to get the list of all commands");
            System.out.print(">>>");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String action = reader.readLine();
            if (!action.isEmpty()) {
                CommandExecutor.execute(action, myCollection);
                System.out.println();
            }
        }
    }
}
