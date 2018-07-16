package dagger_project.com.nhut.software.realmdb_myexample1;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cat extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private Integer age;
    private Boolean sex;

    public static final Boolean FEMALE = false;
    public static final Boolean MALE = true;

    public Cat() {}

    public Cat(int id, String name, Integer age, Boolean sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "+Cat " + name + " : " +
                ((sex) ? "male" : "female")
                + " " + age + " \n";
    }
}