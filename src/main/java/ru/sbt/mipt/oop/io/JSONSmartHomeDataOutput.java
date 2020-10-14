package ru.sbt.mipt.oop.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONSmartHomeDataOutput implements SmartHomeDataOutput {
    private final String filePath;
    private final Gson gson;

    public JSONSmartHomeDataOutput(String path) {
        filePath = path;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.setPrettyPrinting().create();
    }

    @Override
    public void writeSmartHomeData(SmartHome smartHome) {
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException exception) {
            System.out.println("SmartHomeDataOutputJSON::writeSmartHomeData(..) can't write SmartHomeData.");
            exception.printStackTrace();
        }
    }
}
