package dagger_project.com.nhut.software.realmdb_myexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/*
    Author : Thanh Nhut
    Date   : July 2018
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView table = (TextView) findViewById(R.id.table);
        Dog dog = new Dog();
        dog.name = "Rex";
        dog.age = 1;
        Dog dog1 = new Dog();
        dog1.name = "Alaska";
        dog1.age = 3;
        Dog dog2 = new Dog();
        dog2.name = "TRex";
        dog2.age = 4;

        List<Dog> listDogs = Arrays.asList(dog, dog1, dog2);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll(); // clean DB
        realm.copyToRealm(dog2);
        realm.insert(dog1);
        realm.insert(listDogs);
        realm.commitTransaction();

        RealmResults<Dog> pups = realm.where(Dog.class)
            .lessThan("age", 10)
            .findAll();
        table.setText("Result \n");
        for (Dog d : pups.subList(0, pups.size()))
            table.append(d.toString());
    }
}