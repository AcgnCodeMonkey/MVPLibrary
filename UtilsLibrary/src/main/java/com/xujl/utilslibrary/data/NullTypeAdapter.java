package com.xujl.utilslibrary.data;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * nullString处理(gson)
 *
 * @author Administrator
 */
public class NullTypeAdapter extends TypeAdapter<String> {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

        @Override
        public <T> TypeAdapter<T> create (Gson gson, TypeToken<T> type) {
            Class<?> rawType = type.getRawType();
            if (String.class != rawType) {
                return null;
            }
            return (TypeAdapter<T>) new NullTypeAdapter();
        }

    };

    @Override
    public String read (JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return "";
        }
        return reader.nextString();
    }

    @Override
    public void write (JsonWriter writer, String value) throws IOException {
        writer.value(value);
    }

}

