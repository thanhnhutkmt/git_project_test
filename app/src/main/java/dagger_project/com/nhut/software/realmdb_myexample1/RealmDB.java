package dagger_project.com.nhut.software.realmdb_myexample1;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class RealmDB {
    private Realm realm;
    private int cindex;

    public RealmDB(Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        cindex = -1;
    }

    public int getNewID(Class cl, boolean checkDB) {
        if (checkDB) {
            Number n = realm.where(cl).max("id");
            int index = (n == null) ? 0 : n.intValue() + 1;
            Log.i("MyTag", "index " + index);
            return index;
        } else
            return ++cindex;
    }

    public void insert(RealmModel rm) {
        realm.beginTransaction();
        realm.insert(rm);
        realm.commitTransaction();
    }

    public void insert(Collection<? extends RealmModel> col) {
        realm.beginTransaction();
        realm.insert(col);
        realm.commitTransaction();
    }

    public void deleteAll() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public <T> void updateRow(Class typeOfRow, String columnName, Object value,
        String[] editingColumnNames, Object[] newValue) {
        realm.beginTransaction();
        T d = (T) find(typeOfRow, columnName, value);
        if (d != null) {
            Method m = null;
            try {
                for (int i = 0; i < editingColumnNames.length; i++) {
                    m = typeOfRow.getDeclaredMethod(
                "set" + ("" + editingColumnNames[i].charAt(0)).toUpperCase()
                        + editingColumnNames[i].substring(1), newValue[i].getClass());
                    m.invoke(d, newValue[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            realm.insertOrUpdate((RealmModel) d);
        }
        realm.commitTransaction();
    }

    public Object find(Class cl, String columnName, Object obj) {
        if (obj instanceof Byte) {
            return realm.where(cl).equalTo(columnName, (Byte)obj).findFirst();
        } else if (obj instanceof Date) {
            return realm.where(cl).equalTo(columnName, (Date)obj).findFirst();
        } else if (obj instanceof Long) {
            return realm.where(cl).equalTo(columnName, (Long)obj).findFirst();
        } else if (obj instanceof Float) {
            return realm.where(cl).equalTo(columnName, (Float)obj).findFirst();
        } else if (obj instanceof Short) {
            return realm.where(cl).equalTo(columnName, (Short)obj).findFirst();
        } else if (obj instanceof Double) {
            return realm.where(cl).equalTo(columnName, (Double)obj).findFirst();
        } else if (obj instanceof String) {
            return realm.where(cl).equalTo(columnName, (String)obj).findFirst();
        } else if (obj instanceof Boolean) {
            return realm.where(cl).equalTo(columnName, (Boolean)obj).findFirst();
        } else if (obj instanceof Integer) {
            return realm.where(cl).equalTo(columnName, (Integer)obj).findFirst();
        } else if (obj instanceof byte[]) {
            return realm.where(cl).equalTo(columnName, (byte[])obj).findFirst();
        } else return null;
    }

    public <T> List<T> getAllData(Class cl) {
        RealmResults<T> pups = realm.where(cl).findAll();
        List<T> l = new ArrayList<>();
        for (T t : pups.subList(0, pups.size())) l.add(t);
        return l;
    }

    public void delete(Class<? extends RealmModel> obj) {
        realm.delete(obj);
    }

    public Realm getRealm() {
        return realm;
    }
}