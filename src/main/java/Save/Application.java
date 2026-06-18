package Save;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-w3-d2pu");

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
