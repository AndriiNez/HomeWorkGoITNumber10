import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ChangeInGSON {
    private static final String ABSOLUTE_PARTF =
            "C:\\goit\\goitJava\\IdeaProjects\\HomeWorkGoITNumber10\\textfolder\\file2.txt";
    private static final String ABSOLUTE_PARTF_IN_GSON =
            "C:\\goit\\goitJava\\IdeaProjects\\HomeWorkGoITNumber10\\textfolder\\user.json";

    private static List<User> readTextDok() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ABSOLUTE_PARTF))) {
            String line = reader.readLine();
            boolean isFirstLIne = true;
            String[] headers = null;

            while (line != null) {
                if (isFirstLIne) {
                    headers = line.split(" ");
                    isFirstLIne = false;
                } else {
                    String[] colums = line.split(" ");
                    User user = createUserFromColumns(headers, colums);
                    userList.add(user);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return userList;
    }

    private static User createUserFromColumns(String[] headers, String[] columns) {
        User user = new User();

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            String value = columns[i];

            if (header.equals("name")) {
                user.setName(value);
            } else if (header.equals("age")) {
                int age = Integer.parseInt(value);
                user.setAge(age);
            }
        }


        return user;
    }

    private static void writeUsersToJsonFile() {
        List<User> userList = readTextDok();
        File file = new File(ABSOLUTE_PARTF_IN_GSON);
        checkIfFileAvailebel(file);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userList);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkIfFileAvailebel(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    static class User {
        private String name;
        private int age;

        public User() {
            this.name = name;
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        writeUsersToJsonFile();
    }
}
