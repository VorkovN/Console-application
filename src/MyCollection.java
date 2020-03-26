/**
 * Класс коллекции MyCollection
 * ArrayList Rout-ов, хранит все методы по работе с ними
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
        try (Reader reader = new FileReader("C:\\JV\\first console app\\src\\laba5.json")) {
            BufferedReader bufferedReader = new BufferedReader(reader);//я не хотел этого делать, меня заставилт :(
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(bufferedReader);
            JSONObject route = (JSONObject) jsonObject.get("Route");
            newRoute.setId(0);
            newRoute.setName((String) route.get("name"));
            JSONObject coordinates = (JSONObject) route.get("Coordinates");
            newRoute.setX(((Double)  coordinates.get("x")).floatValue());
            newRoute.setY((Double) coordinates.get("y"));
            //java.time.LocalDate Date = (java.time.LocalDate) jsonObject.get("Date");
            JSONObject location1 = (JSONObject) route.get("Location1");
            newRoute.setXl1((Long) location1.get("xl1"));
            newRoute.setYl1((Double) location1.get("yl1"));
            newRoute.setZl1((long) location1.get("zl1"));
            JSONObject location2 = (JSONObject) route.get("Location2");
            newRoute.setXl2((int)(long)location2.get("xl2"));
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

    public void help(){
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

    };

    public void info()throws IndexOutOfBoundsException{
        System.out.println("type: Roue\n"
                        + "Дата инициализации: " + arr.get(0).getDate() + '\n'
                        +"Количество элементов: " + arr.size());
    }

    public void show(){
        for(int i=0; i<arr.size(); i++){
            System.out.println(arr.get(i));
        }
    }

    public void add() {
        try {
            Route newRoute = initialization();
            arr.add(newRoute);
        }catch (NumberFormatException e){
            System.out.println("\nWrong input, please enter your values again!");
            add();
        }
    }

    public void update(String arg) throws NumberFormatException, NonexistentArgumentException{
        int id = Integer. parseInt(arg);
        if (id > arr.size()-1){
            throw new NonexistentArgumentException();
        }
        try {
        Route newRoute = initialization();
        arr.set(id, newRoute);
        }catch (NumberFormatException e){
            System.out.println("\nWrong input, please enter your values again!");
            add();
        }
    }

    public void removeById(String arg) throws NumberFormatException, NonexistentArgumentException{
        int id = Integer. parseInt(arg);
        if (id > arr.size()-1){
            throw new NonexistentArgumentException();
        }
        arr.remove(id);
    }

    public void clear(){
        arr.clear();
    }

    public void save(){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\JV\\first console app\\src\\output.json"))) {
            for (int i=0; i<arr.size(); i++){
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

    public void executeScript(String arg) throws LoopException{
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\JV\\first console app\\src\\" + arg))) {
            String line;
            while((line = bufferedReader.readLine()) != null){
                if (line.split(" ")[0].equals("execute_script")){
                    throw new LoopException();
                }
                System.out.println(line.split(" ")[0]);
                CommandExecutor.execute(line, this);
            }
        } catch (IOException e) {
            System.out.println("File not found, please, input existent file");
        }
    }


    public void exit(){
        System.exit(0);
    }

    public void removeFirst()throws NonexistentArgumentException{
        if (arr.size() < 1){
            throw new NonexistentArgumentException();
        }
        arr.remove(0);
    }

    public void removeGreater(String arg) throws NumberFormatException, NonexistentArgumentException{
        int id = Integer.parseInt(arg);
        if (id > arr.size()-1){
            throw new NonexistentArgumentException();
        }
        for(int i=id; i<arr.size(); i++) {
            arr.remove(id);
        }
    }

    public void history(){
        for(int i = 0; i < CommandExecutor.history.size(); i++ ){
            System.out.println(CommandExecutor.history.get(i));
        }
    };

    public void removeAllByDistance(String arg) throws NumberFormatException{
        int distance =Integer.parseInt(arg);
        for(int i=0; i<arr.size(); i++) {
            if(arr.get(i).getDistance() == distance){
                arr.remove(i);
            }
        }
    }

    public void countLessThanDistance(String arg) throws NumberFormatException{
        int distance =Integer.parseInt(arg);
        int k =0;
        for(int i=0; i<arr.size(); i++) {
            if (arr.get(i).getDistance() < distance) {
                k++;
            }
        }
        System.out.println("Число элементов: "+ k);
    }

    public void filterGreaterThanDistance(String arg) throws NumberFormatException{
        int distance =Integer.parseInt(arg);
        for(int i=0; i<arr.size(); i++) {
            if (arr.get(i).getDistance() > distance) {
                System.out.println(arr.get(i));
            }
        }
    }

    public Route initialization() throws NumberFormatException, UnacceptableNumberException {
        Route newRoute = new Route();
        Scanner sc = new Scanner(System.in);

        newRoute.setId(arr.size());
        System.out.println("id = " + arr.size());

        System.out.print("name = ");
        newRoute.setName(sc.nextLine());

        System.out.print("x = ");
        Float x = Float.parseFloat(sc.nextLine());
        if (!((1.4e-45f<Math.abs(x)) && (Math.abs(x)<3.4e+38f))){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setX(x);
        }

        System.out.print("y = ");
        Double y = Double.parseDouble(sc.nextLine());
        if (!(( 4.9e-324<Math.abs(y)) && (Math.abs(y)<142))){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setY(y);
        }

        LocalDate date = LocalDate.now();
        newRoute.setDate(date);
        System.out.println("date = " + date);

        System.out.print("xl1 = ");
        Long xl1 = Long.parseLong(sc.nextLine());
        if (!(( -9223372036854775808L<xl1) && (xl1<9223372036854775807L))){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setXl1(xl1);
        }

        System.out.print("yl1 = ");
        Double yl1 = Double.parseDouble(sc.nextLine());
        if (!(( 4.9e-324<Math.abs(yl1)) && (Math.abs(yl1)<1.7e+308))){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setYl1(yl1);
        }

        System.out.print("zl1 = ");
        long zl1 = Long.parseLong(sc.nextLine());
        if (!(( -9223372036854775808L<zl1) && (zl1<9223372036854775807L))){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setZl1(zl1);
        }

        System.out.print("xl2 = ");
        int xl2 = Integer.parseInt(sc.nextLine());
        if (!(( -2147483648<xl2) && (xl2<2147483647))){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setXl2(xl2);
        }

        System.out.print("yl2 = ");
        Float yl2 = Float.parseFloat(sc.nextLine());
        if (!((1.4e-45f<Math.abs(yl2)) && (Math.abs(yl2)<3.4e+38f))){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setYl2(yl2);
        }


        System.out.print("namel2 = ");
        String name = sc.nextLine();
        if (name.length()>968){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setName(name);
        }

        System.out.print("distance = ");
        float dist = Float.parseFloat(sc.nextLine());
        if (!((1<Math.abs(dist)) && (Math.abs(dist)<3.4e+38f))){
            throw new UnacceptableNumberException();
        }else {
            newRoute.setDistance(dist);
        }

        return newRoute;
    }
}
