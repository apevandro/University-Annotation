package br.com.university;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@Column(name = "CourseId")
    private int courseId;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "TotalCredits")
    private int totalCredits;
	
	@OneToMany(mappedBy = "course", orphanRemoval = true)
	@Cascade(CascadeType.SAVE_UPDATE)
    private Set<Student> students = new HashSet<Student>();
	
    @ManyToMany
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinTable(name = "DISC_COURSE",
               joinColumns = {@JoinColumn(name = "CourseId")}, 
               inverseJoinColumns = {@JoinColumn(name = "DiscId")})	
    private Set<Discipline> disciplines = new HashSet<Discipline>();

    public Course() {}

    public Course(int courseId, String name, int totalCredits) {
        this.courseId = courseId;
        this.name = name;
        this.totalCredits = totalCredits;
    }

    public int getCourseId() {
		return courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(Set<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	// Assistant method. It guarantees referential integrity.
	public void addStudent(Student student) {
		getStudents().add(student);
		student.setCourse(this);
	}
		
	// Assistant method. It guarantees referential integrity.
	public void addDiscipline(Discipline discipline) {
		getDisciplines().add(discipline);
		discipline.getCourses().add(this);
	}
	
	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if(!obj.getClass().equals(this.getClass())) {
        	return false;
        }

        Course other = (Course) obj;

        if (other.getCourseId() == courseId &&
                other.getName().equals(name) &&
                    other.getTotalCredits() == totalCredits) {
            return true;
        }

        return false;
    }

    public int hashCode() {
    	final int prime = 31;
		int result = 1;
		result = prime * result + (courseId + name + totalCredits).hashCode();
		return result;
    }

}