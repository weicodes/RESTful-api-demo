package com.wd.rest.pojo;

public class User {

    /** 用户id */
    Integer id;
    /** 用户名 */
    String name;
    /** 用户年龄 */
    Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
