package com.course.autopodborplatform.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private int ID;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy="role",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<User> users;

    public Role(int ID, String name, List<User> users) {
        this.ID = ID;
        this.name = name;
        this.users = users;
    }

    public Role() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
