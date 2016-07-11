package net.vsona.orz.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by roy on 16/7/11.
 */
public class MyGson {
    public static Gson get() {
        return new GsonBuilder().create();
    }
}
