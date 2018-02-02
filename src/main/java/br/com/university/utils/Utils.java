package br.com.university.utils;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.university.Address;
import br.com.university.Course;
import br.com.university.Discipline;
import br.com.university.Professor;
import br.com.university.Student;
import br.com.university.UniClass;

public class Utils {

	public void insertion() {

		Transaction transaction = null;

        Discipline discipline1 = new Discipline(1, "Banco de Dados", 30);
	    Discipline discipline2 = new Discipline(2, "Estrutura de Dados", 30);
	    Discipline discipline3 = new Discipline(3, "Direito Penal", 25);
	    Discipline discipline4 = new Discipline(4, "Calculo Numerico", 30);
	    Discipline discipline5 = new Discipline(5, "Psicologia Infantil", 25);
	    Discipline discipline6 = new Discipline(6, "Direito Tributario", 33);
	    Discipline discipline7 = new Discipline(7, "Engenharia de Software", 27);

	    Course course1 = new Course(2142, "Engenharia Civil", 1500);
	    Course course2 = new Course(2143, "Ciencia da Computacao", 2000);
	    Course course3 = new Course(2144, "Direito", 1750);
	    Course course4 = new Course(2145, "Pedagogia", 1500);
	    Course course5 = new Course(2146, "Odontologia", 1600);
		
	    Professor professor1 = new Professor(45675, "Abgair Simon Ferreira", LocalDate.of(1992, 04, 10), "Banco de Dados");
	    Professor professor2 = new Professor(45690, "Ramon Travanti", LocalDate.of(1993, 05, 20), "Direito Romano");
	    Professor professor3 = new Professor(45691, "Gustavo Golveia Netto", LocalDate.of(1993, 04, 05), "Sociologia");
	    Professor professor4 = new Professor(45692, "Marcos Salvador", LocalDate.of(1993, 03, 31), "Matematica Financeira");
	    Professor professor5 = new Professor(45693, "Cintia Falcao", LocalDate.of(1993, 02, 15), "Engenharia de Software");

	    Student student1 = new Student(111, "Edvaldo Carlos Silva", new Address("Av. Sao Carlos, 186", "Sao Carlos-SP"));
	    Student student2 = new Student(112, "Joao Benedito Scapin", new Address("R. Jose Bonifacio, 70", "Sao Carlos-SP"));
	    Student student3 = new Student(113, "Carol Antonia Silveira", new Address("R. Luiz Camoes, 120", "Ibate-SP"));
	    Student student4 = new Student(114, "Marcos Joao Casanova", new Address("Av. Sao Carlos, 176", "Sao Carlos-SP"));
	    Student student5 = new Student(115, "Simone Cristina Lima", new Address("R. Raul Junior, 180", "Sao Carlos-SP"));
	    Student student6 = new Student(116, "Ailton Castro", new Address("R. Antonio Carlos, 120", "Ibate-SP"));
	    Student student7 = new Student(117, "Jose Paulo Figueira", new Address("R. XV Novembro, 871", "Sao Carlos-SP"));

	    course1.addDiscipline(discipline4);
	    course1.addDiscipline(discipline7);
	    course2.addDiscipline(discipline1);
	    course2.addDiscipline(discipline2);
	    course2.addDiscipline(discipline4);
	    course2.addDiscipline(discipline7);
	    course3.addDiscipline(discipline3);
	    course3.addDiscipline(discipline6);
	    course4.addDiscipline(discipline5);
			
	    course1.addStudent(student2);
	    course1.addStudent(student6);
	    course2.addStudent(student1);
	    course2.addStudent(student4);
	    course3.addStudent(student5);
	    course4.addStudent(student3);
	    course4.addStudent(student7);
	
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
	    	transaction = session.beginTransaction();
	    	session.save(course1);
	    	session.save(course2);
	    	session.save(course3);
	    	session.save(course4);
	    	session.save(course5);
				
	    	session.save(professor1);
	    	session.save(professor2);
	    	session.save(professor3);
	    	session.save(professor4);
	    	session.save(professor5);
	    	transaction.commit();

	    } catch (Exception exception) {
	    	if (transaction != null) {
	    	    transaction.rollback();
	    	}
	    	throw exception;
	    }
		
	    UniClass uniClass1 = new UniClass("01/1998", 8.5, student1, discipline1, professor1);
	    UniClass uniClass2 = new UniClass("01/1998", 7.0, student4, discipline1, professor1);
	    UniClass uniClass3 = new UniClass("01/1998", 6.0, student1, discipline2, professor1);
	    UniClass uniClass4 = new UniClass("01/1998", 8.0, student4, discipline2, professor1);
	    UniClass uniClass5 = new UniClass("02/1998", 7.0, student1, discipline2, professor1);
	    UniClass uniClass6 = new UniClass("01/1998", 4.5, student5, discipline3, professor2);
	    UniClass uniClass7 = new UniClass("02/1998", 7.5, student5, discipline3, professor2);
	    UniClass uniClass8 = new UniClass("01/1998", 8.0, student1, discipline4, professor4);
	    UniClass uniClass9 = new UniClass("01/1998", 7.0, student2, discipline4, professor4);
	    UniClass uniClass10 = new UniClass("01/1998", 7.5, student3, discipline5, professor3);
	    UniClass uniClass11 = new UniClass("01/1998", 9.0, student5, discipline6, professor2);
	    UniClass uniClass12 = new UniClass("01/1998", 10.0, student1, discipline7, professor5);
	    UniClass uniClass13 = new UniClass("01/1998", 5.5, student2, discipline7, professor5);
	    UniClass uniClass14 = new UniClass("02/1998", 10.0, student2, discipline7, professor5);
	    UniClass uniClass15 = new UniClass("01/1998", 6.5, student4, discipline4, professor4);
	    UniClass uniClass16 = new UniClass("02/1998", 8.5, student4, discipline4, professor4);
	    UniClass uniClass17 = new UniClass("01/1998", 3.5, student6, discipline4, professor4);
	    UniClass uniClass18 = new UniClass("02/1998", 9.5, student6, discipline4, professor4);
	    UniClass uniClass19 = new UniClass("01/1998", 9.5, student4, discipline7, professor5);
	    UniClass uniClass20 = new UniClass("01/1998", 8.5, student6, discipline7, professor5);
		
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
	    	transaction = session.beginTransaction();
	    	session.save(uniClass1);
	    	session.save(uniClass2);
	    	session.save(uniClass3);
	    	session.save(uniClass4);
	    	session.save(uniClass5);
	    	session.save(uniClass6);
	    	session.save(uniClass7);
	    	session.save(uniClass8);
	    	session.save(uniClass9);
	    	session.save(uniClass10);
	    	session.save(uniClass11);
	    	session.save(uniClass12);
	    	session.save(uniClass13);
	    	session.save(uniClass14);
	    	session.save(uniClass15);
	    	session.save(uniClass16);
	    	session.save(uniClass17);
	    	session.save(uniClass18);
	    	session.save(uniClass19);
	    	session.save(uniClass20);
	    	transaction.commit();

	    } catch (Exception exception) {
	    	if (transaction != null) {
	    	    transaction.rollback();
	        }
	        throw exception;
	    }
    }

}