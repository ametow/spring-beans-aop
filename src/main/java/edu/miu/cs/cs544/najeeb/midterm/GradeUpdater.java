package edu.miu.cs.cs544.najeeb.midterm;

import edu.miu.cs.cs544.najeeb.midterm.entity.Student;
import edu.miu.cs.cs544.najeeb.midterm.service.CRUD_Service;
import jakarta.persistence.CacheRetrieveMode;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class GradeUpdater {
    public static void ConcurrentOperations(ApplicationContext context) throws Exception {
        CRUD_Service service = (CRUD_Service) context.getBean("crudService");
        // check the database to make sure Jack has id 4, otherwise change this to match the id in the database
        Student jack= service.read(4);
        Thread[] threads = new Thread[50];
        for(int i = 0; i < threads.length; ) {
            threads[i]= new GradeAdder(jack.getId(), 1);
            threads[i++].start();
            threads[i]= new GradeAdder(jack.getId(), -1);
            threads[i++].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        Map properties= new HashMap();
//        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        em.setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);

        jack= service.read(jack.getId());
        System.out.println(jack);
        service.close();
    }
}
