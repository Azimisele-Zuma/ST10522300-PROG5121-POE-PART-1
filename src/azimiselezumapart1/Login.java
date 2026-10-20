/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package azimiselezumapart1;

/**
 *
 * @author Azimisele Zuma
 */
public class Login {
    // Stores the user's personal details and login information
    public String name;
    public String surname;
    public String storedUsername;
    public String storedPassword;

    // Constructor to initialize user details
    public Login(String name, String surname, String storedUsername, String storedPassword) {
        this.name = name;
        this.surname = surname;
        this.storedUsername = storedUsername;
        this.storedPassword = storedPassword;
    }

    // Default no-argument constructor (if needed elsewhere in your project)
    public Login() {
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        // Checks if the entered username and password match the stored details
        return enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword);
    }

    public String returnLoginStatus(boolean isLoggedIn, String name, String surname) {
        // Returns a message based on whether the login was successful or unsuccessful
        if (isLoggedIn) {
            return "Welcome " + name + " " + surname + " it is great to see you again";
        }
        return "Username or password is incorrect please try again.";
    }
}

