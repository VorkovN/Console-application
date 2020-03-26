/**
 * Класс обрабатываемого исключения UnacceptableNumberException
 * @author VorkovN and Kekos
 * @version 1.0
 */

public class UnacceptableNumberException extends NumberFormatException {
    public UnacceptableNumberException() {
        super("\n Unacceptable value, please enter you value again");
    }
}
