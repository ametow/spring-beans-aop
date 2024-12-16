package edu.miu.cs.cs544.najeeb.midterm;

import edu.miu.cs.cs544.najeeb.midterm.entity.Address;
import edu.miu.cs.cs544.najeeb.midterm.entity.Course;
import edu.miu.cs.cs544.najeeb.midterm.entity.Faculty;
import edu.miu.cs.cs544.najeeb.midterm.entity.Student;
import edu.miu.cs.cs544.najeeb.midterm.service.CRUD_Service;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "edu.miu.cs.cs544.najeeb.midterm.aspects")
public class JavaConfigs {
    @Bean
    @Scope("prototype")
    public CRUD_Service crudService(){
        return new CRUD_Service("midterm");
    }

    @Bean
    public Address fairfield() {
        return new Address("1000 N 4TH Street", "Fairfield", "Iowa", "52556");
    }

    @Bean
    public Address miami(){
        return new Address("1016 W Flagler St", "Miami", "Florida", "33130");
    }

    @Bean
    public Faculty najeeb(){
        return new Faculty("Najeeb", "cs", fairfield());
    }

    @Bean
    public Faculty muhyieddin(){
        return new Faculty("Muhyieddin", "cs", miami());
    }

    @Bean
    public Course cs544(){
        return new Course("EA", "544", 25, najeeb());
    }

    @Bean
    public Course cs545(){
        return new Course("WAA", "545", 20, muhyieddin());
    }

    @Bean
    public Student jack(){
        return new Student("Jack", 30);
    }

    @Bean
    public Student john(){
        return new Student("John", 28);
    }

    @Bean
    public Student jill(){
        return new Student("Jill", 35);
    }

    @Bean
    public Student jim(){
        return new Student("Jim", 25);
    }
}
