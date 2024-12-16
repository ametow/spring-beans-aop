package edu.miu.cs.cs544.najeeb.midterm.service;

import edu.miu.cs.cs544.najeeb.midterm.entity.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.util.List;

public class CRUD_Service implements AutoCloseable {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    public CRUD_Service(String persistenceUnitName) {
        System.out.println("CRUD_Service Constructor");
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
    }


    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private void startTransaction() {
        tx = em.getTransaction();
        tx.begin();
    }

    private void endTransaction() {
        tx.commit();
    }

    public void create(Course course) {
        startTransaction();
        em.persist(course);
        endTransaction();
    }

    public Student read(int id) {
        startTransaction();
        Student student= em.find(Student.class, id);
        endTransaction();
        return student;
    }

    public Student update(Student student) {
        startTransaction();
        Student databaseStudent= em.merge(student);
        endTransaction();
        return databaseStudent;
    }

    public void delete(Student student) {
        startTransaction();
        em.remove(student);
        endTransaction();
    }

    public List<Student> getStudentsFacultyMiamiDynamicQuery(){
        startTransaction();

        TypedQuery<Student> query = em.createQuery("select s from Course c join c.attendees s where c.faculty.address.city = 'Miami'", Student.class);

        List<Student> students = query.getResultList();

        endTransaction();

        return students;
    }

    public List<Person> getAllPeopleNamedQuery(){
        startTransaction();

        TypedQuery<Person> q = em.createNamedQuery("Person.findAll", Person.class);
        List<Person> people = q.getResultList();

        endTransaction();

        return people;
    }

    public List<Student> getAllStudentCriteriaAPI(){
        startTransaction();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        Join<Student, Course> courseJoin = root.join("courses");
        Join<Course, Faculty> facultyJoin = courseJoin.join("faculty");
        Join<Faculty, Address> addressJoin = facultyJoin.join("address");

        Predicate capacityPred = cb.greaterThan(courseJoin.get("capacity"), 22);
        Predicate addressPred = cb.equal(addressJoin.get("state"), "Iowa");
        Predicate studentPred = cb.greaterThanOrEqualTo(root.get("gpa"), 30);

        CriteriaQuery<Student> studentCriteriaQuery = query.select(root)
                .where(cb.and(addressPred, studentPred, capacityPred));
        List<Student> students = em.createQuery(studentCriteriaQuery).getResultList();

        endTransaction();

        return students;
    }

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();
    }

}
