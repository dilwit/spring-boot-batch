package net.dilwit.springboot.batch.chunk.dbtodb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudentConcat {

    @Id
    @GeneratedValue
    private long id;

    private String value;

    public StudentConcat() {
    }

    public StudentConcat(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
