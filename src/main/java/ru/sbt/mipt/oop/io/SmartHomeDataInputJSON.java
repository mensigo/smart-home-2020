package ru.sbt.mipt.oop.io;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.general.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeDataInputJSON implements SmartHomeDataInput {
    private final String filePath;
    private final Gson gson;

    public SmartHomeDataInputJSON(String path) {
        filePath = path;
        gson = new Gson();
    }

    @Override
    public SmartHome getData() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return gson.fromJson(json, SmartHome.class);
    }
}
