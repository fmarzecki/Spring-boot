package JDBC_testing.JDBC_mysql.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository 
public class StudentDAORepo implements StudentDAO {
    private EntityManager entityManager;

    @Autowired
    public StudentDAORepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    // nie musimy dawac @Transactional poniewaz to
    // tylko zapytanie do bazy i nic w niej nie modyfikujemy
    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> thQuery = entityManager.createQuery("FROM Student", Student.class);
        List<Student> students = thQuery.getResultList();
        return students;
    }

    @Override
    public List<Student> findByEmail(String email) {
        TypedQuery<Student> theQuery = entityManager.
        createQuery("FROM Student WHERE email=:theEmail", Student.class);
        theQuery.setParameter("theEmail", email);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public int updateAllStudentsLastName(String newLastName) {
        int numRowsUpdated = entityManager.createQuery(
            "UPDATE Student SET lastName=:theLastName").
            setParameter("theLastName", newLastName).executeUpdate();

        return numRowsUpdated;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
		Student myStudent = entityManager.find(Student.class, id)   ;
        entityManager.remove(myStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numStudentsDeleted = entityManager.createQuery(
            "DELETE FROM Student").executeUpdate();

        return numStudentsDeleted;
    }
}
