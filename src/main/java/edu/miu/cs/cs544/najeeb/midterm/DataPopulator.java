package edu.miu.cs.cs544.najeeb.midterm;

import edu.miu.cs.cs544.najeeb.midterm.entity.Course;
import edu.miu.cs.cs544.najeeb.midterm.entity.Student;
import edu.miu.cs.cs544.najeeb.midterm.service.CRUD_Service;
import org.springframework.context.ApplicationContext;

public class DataPopulator {

    public static void populate_database(ApplicationContext context) throws Exception {
        CRUD_Service crudService = (CRUD_Service) context.getBean("crudService");
        // get Courses
        Course cs544= context.getBean("cs544", Course.class);
        Course cs545= context.getBean("cs545", Course.class);
        // get Students
        Student jack= context.getBean("jack", Student.class);
        Student john= context.getBean("john", Student.class);
        Student jill= context.getBean("jill", Student.class);
        Student jim= context.getBean("jim", Student.class);
        // add Students to courses
        cs545.addStudent(jack);
        cs545.addStudent(john);
        cs545.addStudent(jill);
        cs544.addStudent(jack);
        cs544.addStudent(jill);
        cs545.addStudent(jim);
        // persist all courses
        crudService.create(cs545);
        crudService.create(cs544);
        crudService.close();
    }
}
