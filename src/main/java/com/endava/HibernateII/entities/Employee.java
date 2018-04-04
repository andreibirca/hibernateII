package com.endava.HibernateII.entities;

import com.endava.HibernateII.enums.Status;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NaturalId
    private int userID;

    @Column
    private String f_name;

    @Column
    private String l_name;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "employee_skills",
    joinColumns = {@JoinColumn(name = "employee_id")},
    inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private Set<Skills> skills;

    @OneToOne
    private Address address;

    public enum Role{ WORKER, MANAGER, ADMIN}

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_project",
        joinColumns = {@JoinColumn(name = "employee_id")},
        inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projects;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public Set<com.endava.HibernateII.entities.Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<com.endava.HibernateII.entities.Skills> skills) {
        this.skills = skills;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
