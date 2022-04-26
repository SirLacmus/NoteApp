package ru.sirlacmus.note.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.sirlacmus.note.Note;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class JSONHandler {

    public static final String file = "notes.json";
    private static final Gson gson = new GsonBuilder().create();

    public static String serialize(Map<Integer, Note> map) {
        return gson.toJson(map);
    }

    public static Map<Integer, Note> deserialize(String string) {
        Type type = new TypeToken<Map<Integer, Note>>(){}.getType();
        return gson.fromJson(string, type);
    }

    public static Path getFile(String filePath) {
        return Paths.get(filePath).toAbsolutePath();
    }

    public static void save(Map<Integer, Note> notes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(serialize(notes));
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }

    public static Map<Integer, Note> read() {
        try {
            return deserialize(String.join("", Files.readAllLines(getFile(file))));
        } catch (IOException e) {
            System.out.println("IO Error");
        }
        return null;
    }
}
