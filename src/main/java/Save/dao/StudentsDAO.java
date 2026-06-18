package Save.dao;
import Save.entities.Student;
import Save.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
// DAO termine tecnico che significa data access Object, è un astrazione, siccome le interazioni con il DB richiedono parecchie righe di codice non semplici,
//creiamo questa classe che fornisce metodi semplici da usare nel main nascondendo un po queste complessità che inseriremo qui

public class StudentsDAO {

    private final EntityManager entityManager;
    // Tutti i metodi di questo DAO avranno bisogno di utilizzare 1'EntityManager poichè é l'oggetto che mi consente
    // di salvare, cancellare, leggere, sincronizzarmi col DB. Siccome l'oggetto entity manager viene creato nel main
    // è comodo passarlo come parametro del costruttore del DAO in maniera da avercelo già a disposizione in tutti i suoi metodi


    public StudentsDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Student newStudent) {
        // Entity manager vuole che quando facciamo modifiche esige una transizione
        // 1. la creiamo
        EntityTransaction transaction = this.entityManager.getTransaction();
        // 2. la facciamo partire
        transaction.begin();
        // 3. siccome newStudent non è MANAGED, per aggiungerlo all'elenco degli oggetti
        // monitorati (PersistenceContext) dobbiamo effettuare un operazione di PERSIST
        // l'oggetto managed però è ancora parte del DB
        this.entityManager.persist(newStudent);
        // 4. L'operazione di COMMIT sincronizza il PersistenceContext con il DB
        // siccome in questo caso c'è un oggetto nuovo nel PC, creerà una nuova riga nella tabella Student.
        transaction.commit();
        // 5. log di avvenuto salvataggio
        System.out.println("Lo studente" + newStudent + "è stato salvato nel db");

    }

    public Student findById(long id) {
      Student fromDb =  this.entityManager.find(Student.class, id); // se non trova niente mi torna null
      if (fromDb == null) throw new NotFoundException(id);
      return fromDb;
    }

    public void findByIdAndDelete(long id) {
        // 1. cerchiamo lo studente tramite id visto che abbiamo il metodo lo recicliamo
        Student fromDb = this.findById(id);
        // 2. creiamo una transazione
        EntityTransaction transaction = this.entityManager.getTransaction();
        // 3. facciamo partire la transizione
        transaction.begin();
        // 4. Informiamo l'entityManager che lo studente trovato è da cancellare da Db lo facciamo tramite metodo remove
        this.entityManager.remove(fromDb);
        // 5. facciamo l'operazione di commit (siccome in questo caso c'è un oggetto segnato da rimuovere il DB lo cancellerà
        transaction.commit();
        // 6. eventuale log di cancellazione
        System.out.println("Lo Studente" + fromDb + " è stato rimosso da DB");



    }

}
