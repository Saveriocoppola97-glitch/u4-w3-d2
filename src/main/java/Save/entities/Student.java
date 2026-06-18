package Save.entities;

import jakarta.persistence.*;
import org.hibernate.Length;

@Entity
// Annotazione OBBLIGATORIA per tutte le Entities. Serve per creare una mappatura tra questa classe ed una tabella corrispondente nel DB
// Se abbiamo usato l'impostazione .......< property name="hibernate.hmb2ddl.auto" value="update"/> all'avvio dell'applicazione
// verrà rilevata questa Entity e verrà creata la tabella corrispondente, oppure se già esistente verrà aggiornata al bis
// Gli attributi di questa classe verranno mappati a colonne della tabella anche se non usiamo l'annotazione @Column

// N.B. Un errore molto molto comune è quello di dimenticarsi di aggiornare il persistence.xml con
// <class>Save.entities.Student</class>
// <class>Save.entities.Esempio</class>

@Table(name= "students")
public class Student {

    @Id // Annotazione obbligatoria che serve a dire che questo campo "id" sarà la chiave primaria(PK) del database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
// l'id ad ogni inserimento, con strategy Identity gli chiediamo inoltre di rendere quel campo long invece che un biginteger un
// bigserial, quindi intero grande che auto-incrementa

    private long id;

    @Column(nullable = false, length = 30)

    private String name;

    @Column(nullable = false, length = 30)
    private String surname;

    @Column(name = "student_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentType studentType; //di default gli ENUM vengono trattati come int in SQL, e si risolve con "@Enumerated(Enum.Type.STRING) "

    public Student() {} // il costruttore vuoto è obbligatorio per tutte le entities

    public Student(String name,String surname, StudentType studentType) {
        this.name = name;
        this.surname = surname;
        this.studentType = studentType;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studentType=" + studentType +
                '}';
    }
}

