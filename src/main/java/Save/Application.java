package Save;

import Save.dao.StudentsDAO;
import Save.entities.Student;
import Save.entities.StudentType;
import Save.exception.NotFoundException;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-w3-d2pu");

    public static void main(String[] args) {
        System.out.println("Hello World!");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StudentsDAO StudentsDAO=  new StudentsDAO(entityManager);

        Student Aldo = new Student("Aldo", "Baglio", StudentType.FULLTIME);
        Student Giovanni = new Student("Giovanni", "Storti", StudentType.PARTTIME);
        Student Giacomo = new Student("Giacomo", "Poretti", StudentType.PARTTIME);

        StudentsDAO.save(Aldo);
        StudentsDAO.save(Giovanni);
        StudentsDAO.save(Giacomo);

        try {
            Student found = StudentsDAO.findById(2);
            System.out.println("trovato");
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            StudentsDAO.findByIdAndDelete(2);
        } catch (NotFoundException ex){
            System.out.println(ex.getMessage());
        }



    }
}
