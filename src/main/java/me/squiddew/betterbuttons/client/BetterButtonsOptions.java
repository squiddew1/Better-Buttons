package me.squiddew.betterbuttons.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class BetterButtonsOptions {

    public static boolean font = false;
    public static boolean showTitleScreenButton = true;
    private static final String fontStr = "font";
    private static final String showTitleScreenButtonStr = "showTitleScreenButton";
    private static final Gson GSON = new
            GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void save(){
        new Thread(() -> {
            Path configPath = getConfigPath();

            try (FileWriter writer = new FileWriter(String.valueOf(configPath))){
                JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("_comment_", "do not modify this file manually!");
                jsonObject.addProperty(fontStr, font);
                jsonObject.addProperty(showTitleScreenButtonStr, showTitleScreenButton);

                GSON.toJson(jsonObject, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "better-buttons-worker-thread")
                .start();
    }
    private static Path getConfigPath(){
        return FabricLoader
                .getInstance()
                .getConfigDir()
                .resolve("better-buttons-options.json");
    }
    public static void load(){
        Path configPath = getConfigPath();
        if (!configPath.toFile().exists()){
            save();
        } else {
            try (FileReader reader = new FileReader(configPath.toFile())){
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                if (jsonObject.has(fontStr)) font = jsonObject.get(fontStr).getAsBoolean();
                if (jsonObject.has(showTitleScreenButtonStr)) showTitleScreenButton = jsonObject.get(showTitleScreenButtonStr).getAsBoolean();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
