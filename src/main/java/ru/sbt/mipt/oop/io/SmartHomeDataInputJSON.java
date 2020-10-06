package ru.sbt.mipt.oop.io;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.objects.SmartHome;

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
    public SmartHome readSmartHomeData() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            SmartHome smartHomeData = gson.fromJson(json, SmartHome.class);
            return smartHomeData;
        } catch (IOException exception) {
            System.out.println("SmartHomeDataInputJSON::readSmartHomeData() can't read SmartHomeData.");
            exception.printStackTrace();
            return null;
        }
    }
}