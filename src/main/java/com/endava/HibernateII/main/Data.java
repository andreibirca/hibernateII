package com.endava.HibernateII.main;

import com.endava.HibernateII.entities.*;
import com.endava.HibernateII.enums.Status;
import com.endava.HibernateII.urils.HibernateUtils;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.HashSet;

public class Data {

    static Country russia = new Country();
    static Address adr1 = new Address();
    static Skills sk1 = new Skills();
    static Skills sk2 = new Skills();
    static Skills sk3 = new Skills();
    static Company cny1 = new Company();
    static Project proj1 = new Project();
    static Project proj2 = new Project();
    static Employee empl = new Employee();
    static Employee emp2 = new Employee();
    static Employee emp3 = new Employee();


    static {
        russia.setIso_code(789);
        russia.setName("Russia");

        adr1.setCity("Moscow");
        adr1.setCountry(russia);
        adr1.setStreet("Lubianka");
        adr1.setStr_num(49);

        sk1.setType(Skills.Type.Managrial);
        sk1.setName("Manager");

        sk2.setType(Skills.Type.Soft);
        sk2.setName("Java");

        sk3.setType(Skills.Type.Managrial);
        sk3.setName("Basic");

        cny1.setName("IBM");
        cny1.setProjects(new HashSet<Project>(Arrays.asList(proj1)));

        proj1.setName("Project_One");
        proj1.setProject_code(123);
        proj1.setDescription("My first project");
        proj1.setCompany(cny1);
        proj1.setStatus(Status.ACTIVE);

        proj2.setName("Project_Two");
        proj2.setProject_code(124);
        proj2.setDescription("My second project");
        proj2.setCompany(cny1);
        proj2.setStatus(Status.ACTIVE);
        empl.setF_name("Petru");
        empl.setL_name("Tataru");
        empl.setUserID(1);
        empl.setRole(Employee.Role.MANAGER);
        empl.setAddress(adr1);
        empl.setCompany(cny1);
        empl.setSkills(new HashSet<Skills>(Arrays.asList(sk1, sk2)));
        empl.setProjects(new HashSet<Project>(Arrays.asList(proj1)));
        empl.setStatus(Status.ACTIVE);

        emp2.setF_name("Ion");
        emp2.setL_name("Zuza");
        emp2.setUserID(2);
        emp2.setRole(Employee.Role.WORKER);
        emp2.setAddress(adr1);
        emp2.setCompany(cny1);
        emp2.setSkills(new HashSet<Skills>(Arrays.asList(sk1, sk2)));
        emp2.setProjects(new HashSet<Project>(Arrays.asList(proj2)));
        emp2.setStatus(Status.ACTIVE);

        emp3.setF_name("Boris");
        emp3.setL_name("Statin");
        emp3.setUserID(3);
        emp3.setRole(Employee.Role.WORKER);
        emp3.setAddress(adr1);
        emp3.setCompany(cny1);
        emp3.setSkills(new HashSet<Skills>(Arrays.asList(sk1, sk2)));
        emp3.setProjects(new HashSet<Project>(Arrays.asList(proj2)));
        emp3.setStatus(Status.ACTIVE);



    }

    public static void addNewData(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(russia);
            session.save(adr1);
            session.save(sk1);
            session.save(sk2);
            session.save(sk3);
            session.save(cny1);
            session.save(proj1);
            session.save(proj2);
            session.save(empl);
            session.save(emp2);
            session.save(emp3);

//            static Country russia = new Country();
//            static Address adr1 = new Address();
//            static Skills sk1 = new Skills();
//            static Skills sk2 = new Skills();
//            static Skills sk3 = new Skills();
//            static Company cny1 = new Company();
//            static Project proj1 = new Project();
//            static Project proj2 = new Project();
//            static Employee empl = new Employee();
//            static Employee emp2 = new Employee();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
