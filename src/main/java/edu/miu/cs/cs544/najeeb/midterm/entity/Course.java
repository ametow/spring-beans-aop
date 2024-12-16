package edu.miu.cs.cs544.najeeb.midterm.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id@GeneratedValue
    private int id;
    private String title;
    private String code;
    private int capacity;

    public Course() {
    }

    public Course(String title, String code, int capacity, Faculty faculty) {
        this.title = title;
        this.code = code;
        this.capacity = capacity;
        this.faculty = faculty;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    private Faculty faculty;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Student> attendees = new ArrayList<>();

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addStudent(Student student) {
        this.attendees.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", capacity=" + capacity +
                ", faculty=" + faculty +
                ", attendees=" + attendees +
                '}';
    }
}
