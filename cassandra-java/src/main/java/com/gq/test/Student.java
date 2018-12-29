package com.gq.test;

public class Student {
    private Integer id;
    private String address;
    private String name;
    private Integer age;
    private Integer height;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }

    public Student(Integer id, String address, String name, Integer age, Integer height) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
