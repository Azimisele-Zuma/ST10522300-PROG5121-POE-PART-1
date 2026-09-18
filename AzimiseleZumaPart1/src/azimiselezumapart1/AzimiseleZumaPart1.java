/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

//package name
package azimiselezumapart1;

/**
 *
 * @author Azimisele Zuma
 */
import java.util.Scanner;
public class AzimiseleZumaPart1 {
    //Stores the username and password enetered by the user during registration
    public static String storedUsername = "";
    public static String storedPassword = "";
    
    //Variables used to store the users personal information
         static String name;
         static String surname;
         static String username= "";
         static String password = "";
         static String cellphone;
        
    public static void main(String[] args) {
        
        //Creates a scanner object to allow the user to enter information
        Scanner input = new Scanner(System.in);
        
        //Continously asks the user to enter their name until a valid name is entered 
        while(true){
        System.out.println("Enter your name");
        name = input.nextLine();
        //Checks that the name is not empty or have any spaces 
        if(!name.trim().isEmpty()){
            break;
        }  
    }
        
        //Continously asks the user to enter their surname until a valid name is entered 
        while(true){
        System.out.println("Enter your surname");
        surname = input.nextLine();
        //Checks that the surname is not empty or have any spaces 
        if(!surname.trim().isEmpty()){
            break;
        }    
    }
        //Methods within this program,Validate the username password and cellphone number
        checkUsername(input);
        checkPasswordComplexity(input);
        checkCellphoneNumber(input, cellphone);
        //Register the user and display the registration result
        System.out.println(registerUser(username,password,name,surname));
        //Creates a login object
        Login login = new Login();
        //Stores the registered user details for the login class
        login.name = name;
        login.surname = surname;
        login.storedUsername = username;
        login.storedPassword = password;

        boolean isLoggedIn = false;

        //Asks the user to login their details until they successfullylog in 
        System.out.println("===== LOGIN PAGE =====");
        while(!isLoggedIn){
        System.out.println("Enter your username:");
        String enteredUsername = input.nextLine();
        System.out.println("Enter your password:");
        String enteredPassword = input.nextLine();
        //Check if the entered username and password are correct
        isLoggedIn = login.loginUser(enteredUsername, enteredPassword);
        System.out.println(login.returnLoginStatus(isLoggedIn, name, surname));    
    }
        input.close();
    }
    
    //Checs if the username meets the required conditions
    public static boolean checkUsername(Scanner input){
        System.out.println("======Registration======");
        boolean isValid = false;
        username = "";
        
        //Keeps asking until a vaild useranme is entered
        while(true){
        System.out.println("Enter your username");
        username = input.nextLine();
        
        if(username.contains("_") && username.length()<=5){
            //Confirms that the username was successfully captured 
            System.out.println("Username successfully captured");
            storedUsername = username;
            isValid = true;
            break;
        }else{
        //States that the username was incorrectly formatted 
        System.out.println("Username is not correctly formatted,Please ensure that your username contains an underscore and is no longer than 5 characters in lenght");
        
        }
       
    }
        return isValid;
    }
    
    
    public static boolean checkPasswordComplexity(Scanner input){
        boolean isValid = false;
        password = "";
        
        //Keeps asking for until a valid password is entered
        while(!isValid){
        System.out.println("Enter your password");
        password = input.nextLine();
        
     
        boolean hasCapital=false;
        boolean hasNumber=false;
        boolean hasSpecial=false;
        
        //Checks each character to see if the password meets the requiremnets
        for (int i = 0; i < password.length(); i++){
            
        char ch = password.charAt(i);
        
        if(Character.isUpperCase(ch)){
           hasCapital=true;
        }
        if(Character.isDigit(ch)){
            hasNumber=true;   
        }
        if(!Character.isLetterOrDigit(ch)){
            hasSpecial=true;
        }
       }
        //Conditions the password has to meet to be successful
        if(password.length()>=8 && hasCapital && hasNumber && hasSpecial){
            System.out.println("Password successfully captured");
            storedPassword = password;
            isValid = true;  
        }else{
            //Outputted if conditions of the password arent met
            System.out.println("Password is not correctly formatted;please ensure that the password contains at least eight characters,a capital letter, a number, and a special character ");
        }
        }
        return isValid;
    }
     
    //Checks whether the cellphone number has the required format
     public static boolean checkCellphoneNumber(Scanner input, String cellphone){
         boolean isValid = false;
         
         //Kepep asking until a valid cellphone number is entered 
         while(true){ 
         System.out.println("Enter your cellphone number");
         cellphone = input.nextLine();
         
         //Conditions the cellphone number has to meet to be successful
         if (cellphone.startsWith("+27") && cellphone.length() <=13){
             System.out.println("Cellphone number successfully added");
             isValid = true;
             break;
         }else{
             System.out.println("Cellphone number incorrectly formatted or does not contain international code");
         }
     }
        return isValid; 
     }
     public static String registerUser(String username, String password, String name, String surname) {
         
         //Validates that the usernamemeets the required format
    if (!storedUsername.contains("_") && storedUsername.length() <=5) {
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    } 
    
    boolean hasCapital = false;
    boolean hasNumber = false;
    boolean hasSpecial = false;
    
    //Check the password for capital letter, number and special character
    for (int i = 0; i < storedPassword.length(); i++) {
        char ch = storedPassword.charAt(i);
        if (Character.isUpperCase(ch)) hasCapital = true;
        if (Character.isDigit(ch)) hasNumber = true;
        if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
    }
    //Check whether the password meets the complexity requiremnets
    if (storedPassword.length() < 8 && !hasCapital && !hasNumber && !hasSpecial) {
        return "The password does not meet the complexity requirements.";
    } 

    return "The two above conditions have been met, and the user has been registered successfully.";
}
     
}
