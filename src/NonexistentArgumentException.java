/**
 * Класс обрабатываемого исключения NonexistentArgumentException
 * @author VorkovN and Kekos
 * @version 1.0
 */

public class NonexistentArgumentException extends NumberFormatException {
    public NonexistentArgumentException() {
        super("\n Incorrect value, please enter you value again");
    }
}
