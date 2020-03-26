import java.nio.charset.Charset;

/**
 * Интерфейс комманды
 * @author VorkovN and kekos
 * @version 2.0
 */

public interface Command {
    Charset windows1251 = Charset.forName("Windows-1251");
    void execute(String arg, MyCollection myCollection);
}
