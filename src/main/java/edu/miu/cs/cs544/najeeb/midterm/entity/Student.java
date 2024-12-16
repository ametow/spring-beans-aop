package edu.miu.cs.cs544.najeeb.midterm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;

import java.util.List;

@Entity
public class Student extends Person {
    private int gpa;

    @ManyToMany(mappedBy = "attendees")
    private List<Course> courses;

    public Student() {
    }

    public Student(String name, int gpa) {
        super(name);
        this.gpa = gpa;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }


    @Override
    public String toString() {
        return "Student{" +
                "gpa=" + gpa + " " +
                super.toString() +
                '}';
    }
}
