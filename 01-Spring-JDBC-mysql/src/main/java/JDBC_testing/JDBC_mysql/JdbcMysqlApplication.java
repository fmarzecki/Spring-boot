package JDBC_testing.JDBC_mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import JDBC_testing.JDBC_mysql.entity.Student;
import JDBC_testing.JDBC_mysql.entity.StudentDAO;

@SpringBootApplication
public class JdbcMysqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(JdbcMysqlApplication.class, args);
	}

	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			addStudents(studentDAO);
			// findByEmail(studentDAO);
			// updateStudent(studentDAO);
			// updateAllStudentsLastName(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudents(studentDAO);
			retrieveAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		studentDAO.deleteAll();
	}
	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(1);
		System.out.println("\nDeleting...");
	}

	private void updateAllStudentsLastName(StudentDAO studentDAO) {
		int numRowsUpdated = studentDAO.updateAllStudentsLastName("Tester");
		System.out.println(numRowsUpdated + " students updated");
	}
	
	private void updateStudent(StudentDAO studentDAO) {
		int id = 1;
		Student myStudent = studentDAO.findById(id);
		
		System.out.println("\nUpdating student...");
		myStudent.setEmail("@wp.pl");
		myStudent.setFirstName("Ericz");
		studentDAO.updateStudent(myStudent);
	}

	private void addStudents(StudentDAO studentDAO) {
		Student newStudent = new Student("Pilena", "Sinkevich", "@huge");
		Student newStudent2 = new Student("Elek", "Zelek", "@capi");
		studentDAO.save(newStudent);
		studentDAO.save(newStudent2);

		int id = newStudent.getId();
		Student studentFromDatabase = studentDAO.findById(id);

		System.out.println("\nNew student Name:" + newStudent.getLastName());
		System.out.println("Database student:" + studentFromDatabase.getLastName());
	}

	public void retrieveAllStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();

		for (Student student : students) {
			System.out.println(student);
		}
	}

	public void findByEmail(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByEmail("@huge");

		System.out.println("\nFound by Email:");
		for (Student student : students) {;
			System.out.println(student);
		};
		
	}
}
