package com.endava.HibernateII.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NaturalId
    private long iso_code;

    @Column
    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Address> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIso_code() {
        return iso_code;
    }

    public void setIso_code(long iso_code) {
        this.iso_code = iso_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
