/**
 * Класс коллекции MyCollection
 * ArrayList Rout-ов, хранит все методы по работе с ними
 *
 * @author VorkovN and Kekos
 * @version 2.1
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCollection {

    private ArrayList<Route> arr = new ArrayList<Route>();

    public MyCollection() {
        Route newRoute = new Route();
        try (Reader reader = new FileReader("laba5.json")) {
            BufferedReader bufferedReader = new BufferedReader(reader);//я не хотел этого делать, меня заставилт :(
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(bufferedReader);
            JSONObject route = (JSONObject) jsonObject.get("Route");
            newRoute.setId(0);
            newRoute.setName((String) route.get("name"));
            JSONObject coordinates = (JSONObject) route.get("Coordinates");
            newRoute.setX(((Double) coordinates.get("x")).floatValue());
            newRoute.setY((Double) coordinates.get("y"));
            //java.time.LocalDate Date = (java.time.LocalDate) jsonObject.get("Date");
            JSONObject location1 = (JSONObject) route.get("Location1");
            newRoute.setXl1((Long) location1.get("xl1"));
            newRoute.setYl1((Double) location1.get("yl1"));
            newRoute.setZl1((long) location1.get("zl1"));
            JSONObject location2 = (JSONObject) route.get("Location2");
            newRoute.setXl2((int) (long) location2.get("xl2"));
            newRoute.setYl2(((Double) location2.get("yl2")).floatValue());
            newRoute.setNamel2((String) location2.get("namel2"));
            newRoute.setDistance((float) (Long) route.get("distance"));
            LocalDate date = LocalDate.now();
            newRoute.setDate(date);
            arr.add(newRoute);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public void help() {
        System.out.println("help : вывести справку по доступным командам \n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении \n" +
                "add {element} : добавить новый элемент в коллекцию \n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному \n" +
                "remove_by_id id : удалить элемент из коллекции по его id \n" +
                "clear : очистить коллекцию \n" +
                "save : сохранить коллекцию в файл \n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. \n" +
                "remove_first : удалить первый элемент из коллекции \n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный \n" +
                "history : вывести последние 7 команд (без их аргументов) \n" +
                "remove_all_by_distance distance : удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному \n" +
                "count_less_than_distance distance : вывести количество элементов, значение поля distance которых меньше заданного \n" +
                "filter_greater_than_distance distance : вывести элементы, значение поля distance которых больше заданного \n" +
                "exit : завершить программу (без сохранения в файл) \n");

    }

    ;

    public void info() throws IndexOutOfBoundsException {
        System.out.println("type: Roue\n"
                + "Дата инициализации: " + arr.get(0).getDate() + '\n'
                + "Количество элементов: " + arr.size());
    }

    public void show() {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }

    public void add() {
        try {
            Route newRoute = initialization();
            arr.add(newRoute);
        } catch (NumberFormatException e) {
            System.out.println("\nWrong input, please enter your values again!");
            add();
        }
    }

    public void update(String arg) throws NumberFormatException, NonexistentArgumentException {
        int id = Integer.parseInt(arg);
        if (id > arr.size() - 1) {
            throw new NonexistentArgumentException();
        }
        try {
            Route newRoute = initialization();
            arr.set(id, newRoute);
        } catch (NumberFormatException e) {
            System.out.println("\nWrong input, please enter your values again!");
            add();
        }
    }

    public void removeById(String arg) throws NumberFormatException, NonexistentArgumentException {
        int id = Integer.parseInt(arg);
        if (id > arr.size() - 1) {
            throw new NonexistentArgumentException();
        }
        arr.remove(id);
    }

    public void clear() {
        arr.clear();
    }

    public void save() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.json"))) {
            for (int i = 0; i < arr.size(); i++) {
                JSONObject out = new JSONObject();
                out.put("id", arr.get(i).getId());
                out.put("name", arr.get(i).getName());
                JSONObject coordinates = new JSONObject();
                coordinates.put("x", arr.get(i).getX());
                coordinates.put("y", arr.get(i).getY());
                out.put("Coordinates", coordinates);
                JSONObject location1 = new JSONObject();
                location1.put("xl1", arr.get(i).getXl1());
                location1.put("yl1", arr.get(i).getYl1());
                location1.put("zl1", arr.get(i).getZl1());
                out.put("Location1", location1);
                JSONObject location2 = new JSONObject();
                location2.put("xl2", arr.get(i).getXl2());
                location2.put("yl2", arr.get(i).getYl2());
                location2.put("namel2", arr.get(i).getNamel2());
                out.put("Location2", location2);
                out.put("distance", arr.get(i).getDistance());
                bufferedWriter.write(out.toJSONString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeScript(String arg) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arg))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.split(" ")[0].equals("srcexecute_script")) {
                    System.out.println(line);
                    CommandExecutor.execute(line, this);
                } else {
                    System.out.println("Invalid command");
                }
            }
        } catch (IOException e) {
            System.out.println("File not found, please, input existent file");
        }
    }


    public void exit() {
        System.exit(0);
    }

    public void removeFirst() throws NonexistentArgumentException {
        if (arr.size() < 1) {
            throw new NonexistentArgumentException();
        }
        arr.remove(0);
    }

    public void removeGreater(String arg) throws NumberFormatException, NonexistentArgumentException {
        int id = Integer.parseInt(arg);
        if (id > arr.size() - 1) {
            throw new NonexistentArgumentException();
        }
        for (int i = id; i < arr.size(); i++) {
            arr.remove(id);
        }
    }

    public void history() {
        for (int i = 0; i < CommandExecutor.history.size(); i++) {
            System.out.println(CommandExecutor.history.get(i));
        }
    }

    ;

    public void removeAllByDistance(String arg) throws NumberFormatException {
        int distance = Integer.parseInt(arg);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getDistance() == distance) {
                arr.remove(i);
            }
        }
    }

    public void countLessThanDistance(String arg) throws NumberFormatException {
        int distance = Integer.parseInt(arg);
        int k = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getDistance() < distance) {
                k++;
            }
        }
        System.out.println("Число элементов: " + k);
    }

    public void filterGreaterThanDistance(String arg) throws NumberFormatException {
        int distance = Integer.parseInt(arg);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getDistance() > distance) {
                System.out.println(arr.get(i));
            }
        }
    }

    public void id(Route newRoute) {
        newRoute.setId(arr.size());
        System.out.println("(Integer) id = " + arr.size());
    }

    public void name(Route newRoute, Scanner sc) {
        try {
            System.out.print("(String) name = ");
            newRoute.setName(sc.nextLine());
        } catch (NumberFormatException e) {
            name(newRoute, sc);
        }
    }

    public void x(Route newRoute, Scanner sc) {
        try{
            System.out.print("(Float) x = ");
            Float x = Float.parseFloat(sc.nextLine());
            if (!((Float.MIN_VALUE < Math.abs(x)) && (Math.abs(x) < Float.MAX_VALUE))) {
                x(newRoute, sc);
                System.out.println("Wrong input");
            } else {
                newRoute.setX(x);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            x(newRoute, sc);
        }
    }

    public void y(Route newRoute, Scanner sc){
        try{
            System.out.print("(Double) y = ");
            Double y = Double.parseDouble(sc.nextLine());
            if (!((Double.MIN_VALUE < Math.abs(y)) && (Math.abs(y) < Double.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setY(y);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            y(newRoute, sc);
        }
    }

    public void date(Route newRoute) {
        LocalDate date = LocalDate.now();
        newRoute.setDate(date);
        System.out.println("(LocalDatedate) = " + date);
    }

    public void xl1(Route newRoute, Scanner sc) {
        try{
            System.out.print("(Long) xl1 = ");
            Long xl1 = Long.parseLong(sc.nextLine());
            if (!((Long.MIN_VALUE < xl1) && (xl1 < Long.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setXl1(xl1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            xl1(newRoute, sc);
        }
    }

    public void yl1(Route newRoute, Scanner sc) {
        try{
            System.out.print("(Double) yl1 = ");
            Double yl1 = Double.parseDouble(sc.nextLine());
            if (!((Double.MIN_VALUE < Math.abs(yl1)) && (Math.abs(yl1) < Double.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setYl1(yl1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            yl1(newRoute, sc);
        }
    }

    public void zl1(Route newRoute, Scanner sc) {
        try{
            System.out.print("(long) zl1 = ");
            long zl1 = Long.parseLong(sc.nextLine());
            if (!((Long.MIN_VALUE < zl1) && (zl1 < Long.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setZl1(zl1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            zl1(newRoute, sc);
        }
    }

    public void xl2(Route newRoute, Scanner sc) {
        try{
            System.out.print("(int) xl2 = ");
            int xl2 = Integer.parseInt(sc.nextLine());
            if (!((Integer.MIN_VALUE < xl2) && (xl2 < Integer.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setXl2(xl2);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            xl2(newRoute, sc);
        }
    }

    public void yl2(Route newRoute, Scanner sc) {
        try{
            System.out.print("(Float) yl2 = ");
            Float yl2 = Float.parseFloat(sc.nextLine());
            if (!((Float.MIN_VALUE < Math.abs(yl2)) && (Math.abs(yl2) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setYl2(yl2);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            yl2(newRoute, sc);
        }
    }

    public void namel2(Route newRoute, Scanner sc) {
        try{
            System.out.print("(String) namel2 = ");
            String name = sc.nextLine();
            if (name.length() > 968) {
                throw new UnacceptableNumberException();
            } else {
                System.out.println(name);
                newRoute.setNamel2(name);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            namel2(newRoute, sc);
        }
    }

    public void distance(Route newRoute, Scanner sc) {
        try{
            System.out.print("(float) distance = ");
            float dist = (Float.parseFloat(sc.nextLine()));
            if (!((1 < Math.abs(dist)) && (Math.abs(dist) < Float.MAX_VALUE))) {
                throw new UnacceptableNumberException();
            } else {
                newRoute.setDistance(dist);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            distance(newRoute, sc);
        }
    }

    public Route initialization() {
        Route newRoute = new Route();
        Scanner sc = new Scanner(System.in);

        date(newRoute);
        id(newRoute);
        name(newRoute, sc);
        x(newRoute, sc);
        y(newRoute, sc);
        xl1(newRoute, sc);
        yl1(newRoute, sc);
        zl1(newRoute, sc);
        xl2(newRoute, sc);
        yl2(newRoute, sc);
        namel2(newRoute, sc);
        distance(newRoute, sc);
        return newRoute;
    }
}
