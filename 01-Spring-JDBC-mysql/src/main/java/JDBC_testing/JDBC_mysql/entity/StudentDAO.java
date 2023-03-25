package JDBC_testing.JDBC_mysql.entity;

import java.util.List;

public interface StudentDAO {
    void save(Student student);
    void updateStudent(Student student);
    void delete(Integer id);
    int deleteAll();
    int updateAllStudentsLastName(String newLastName);
    Student findById(int id);
    List<Student> findAll();
    List<Student> findByEmail(String email);
}
