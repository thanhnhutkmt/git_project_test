package dagger_project.com.nhut.software.realmdb_myexample1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.realm.RealmResults;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/*
    Author : Thanh Nhut
    Date   : July 2018
 */

public class MainActivity extends AppCompatActivity {
    Realm realm;
    TextView table;
    EditText nameet, nnameet;
    Button updateName, updateAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = (TextView) findViewById(R.id.table);
        nameet = (EditText) findViewById(R.id.name);
        nnameet = (EditText) findViewById(R.id.nname);
        updateName = (Button) findViewById(R.id.updatename);
        updateAge = (Button) findViewById(R.id.updateage);
        Dog dog = new Dog();
        dog.setId(0);
        dog.setName("Rex");
        dog.setAge(1);

        Dog dog1 = new Dog();
        dog1.setId(1);
        dog1.setName("Alaska");
        dog1.setAge(3);

        Dog dog2 = new Dog();
        dog2.setId(2);
        dog2.setName("TRex");
        dog2.setAge(4);

        Dog dog5 = new Dog();
        dog5.setId(3);
        dog5.setName("TRex1");
        dog5.setAge(5);

        Dog dog4 = new Dog();
        dog4.setId(4);
        dog4.setName("TRex2");
        dog4.setAge(6);

        List<Dog> listDogs = Arrays.asList(dog, dog1, dog2);

        Realm.init(this);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll(); // clean DB
        realm.copyToRealm(dog4);
        realm.insert(dog5);
        realm.insert(listDogs);
        realm.commitTransaction();
        getData();

        updateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Update name", Toast.LENGTH_SHORT).show();
                String oldname = nameet.getText().toString();
                updateDogName(nnameet.getText().toString(), oldname);
                getData();
            }
        });
        updateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Update age", Toast.LENGTH_SHORT).show();
                String oldname = nameet.getText().toString();
                updateDogAge((int)(Math.random() * 10), oldname);
                getData();
            }
        });
    }

    void updateDogName(String newName, String oldName) {
        realm.beginTransaction();
        Dog d = getDog(oldName);
        if (d != null) {
            d.setName(newName);
            realm.insertOrUpdate(d);
        }
        realm.commitTransaction();
    }

    void updateDogAge(int age, String oldName) {
        realm.beginTransaction();
        Dog d = getDog(oldName);
        if (d != null) {
            d.setAge(age);
            realm.insertOrUpdate(d);
        }
        realm.commitTransaction();
    }

    Dog getDog(String name) {
        return realm.where(Dog.class).equalTo("name", name).findFirst();
    }

    void getData() {
        RealmResults<Dog> pups = realm.where(Dog.class)
//            .lessThan("age", 10)
            .findAll();
        table.setText("Result \n");
        for (Dog d : pups.subList(0, pups.size()))
            table.append(d.toString());
    }
}
