package edu.miu.cs.cs544.najeeb.midterm;

import edu.miu.cs.cs544.najeeb.midterm.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;

import java.util.Random;

public class GradeAdder extends Thread {
    private EntityManager em;
    private int studentId;
    private Random random= new Random();
    private static int count= 0;
    private int threadId;
    private int amount;

    public GradeAdder(int studentId, int amount) {
        this.em= Persistence.createEntityManagerFactory("midterm").createEntityManager();
        this.studentId = studentId;
        this.threadId = count++;
        this.amount = amount;
    }

    @Override
    public void run() {
        this.addGpa();
    }

    private void addGpa() {
        System.out.println("thread " + this.threadId);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Student jack = em.find(Student.class, studentId, LockModeType.PESSIMISTIC_READ);
        try {
            Long time = random.nextLong(10);
            Thread.sleep(Math.abs(time));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        jack.setGpa(jack.getGpa()+this.amount);
        tx.commit();
    }
}
