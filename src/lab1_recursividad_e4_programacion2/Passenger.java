package lab1_recursividad_e4_programacion2;

/** Datos de un pasajero. Contenedor de datos, ya esta completo. */
public class Passenger {

    private final String name;
    private final int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (edad: " + age + ")";
    }
}
