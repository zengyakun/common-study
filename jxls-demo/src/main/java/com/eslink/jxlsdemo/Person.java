package com.eslink.jxlsdemo;

public class Person {
    String id;
    String name;
    Integer age;
    byte[] img;

    public Person(String id, String name, Integer age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    /**
     * 以下省略了对应的get/set方法，请自行补齐
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
