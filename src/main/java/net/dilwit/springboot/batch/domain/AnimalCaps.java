package net.dilwit.springboot.batch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AnimalCaps {

    @Id
    @GeneratedValue
    private long id;
    private String name;


    public AnimalCaps() {
    }

    public AnimalCaps(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: " + name;
    }
}
