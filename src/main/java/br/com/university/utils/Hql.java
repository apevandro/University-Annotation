/* The Stream API provides a set of methods that make it easy to filter elements, 
 * to check if they match certain criteria and to aggregate all elements.
 * In general, these methods are great but don’t use them to post-process your query results. 
 * As long as you can express these operations with SQL the database can do them a lot better!
 * 
 * There are four stream examples bellow. Aggregate operations that accept lambda expression.
 */
package br.com.university.utils;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.stream.Stream;

import org.hibernate.Session;

import br.com.university.Course;
import br.com.university.Discipline;
import br.com.university.Student;
import br.com.university.utils.HibernateUtil;

public class Hql {

	public void questionA() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.createQuery("from Course", Course.class)
		       .stream()
		       .forEach(course -> System.out.println("Number: " + course.getCourseId() + "  " + 
		                                             "Name: " + course.getName() + "  " +
		                        	  			     "Total credits: " + course.getTotalCredits()));
		session.close();
	}

	public void questionB() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select s.name from Student s " +
				     "where s.address.city = :city " +
		             "order by s.name desc";

		session.createQuery(hql, String.class).setParameter("city", "Sao Carlos-SP")
			                                  .stream()
			                                  .forEach(name -> System.out.println("Student name: " + name));
		session.close();
    }

    public void questionB_streamVersion() {

    	Session session = HibernateUtil.getSessionFactory().openSession();

		Stream<Student> student = session.createQuery("from Student", Student.class).stream();

		student.filter(s -> s.getAddress().getCity().equals("Sao Carlos-SP"))   // get all students from Sao Carlos city
		       .map(s -> s.getName())                                           // get all names from those students
		       .sorted((n1, n2) -> n2.compareTo(n1))                            // sort the names
		       .forEach(name -> System.out.println("Student name: " + name));   // and finally print the names

		session.close();
    }

    public void questionC() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select p.name from Professor p " +
		             "where p.admission < :date";

		session.createQuery(hql, String.class).setParameter("date", LocalDate.of(1993, 01, 01))
		                                      .stream()
	                                          .forEach(name -> System.out.println("Professor name: " + name));

		session.close();
    }

    public void questionD() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select s.name from Student s " +
			         "where s.name like 'J%'";

		session.createQuery(hql, String.class).stream()
		                                      .forEach(name -> System.out.println("Student name: " + name));

		session.close();
    }

    public void questionD_streamVersion() {

    	Session session = HibernateUtil.getSessionFactory().openSession();

		Stream<Student> student = session.createQuery("from Student", Student.class).stream();

		student.filter(s -> s.getName().startsWith("J"))                        // get all students whose names start with J
	           .map(s -> s.getName())                                           // get all those names
	           .forEach(name -> System.out.println("Student name: " + name));   // print the names

		session.close();
    }

    public void questionE() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select d.name from Discipline d join d.courses c " +
		             "where c.name like 'Ciencia da Computacao'";

		session.createQuery(hql, String.class).stream()
		                                      .forEach(name -> System.out.println("Discipline name: " + name));

		session.close();
    }

    public void questionE_streamVersion() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		Stream<Course> course = session.createQuery("from Course", Course.class).stream();

		course.filter(c -> c.getName().equals("Ciencia da Computacao"))
		      .forEach(c -> c.getDisciplines().stream()
		    		                          .map(d -> d.getName())
		    		                          .forEach(name -> System.out.println("Discipline name: " + name)));

		session.close();
    }

    public void questionF() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select c.name from Course c join c.disciplines d " +
			         "where d.name like 'Calculo Numerico'";

		session.createQuery(hql, String.class).stream()
		                                      .forEach(name -> System.out.println("Course name: " + name));

		session.close();
    }

    public void questionF_streamVersion() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		Stream<Discipline> discipline = session.createQuery("from Discipline", Discipline.class).stream();

		discipline.filter(d -> d.getName().equals("Calculo Numerico"))
	              .forEach(d -> d.getCourses().stream()
	    		                              .map(c -> c.getName())
	    		                              .forEach(name -> System.out.println("Course name: " + name)));

		session.close();
    }

    public void questionG() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select d.name from Discipline d, Student s, UniClass c  " +
			         "where d.discId = c.id.discId and " +
				           "c.id.semester like '01/1998' and " +
			               "c.id.studentId = s.studentId and " +
				           "s.name like 'Marcos Joao Casanova'";

		session.createQuery(hql, String.class).stream()
		                                      .forEach(name -> System.out.println("Discipline name: " + name));

		session.close();
    }

    public void questionH() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select d.name from Discipline d, Student s, UniClass c " +
			         "where s.studentId = c.id.studentId and " +
				           "c.id.discId = d.discId and " +
			               "s.name like 'Ailton Castro' and " +
				           "c.grade < 7";

		session.createQuery(hql, String.class).stream()
		                                      .forEach(name -> System.out.println("Discipline name: " + name));

		session.close();
    }

    public void questionI() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select s.name from Student s, Discipline d, UniClass c " +
			         "where s.studentId = c.id.studentId and " +
				           "c.id.discId = d.discId and " +
			               "d.name like 'Calculo Numerico' and " +
				           "c.id.semester like '01/1998' and " + 
				           "c.grade < 7";

		session.createQuery(hql, String.class).stream()
		                                      .forEach(name -> System.out.println("Student name: " + name));

		session.close();
    }

    public void questionJ() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select distinct d.name from Discipline d, Professor p, UniClass c " +
			         "where c.id.discId = d.discId and " +
				           "c.id.profId = p.profId and " +
				           "p.name like 'Ramon Travanti'";

		session.createQuery(hql, String.class).stream()
		                                      .forEach(name -> System.out.println("Discipline name: " + name));

		session.close();
    }

    public void questionK() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select distinct p.name from Professor p, Discipline d, UniClass c " +
			         "where c.id.profId = p.profId and " +
				           "c.id.discId = d.discId and " +
				           "d.name like 'Banco de Dados'";

		session.createQuery(hql, String.class).stream()
		                                      .forEach(name -> System.out.println("Professor name: " + name));

		session.close();
    }

    public void questionL() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select min(c.grade), max(c.grade) from UniClass c, Discipline d " +
			         "where d.name like 'Calculo Numerico' and " +
				           "d.discId = c.id.discId and " +
				           "c.id.semester like '01/1998'";

		Object[] result = (Object[]) session.createQuery(hql).list().get(0);

		System.out.println("Minimun grade: " + result[0]);
		System.out.println("Maximun grade: " + result[1]);

		session.close();
    }

    public void questionM() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select s.name, c.grade from Student s, UniClass c, Discipline d " +
			         "where c.id.studentId = s.studentId and " +
				           "c.id.discId = d.discId and " +
				           "d.name like 'Engenharia de Software' and " +
				           "c.id.semester like '01/1998' and " +
				           "c.grade in ( select max(c.grade) from Student s, UniClass c, Discipline d " +
				                        "where c.id.studentId = s.studentId and " +
				                              "c.id.discId = d.discId and " +
				                              "d.name like 'Engenharia de Software' and " +
				                              "c.id.semester like '01/1998' )";

		Object[] result = (Object[]) session.createQuery(hql).list().get(0);

		System.out.println("Student name: " + result[0]);
		System.out.println("Grade: " + result[1]);

		session.close();
    }

    public void questionN() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select s.name, d.name, p.name from Student s, Discipline d, Professor p, UniClass c " +
			         "where c.id.studentId = s.studentId and " +
				           "c.id.discId = d.discId and " +
				           "c.id.profId = p.profId and " +
				           "c.id.semester like '01/1998' " +
				           "ORDER BY s.name ASC";

		Iterator<Object[]> iterator = session.createQuery(hql, Object[].class).list().iterator();
		Object[] result;
		
		while(iterator.hasNext()) {
			result = iterator.next();
			System.out.println("Student: " + result[0] + "   Discipline: " + result[1] + "   Professor: " + result[2]);
		}

		session.close();
    }

    public void questionO() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
	
		String hql = "select s.name, d.name, c.grade from Student s, Discipline d join d.courses co, UniClass c " +
			         "where co.name like 'Ciencia da Computacao' and " +
				           "c.id.semester like '01/1998' and " +
				           "s.studentId = c.id.studentId and " +
				           "c.id.discId = d.discId and " + 
				           "co.courseId = s.course.courseId";

		Iterator<Object[]> iterator = session.createQuery(hql, Object[].class).list().iterator();
		Object[] result;
		
		while(iterator.hasNext()) {
			result = iterator.next();
			System.out.println("Student: " + result[0] + "   Discipline: " + result[1] + "   Grade: " + result[2]);
		}

		session.close();
    }

    public void questionP() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select avg(c.grade) from UniClass c, Professor p " +
			         "where c.id.profId = p.profId and " +
				           "p.name like 'Marcos Salvador'";

		Double average = session.createQuery(hql, Double.class).getSingleResult();

		System.out.println("Average: " + average);

		session.close();
    }

    public void questionQ() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select s.name, d.name, c.grade from Student s, Discipline d, UniClass c " +
			         "where c.id.discId = d.discId and " +
				           "c.id.studentId = s.studentId and " +
			               "c.grade between 5 and 7 " +
				           "order by d.name asc";

		Iterator<Object[]> iterator = session.createQuery(hql, Object[].class).list().iterator();
		Object[] result;
		
		while(iterator.hasNext()) {
			result = iterator.next();
			System.out.println("Student: " + result[0] + "   Discipline: " + result[1] + "   Grade: " + result[2]);
		}

		session.close();
    }

    public void questionR() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select avg(c.grade) from Discipline d, UniClass c " +
			         "where d.name like 'Calculo Numerico' and " +
				           "d.discId = c.id.discId and " +
			               "c.id.semester like '01/1998'";

        Double average = session.createQuery(hql, Double.class).getSingleResult();
		
		System.out.println("Average: " + average);

		session.close();
    }

    public void questionS() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select count(c.id.studentId) from UniClass c, Professor p " +
			         "where p.name like 'Abgair Simon Ferreira' and " +
				           "p.profId = c.id.profId and " +
			               "c.id.semester like '01/1998'";

        Long quantity = session.createQuery(hql, Long.class).getSingleResult();

        System.out.println("Number of students: " + quantity);

		session.close();
    }

    public void questionT() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select avg(c.grade), count(c.id.studentId) from Student s, UniClass c " +
			         "where s.name like 'Edvaldo Carlos Silva' and " +
				           "s.studentId = c.id.studentId and " +
			               "c.id.semester like '01/1998'";

		Object[] result = session.createQuery(hql, Object[].class).list().get(0);

		System.out.println("Grade average: " + result[0] + "   Number of classes: " + result[1]);

		session.close();
    }

    public void questionU() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select d.name, avg(c.grade) from  Discipline d, UniClass c " +
			         "where d.discId = c.id.discId and " +
				           "c.id.semester like '01/1998' " +
			               "group by d.name " +
				           "order by d.name asc";

		Iterator<Object[]> iterator = session.createQuery(hql, Object[].class).list().iterator();
		Object[] result;

		while(iterator.hasNext()) {
			result = iterator.next();
			System.out.println("Discipline: " + result[0] + "   Average: " + result[1]);
		}

		session.close();
    }

    public void questionV() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select p.name, avg(c.grade), count(c.grade) from Professor p, Discipline d, UniClass c " +
			         "where c.id.profId = p.profId and " +
				           "c.id.discId = d.discId and " +
			               "c.id.semester like '01/1998' " +
				           "group by p.name";

		Iterator<Object[]> iterator = session.createQuery(hql, Object[].class).list().iterator();
		Object[] result;

		while(iterator.hasNext()) {
			result = iterator.next();
			System.out.println("Professor: " + result[0] + "   Average: " + result[1] + "   Quantity: " + result[2]);
		}

		session.close();
    }

    public void questionW() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select d.name, avg(c.grade) from Discipline d join d.courses co, UniClass c " +
			         "where co.name like 'Ciencia da Computacao' and " +
				           "d.discId = c.id.discId and " +
			               "c.id.semester like '01/1998' " +
				           "group by d.name";

		Iterator<Object[]> iterator = session.createQuery(hql, Object[].class).list().iterator();
		Object[] result;

		while(iterator.hasNext()) {
			result = iterator.next();
			System.out.println("Discipline: " + result[0] + "   Average: " + result[1]);
		}

		session.close();
    }

    public void questionX() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select sum(d.credits) from Discipline d, Student s, UniClass c " +
			         "where d.discId = c.id.discId and " +
				           "c.id.studentId = s.studentId and " +
			               "s.name like 'Edvaldo Carlos Silva' and " +
				           "c.grade >= 7";

		Long credits = session.createQuery(hql, Long.class).getSingleResult();
		
	    System.out.println("Credits: " + credits);

		session.close();
    }

    public void questionY() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select s.name, sum(d.credits) from Discipline d, Student s, UniClass c " +
			         "where c.id.discId = d.discId and " +
				           "c.id.studentId = s.studentId and " +
				           "c.grade >= 7 " +
				           "group by s.name " +
				           "having sum(d.credits) >= 70";

		Iterator<Object[]> iterator = session.createQuery(hql, Object[].class).list().iterator();
		Object[] result;

		while(iterator.hasNext()) {
			result = iterator.next();
			System.out.println("Student: " + result[0] + "   Credits: " + result[1]);
		}

		session.close();
    }

    public void questionZ() {
    	Session session = HibernateUtil.getSessionFactory().openSession();

		String hql = "select s.name, d.name, p.name from Student s, Discipline d join d.courses co, Professor p, UniClass c " +
			         "where c.id.studentId = s.studentId and " +
				           "c.id.profId = p.profId and " +
				           "c.id.discId = d.discId and " +
				           "s.course.courseId = co.courseId and " +
				           "c.id.semester like '01/1998' and " + 
				           "c.grade > 8 and " +
				           "co.name like 'Ciencia da Computacao'";

		Iterator<Object[]> iterator = session.createQuery(hql, Object[].class).list().iterator();
		Object[] result;

		while(iterator.hasNext()) {
			result = iterator.next();
			System.out.println("Student: " + result[0] + "   Discipline: " + result[1] + "   Professor: " + result[2]);
		}

		session.close();
    }

}