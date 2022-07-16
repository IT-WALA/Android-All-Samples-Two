package ghanekar.vaibhav.allsamples2.fragments.sqlite.room;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StringConverters {
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /*@TypeConverter
    public static ArrayList<SqlitePojo.Addresses> addressesFromString(String value) {
        Type listType = new TypeToken<ArrayList<RoomSqlitePojo.Addresses>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String addresFromArrayList(ArrayList<RoomSqlitePojo.Addresses> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }*/
}