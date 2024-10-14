package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExtractLists {

    public static void main(String[] args) {
        // Отримуємо URL файлу з resources
        URL resource = ExtractLists.class.getClassLoader().getResource("spaces.json");

        if (resource == null) {
            System.out.println("Файл не знайдено");
            return;
        }

        // Перетворюємо URL у файл
        File file = new File(resource.getFile());
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {
            // Парсинг JSON файлу
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray folders = (JSONArray) jsonObject.get("folders");

            // Знаходження всіх об'єктів "lists" і виведення їх ID
            for (Object folderObj : folders) {
                JSONObject folder = (JSONObject) folderObj;
                JSONArray lists = (JSONArray) folder.get("lists");

                if (lists != null) {
                    for (Object listObj : lists) {
                        JSONObject list = (JSONObject) listObj;
                        System.out.println("List ID: " + list.get("id"));
                    }
                }
            }

            // Виведення space.name та space.id для lists, де ім'я починається зі слова "test"
            for (Object folderObj : folders) {
                JSONObject folder = (JSONObject) folderObj;
                JSONArray lists = (JSONArray) folder.get("lists");

                if (lists != null) {
                    for (Object listObj : lists) {
                        JSONObject list = (JSONObject) listObj;
                        String listName = (String) list.get("name");

                        if (listName != null && listName.toLowerCase().startsWith("test")) {
                            JSONObject space = (JSONObject) folder.get("space");
                            System.out.println("Space Name: " + space.get("name"));
                            System.out.println("Space ID: " + space.get("id"));
                        }
                    }
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
