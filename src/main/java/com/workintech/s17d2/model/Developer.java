package com.workintech.s17d2.model;



public class Developer {

    private Integer id;
    private String name;
    private double salary;
    private Experience experience;

    public Developer(Integer id,
                     String name,
                     double salary,
                     Experience experience) {

        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Experience getExperience() {
        return experience;
    }
}