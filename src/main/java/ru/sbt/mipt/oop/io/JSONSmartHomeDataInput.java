package ru.sbt.mipt.oop.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONSmartHomeDataInput implements SmartHomeDataInput {
    private final String filePath;
    private final Gson gson;

    public JSONSmartHomeDataInput(String path) {
        filePath = path;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    public SmartHome readSmartHomeData() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException exception) {
            System.out.println("SmartHomeDataInputJSON::readSmartHomeData() can't read SmartHomeData.");
            exception.printStackTrace();
            return null;
        }
    }
}
