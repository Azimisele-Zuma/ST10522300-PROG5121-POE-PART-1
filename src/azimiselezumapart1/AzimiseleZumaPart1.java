/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package azimiselezumapart1;

import java.util.Scanner;

public class AzimiseleZumaPart1 {

    // Stores the username and password entered during registration.
    public static String storedUsername = "";
    public static String storedPassword = "";

    // Variables used to store the user's personal information.
    static String name;
    static String surname;
    static String username = "";
    static String password = "";
    static String cellphone;

    public static void main(String[] args) {

        // Creates a scanner object to allow the user to enter information.
        Scanner input = new Scanner(System.in);

        // Registration heading and personal details.
        System.out.println("========== REGISTRATION ==========");
        System.out.println("Please enter your personal details.");
       

        // Continuously asks the user to enter their name until valid.
        while (true) {
            System.out.println("Enter your name:");
            name = input.nextLine();

            if (!name.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty. Please try again.");
            }
        }

        // Continuously asks the user to enter their surname until valid.
        while (true) {
            System.out.println("Enter your surname:");
            surname = input.nextLine();

            if (!surname.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Surname cannot be empty. Please try again.");
            }
        }

        // Validates the username, password and cellphone number.
        checkUsername(input);
        checkPasswordComplexity(input);
        checkCellphoneNumber(input);

        // Registers the user and displays the registration result.
        System.out.println(registerUser(username, password, name, surname));

        // Creates a login object.
        Login login = new Login(name, surname, username, password);

        Message message = new Message();

        boolean isLoggedIn = false;

        // Login section.
        System.out.println("\n========== LOGIN PAGE ==========");
        System.out.println(" Enter your registered username, enter your registered password.");

        // Asks the user to log in until they successfully log in.
        while (!isLoggedIn) {

            System.out.println("Enter your username:");
            String enteredUsername = input.nextLine();

            System.out.println("Enter your password:");
            String enteredPassword = input.nextLine();

            // Checks whether the entered details are correct.
            isLoggedIn = login.loginUser(enteredUsername, enteredPassword);

            System.out.println(login.returnLoginStatus(isLoggedIn, name, surname));
        }

        message.quickchat(login, input);

        input.close();
    }

    // Checks whether the username meets the required conditions.
    public static boolean checkUsername(Scanner input) {

        boolean isValid = false;
        username = "";

        System.out.println("\n====================");
        System.out.println();
        System.out.println("Username requirements: Must contain an underscore (_), must not be longer than 5 characters.");

        // Keeps asking until a valid username is entered.
        while (true) {

            System.out.println("Enter your username:");
            username = input.nextLine();

            if (username.contains("_") && username.length() <= 5) {

                System.out.println("Username successfully captured.");
                System.out.println();
                storedUsername = username;
                isValid = true;
                break;

            } else {

                System.out.println("Username is not correctly formatted.");
                System.out.println("Please ensure that your username contains an underscore and is no longer than 5 characters.");
            }
        }

        return isValid;
    }

    // Checks whether the password meets the required complexity.
    public static boolean checkPasswordComplexity(Scanner input) {

        boolean isValid = false;
        password = "";

        
        System.out.println("Password requirements: At least 8 characters, one capital letter, one number, one special character.");

        // Keeps asking until a valid password is entered.
        while (!isValid) {

            System.out.println("Enter your password:");
            password = input.nextLine();

            boolean hasCapital = false;
            boolean hasNumber = false;
            boolean hasSpecial = false;

            // Checks each character in the password.
            for (int i = 0; i < password.length(); i++) {

                char ch = password.charAt(i);

                if (Character.isUpperCase(ch)) {
                    hasCapital = true;
                }

                if (Character.isDigit(ch)) {
                    hasNumber = true;
                }

                if (!Character.isLetterOrDigit(ch)) {
                    hasSpecial = true;
                }
            }

            // Checks whether all password requirements are met.
            if (password.length() >= 8 && hasCapital && hasNumber && hasSpecial) {

                System.out.println("Password successfully captured.");
                System.out.println();
                storedPassword = password;
                isValid = true;

            } else {

                System.out.println("Password is not correctly formatted.");
                System.out.println("Please check the requirements above and try again.");
            }
        }

        return isValid;
    }

    // Checks whether the cellphone number has the required format.
    public static boolean checkCellphoneNumber(Scanner input) {

        boolean isValid = false;

        
        System.out.println("Cellphone requirements: Must start with +27, exactly 9 digits after +27, no spaces or letters.");

        while (!isValid) {

            System.out.println("Enter your cellphone number:");
            cellphone = input.nextLine();

            // Checks the South African international cellphone format.
            String saPhoneRegex = "^\\+27[0-9]{9}$";

            if (cellphone != null && cellphone.matches(saPhoneRegex)) {

                System.out.println("Cellphone number successfully added.");
                System.out.println();
                isValid = true;

            } else {

                System.out.println("Cellphone number incorrectly formatted.");
                System.out.println("Please enter +27 followed by exactly 9 digits.");
            }
        }

        return isValid;
    }

    // Registers the user after checking the username and password.
    public static String registerUser(String username, String password, String name, String surname) {

        // Checks the username requirements.
        if (username == null || !username.contains("_") || username.length() > 5) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Checks the password requirements.
        if (password != null) {

            for (int i = 0; i < password.length(); i++) {

                char ch = password.charAt(i);

                if (Character.isUpperCase(ch)) {
                    hasCapital = true;
                }

                if (Character.isDigit(ch)) {
                    hasNumber = true;
                }

                if (!Character.isLetterOrDigit(ch)) {
                    hasSpecial = true;
                }
            }
        }

        if (password == null || password.length() < 8
                || !hasCapital || !hasNumber || !hasSpecial) {

            return "The password does not meet the complexity requirements.";
        }

        return "The two above conditions have been met, and the user has been registered successfully.";
    }
}
 