/**
 * Класс координат для хранения и обработки координат объектов
 * @author VorkovN and Kekos
 * @version 1.1
 */

public class Coordinates {

    private Float x; //Поле не может быть null
    private Double y; //Максимальное значение поля: 142, Поле не может быть null

    public Float getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x = " + getX() + "; y = " + getY();
    }
}
