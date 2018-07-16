package dagger_project.com.nhut.software.realmdb_myexample1;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Dog extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private Integer age;

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

    @Override
    public String toString() {
        return "+Dog " + name + " : " + age + " \n";
    }
}


