package Controller;

import Commons.FileCSV;
import Commons.validation.FunValidation;
import Moderm.User;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.Writer;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    private static ArrayList<User> listContact = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> list = new ArrayList<>();
    private static ArrayList<String> list1 = new ArrayList<>();
    private static ArrayList<String> list2 = new ArrayList<>();

    public static void displayMenu(){
        System.out.println("\n1. Show Contact" +
                "\n2. Add New User" +
                "\n3. Edit User" +
                "\n4. Delete User" +
                "\n5. Search User" +
                "\n6. Exit" +
                "\n. Please select one below: ");
        String choose = input.nextLine();
        switch (choose){
            case "1":
                showContact();
                break;
            case "2":
                addUser();
                break;
            case "3":
                editUser();
                break;
            case "4":
                deleteUser();
                break;
            case "5":
                searchUserContact();
                break;
            case "6":
                System.exit(0);
                break;
            default:
                System.out.println("Error! Please choose again! Enter to continue...");
                input.nextLine();
                displayMenu();
        }
    }

    private static void showContact() {
        getAllInformationContact();
        if (listContact.isEmpty()){
            System.out.println("Error! you are not information add! ");
        }
        System.out.println("Enter to continue....");
        input.nextLine();
        displayMenu();
    }

    private static void getAllInformationContact(){
        listContact = FileCSV.readFile();
        for (User user: listContact){
            System.out.println("-------------------");
            System.out.println("Phone Number: " + user.getPhoneNumber());
            System.out.println("Group contact: " + user.getGroupContact());
            System.out.println("Name: " + user.getName());
            System.out.println("Sex: " + user.getSex());
            System.out.println("Address: " + user.getAddress());
            System.out.println("-------------------");
        }
    }

    private static void addUser() {
        listContact = FileCSV.readFile();
        User user = new User();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = input.nextLine();
        while (!FunValidation.checkPhoneNumber(phoneNumber)){
            System.out.println("Phone number is invalid! Please try again: (exam: 0123456789 or +84123456789");
            phoneNumber = input.nextLine();
        }
        user.setPhoneNumber(phoneNumber);
        System.out.println("Enter Group Contact: ");
        user.setGroupContact(input.nextLine());
        System.out.println("Enter Name: ");
        user.setName(input.nextLine());
        System.out.println("Enter Male or Female: ");
        user.setSex(input.nextLine());
        System.out.println("Enter Address: ");
        user.setAddress(input.nextLine());
        System.out.println("Enter Birthday: ");
        user.setBirthday(input.nextLine());
        System.out.println("Enter Email: ");
        String email = input.nextLine();
        checkValidEmail(email, user);

        listContact.add(user);
        FileCSV.writerFile(listContact);

        System.out.println("Add new contact complete! Enter to continue...");
        input.nextLine();
        displayMenu();
    }
    private static void editUser() {
        getAllInformationContact();
        System.out.println("Please enter phone number you want edit information:");
        String choosePhoneEdit = input.nextLine();
        int count = 0;
        for (int i = 0; i < listContact.size(); i++){
            if (listContact.get(i).getPhoneNumber().equals(choosePhoneEdit)){
                System.out.println("1.Group contacts: " + listContact.get(i).getGroupContact());
                System.out.println("2.FullName: " + listContact.get(i).getName());
                System.out.println("3.Sex: " + listContact.get(i).getSex());
                System.out.println("4.Address: " + listContact.get(i).getAddress());
                System.out.println("5.Birthday: " + listContact.get(i).getBirthday());
                System.out.println("6.Email: " + listContact.get(i).getEmail());
                System.out.println("Please information you want edit: ");
                String chooseProperty = input.nextLine();
                System.out.println("Please enter data you want change: ");
                String value = input.nextLine();
                switch (chooseProperty){
                    case "1":
                        listContact.get(i).setGroupContact(value);
                        break;
                    case "2":
                        listContact.get(i).setName(value);
                        break;
                    case "3":
                        listContact.get(i).setSex(value);
                        break;
                    case "4":
                        listContact.get(i).setAddress(value);
                        break;
                    case "5":
                        listContact.get(i).setBirthday(value);
                        break;
                    case "6":
                        checkValidEmail(value, listContact.get(i));
                        break;
                }
                FileCSV.writerFile(listContact);
                System.out.println("Edit completed!");
                count++;
                System.out.println("Enter to continue...");
                input.nextLine();
                displayMenu();
            }
        }

        if (count == 0){
            System.out.println("not found");
        }
        count = 0;
        System.out.println("Enter to continue...");
        input.nextLine();
        displayMenu();
    }

    private static void checkValidEmail(String value, User user) {
        while (!FunValidation.checkEmail(value)) {
            System.out.println("Email is invalid! Please try again: ");
            value = input.nextLine();
        }
        user.setEmail(value);
    }

    private static void deleteUser() {
        getAllInformationContact();
        int count = 0;
        do {
            System.out.println("Please enter phone number you want delete: ");
            String choosePhoneDelete = input.nextLine();
            for (int i = 0; i < listContact.size(); i++) {
                if (listContact.get(i).getPhoneNumber().equalsIgnoreCase(choosePhoneDelete)) {
                    System.out.println("Bạn có chắc chắn muốn xóa thông tin danh bạ này không ? Nhập Y nếu bạn muốn xóa.");
                    String choose = input.nextLine();
                    String regex = "^[Y]$";
                    if (choose.toUpperCase().matches(regex)) {
                        listContact.remove(i);
                        FileCSV.writerFile(listContact);
                        System.out.println("Delete complete!");
                        count++;
                        break;
                    } else {
                        System.out.println("Enter to continue...");
                        input.nextLine();
                        displayMenu();
                    }
                }
            }
            if (count == 0) {
                System.out.println("not found");
            }
        } while (count == 0);
        count = 0;
        System.out.println("Enter to continue...");
        input.nextLine();
        displayMenu();
    }

    private static void searchUser() {
        getAllInformationContact();
        System.out.println("Enter User name: ");
        String chooseWordSearch = input.nextLine();
        String result = chooseWordSearch.toLowerCase();

        String regex = ".*" + result + ".*";
        for (int i = 0; i < listContact.size(); i++) {
            if (listContact.get(i).getName().matches(regex)) {
                list.add(listContact.get(i).getPhoneNumber());
                list.add(listContact.get(i).getGroupContact());
                list.add(listContact.get(i).getName());
                list.add(listContact.get(i).getSex());
                list.add(listContact.get(i).getAddress());
                list.add(listContact.get(i).getBirthday());
                list.add(listContact.get(i).getEmail());
            }
        }
    }

    public static void searchUserContact () {
        searchUser();

        if (list.size() != 0) {
            for (String s : list) {
                System.out.println(s);
            }
        } else {
            System.out.println("not found!!!");
        }
        list.clear();

        System.out.println("Enter to continue...");
        input.nextLine();
        displayMenu();
    }
}
