package ca.era.stride.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Map;


public class JsonPackager {

    /*
    take an object of any kind and jsonify it and outputs a string with
    the object as a json
     */
    public static String packData(Object dataToPack){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(dataToPack);
    }


    /*
    take an Json representation of an object and outputs a string with
    the object of a
     */
    public static Object unpackData(String dataToUnpack){
        Gson gson = new Gson();
        /*
        Chances are we won't have a 1:1 correspondence between server JSON representations of
        our patient or other models and the Android Java classes.
         */
        return gson.fromJson(dataToUnpack ,dataToUnpack.getClass());
    }

    public static Map<String,Object> unpackDataAsMap(String jsonLine){
        Map<String, Object> javaRootMapObject = new Gson().fromJson(jsonLine, Map.class);
        return javaRootMapObject;
    }
}
