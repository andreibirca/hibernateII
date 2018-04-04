package com.endava.HibernateII.main;

import com.endava.HibernateII.entities.Company;
import com.endava.HibernateII.entities.Employee;
import com.endava.HibernateII.entities.Project;
import com.endava.HibernateII.entities.Skills;
import com.endava.HibernateII.enums.Status;
import com.endava.HibernateII.urils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.io.Console;
import java.util.List;
import java.util.Scanner;

import static com.endava.HibernateII.entities.Skills.Type.Managrial;
import static com.endava.HibernateII.entities.Skills.Type.Soft;

public class Main {
    static long projectCode = 123;
    static int comp_id = 5;
    static Skills.Type skill_type = Managrial;
    static String skill_name = "Java";
    public static void main(String[] args){
        Data.addNewData();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Company company = null;
        Employee employee = null;
        Employee softEmployee = null;
        Project project1 = null;

        try{
            List<Employee> employees = session.createQuery("select e from Employee as e" +
                    " join e.projects as p where p.project_code = :project_code")
                    .setParameter("project_code", projectCode)
                    .list();
            for(Employee e: employees){
                System.out.println(e.getF_name() + " " + e.getL_name());
                System.out.println("Skills:");
                for(Skills s: e.getSkills()){
                    System.out.println("   Skill name  - " + s.getName() + ", skill type - " + s.getType());
                }
            }

            List<Company> companies = session.createQuery("select co from Company as co join co.projects " +
                    "                       as pr where co.id = :co_id")
                                            .setParameter("co_id", comp_id).list();

            for (Company co : companies
                 ) {
                System.out.println("Companies needed: ");
                System.out.println("Companiy: "+co.getName());
                for (Project proj : co.getProjects()
                     ) {
                    System.out.println("Project name: "+ proj.getName()+" ProjectCode: "+proj.getProject_code());
                }
            }

            List<Employee> employees1 = session.createQuery("select  em from Employee as em " +
                    "join em.skills as sk where sk.type = :sk_type")
                    .setParameter("sk_type",  skill_type).list();

            for (Employee em : employees1
                 ) {
                System.out.println("Employee "+em.getL_name()+" User_ID"+em.getUserID());
                for (Skills sk: em.getSkills()
                     ) {
                    System.out.println("Skill details: "+sk.getName()+" "+sk.getType());
                }
            }

            printLine();




            List<Employee> employees2 = session.createQuery("select  em from Employee as em " +
                    "join em.skills as sk where sk.name = :sk_name")
                    .setParameter("sk_name",  skill_name).list();

            for (Employee em : employees2
                    ) {
                System.out.println("Employee "+em.getL_name()+" User_ID"+em.getUserID());
                for (Skills sk: em.getSkills()
                        ) {
                    System.out.println("Skill details: "+sk.getName()+" "+sk.getType());
                }
            }

            company = (Company) session.createQuery("select  c from Company as c")
                   .list().get(0);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }


        //----- persist bask all active users and projects---------------

        session = HibernateUtils.getSessionFactory().openSession();
        try {
            company = session.get(Company.class, company.getId());
            for(Employee e:company.getEmployees()){
               System.out.println(e.getF_name() + " " + e.getL_name());
            }
            for (Project p : company.getProjects()
                 ) {
                System.out.println(p.getProject_code()+" "+p.getName());
            }
            //--------------------------------------------------------------------------
            employee = (Employee) session.createQuery("select  e from Employee as e")
                    .list().get(0);
            employee.setRole(Employee.Role.ADMIN);
            session.beginTransaction();
            session.merge(employee);
            session.getTransaction().commit();


            //--------------------------------------------------------------------------
            List<Project> projetcs = session.createQuery("select p from Project as p" +
                    " join p.employees as e join e.skills as s where s.name = 'Java' group by p.id having count(p.id) >= 2").list();
            for(Project p: projetcs){
                System.out.println(p.getName());
            }

            //---------------------------------------------------------------------------------------------
            //---Soft delete and Employee by setting it INACTIVE
            employee = (Employee) session.createQuery("select  e from Employee as e")
                    .list().get(0);
            employee.setStatus(Status.INACTIVE);
            session.beginTransaction();
            session.merge(employee);
            session.getTransaction().commit();

            //----------------------------------------------------------------------------------------------
            //  ----   Soft delete a Project
            project1 = (Project) session.createQuery("select p from Project as p")
                    .list().get(1);
            project1.setStatus(Status.INACTIVE);
            session.beginTransaction();
            session.merge(project1);
            session.getTransaction().commit();


            //----------------------------------------------------------------------------------------------

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    public static void printLine(){
        System.out.println("------------------------------------------------------------------------------");
    }
}
