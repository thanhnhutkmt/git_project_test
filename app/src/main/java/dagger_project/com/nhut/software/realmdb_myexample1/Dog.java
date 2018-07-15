package dagger_project.com.nhut.software.realmdb_myexample1;

import io.realm.RealmObject;

public class Dog extends RealmObject {
    public String name;
    public int age;

    @Override
    public String toString() {
        return "+Dog " + name + " : " + age + " \n";
    }
}


