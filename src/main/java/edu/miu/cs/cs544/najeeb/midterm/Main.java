package edu.miu.cs.cs544.najeeb.midterm;

import edu.miu.cs.cs544.najeeb.midterm.service.CRUD_Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Application Start.");
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigs.class);

        CRUD_Service crud_service = context.getBean(CRUD_Service.class);
        System.out.println("Dynamic Query");
        crud_service.getStudentsFacultyMiamiDynamicQuery().forEach(System.out::println);
        System.out.println("Named Query");
        crud_service.getAllPeopleNamedQuery().forEach(System.out::println);
        System.out.println("Criteria API");
        crud_service.getAllStudentCriteriaAPI().forEach(System.out::println);

        // uncomment these two lines one at a time.
//        DataPopulator.populate_database(context);

        GradeUpdater.ConcurrentOperations(context);

        System.out.println("Application End.");
    }
}