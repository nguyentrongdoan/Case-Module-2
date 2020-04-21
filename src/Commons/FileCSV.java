package Commons;

import Moderm.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileCSV {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String fileContact = "src/Data/Contact.csv";
    private static final String FILE_HEADER = "phoneNumber, groupContact, name, sex, address, birthday, email";
    public static void writerFile(ArrayList<User> listContact){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileContact);
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            for (User user: listContact){
                fileWriter.append(user.getPhoneNumber());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(user.getGroupContact());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(user.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(user.getSex());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(user.getAddress());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(user.getBirthday());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(user.getEmail());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (Exception e) {
            System.out.println("Error in writer file");
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            }catch (Exception ex){
                System.out.println("Error when flush or close!");
            }
        }
    }
    public static ArrayList<User> readFile(){
        BufferedReader bufferedReader = null;
        ArrayList<User> listContact = new ArrayList<>();
        Path path = Paths.get(fileContact);
        if (!Files.exists(path)){
            try {
                Writer writer = new FileWriter(fileContact);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        try {
            String line;
             bufferedReader = new BufferedReader(new FileReader(fileContact));

            while ((line = bufferedReader.readLine()) != null){
                String[] splitData = line.split(",");
                if (splitData[0].equals("phoneNumber")){
                    continue;
                }
                User user = new User();
                user.setPhoneNumber(splitData[0]);
                user.setGroupContact(splitData[1]);
                user.setName(splitData[2]);
                user.setSex(splitData[3]);
                user.setAddress(splitData[4]);
                user.setBirthday(splitData[5]);
                user.setEmail(splitData[6]);
                listContact.add(user);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                bufferedReader.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return listContact;
    }
}
