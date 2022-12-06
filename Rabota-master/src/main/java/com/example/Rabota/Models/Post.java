package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @Pattern(regexp ="^[а-яА-ЯёЁ0-9]+$", message = "Наименование должности может состоять только из кириллицы")
    private String postname;

    @Pattern(regexp ="^[0-9]+$", message = "Оклад может содержать только цифры")
    private String salary;

    @ManyToMany
    @JoinTable(name="manager_post",
            joinColumns=@JoinColumn(name="post_id"),
            inverseJoinColumns=@JoinColumn(name="manager_id"))
    private List<Manager> managers;

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public String getPostname() {return postname;}

    public void setPostname(String postname) {this.postname = postname;}

    public String getSalary() {return salary;}

    public void setSalary(String salary) {this.salary = salary;}

    public Post() {}

    public Post(String postname, String salary) {
        this.postname = postname;
        this.salary = salary;
    }
}
