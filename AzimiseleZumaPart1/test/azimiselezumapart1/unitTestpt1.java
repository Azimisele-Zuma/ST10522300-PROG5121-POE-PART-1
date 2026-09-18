/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package azimiselezumapart1;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

/**
 *
 * @author Azimisele Zuma
 */


public class unitTestpt1 {

    @Test
    public void testCheckUserNameValid() {
        Scanner input = new Scanner("kyl_1\n");
        assertEquals(true, AzimiseleZumaPart1.checkUsername(input));
    }

    @Test
    public void testCheckUserNameInvalid() {
        Scanner input = new Scanner("kyle!!!!!!!\nkyl_\n");
        assertEquals(true, AzimiseleZumaPart1.checkUsername(input));
    }

    @Test
    public void testCheckPasswordValid() {
        Scanner input = new Scanner("Ch&&sec@ke99!");
        assertEquals(true, AzimiseleZumaPart1.checkPasswordComplexity(input));
    }

    @Test
    public void testCheckPasswordInvalid() {
        Scanner input = new Scanner("password\nCh&&sec@ke99!\n");
        assertEquals(true, AzimiseleZumaPart1.checkPasswordComplexity(input));
    }

    @Test
    public void testRegisterUserSuccess() {
        String actualResponse = AzimiseleZumaPart1.registerUser("kyl_1", "Ch&&sec@ke99!", "name", "surname");
        assertEquals("The two above conditions have been met, and the user has been registered successfully.", actualResponse);
    }

    @Test
    public void testRegisterUserInvalidUsernameOutput() {
        AzimiseleZumaPart1.storedUsername = "kyle";
        String actualResponse = AzimiseleZumaPart1.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "name", "surname");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", actualResponse);
    }

    @Test
    public void testRegisterUserInvalidPasswordOutput() {
        AzimiseleZumaPart1.storedUsername = "kyl_1";
        AzimiseleZumaPart1.storedPassword = "pass";
        String actualResponse = AzimiseleZumaPart1.registerUser("kyl_1", "password", "name", "surname");
        assertEquals("The password does not meet the complexity requirements.", actualResponse);
    }

    @Test
    public void testLoginUserSuccess() {
        AzimiseleZumaPart1.registerUser("kyl_1", "Ch&&sec@ke99!", "name", "surname");
        Login login = new Login();
        boolean loginResult = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals(false,loginResult);
    }

    @Test
    public void testLoginUserFailure() {
        AzimiseleZumaPart1.registerUser("kyl_1", "Ch&&sec@ke99!", "name", "surname");
        Login login = new Login();
        boolean loginResult = login.loginUser("wrongUser", "wrongPass");
        assertEquals(false,loginResult);
    }

    @Test
    public void testReturnLoginStatusSuccessMessage() {
        
        AzimiseleZumaPart1.registerUser("kyl_1", "Ch&&sec@ke99!", "name", "surname");
        Login login = new Login();
        login.storedUsername = "kyl_1";
        login.storedPassword="Ch&&sec@ke99!";
        boolean isLoggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String expectedMessage = "Welcome name surname it is great to see you again";
        String actualMessage = login.returnLoginStatus(isLoggedIn, "name" , "surname");
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
public void testCheckCellPhoneNumberSuccess() {
    Scanner scan = new Scanner("+27838968976\n");
    boolean actualResponse = AzimiseleZumaPart1.checkCellphoneNumber(scan, "+27838968976");
    assertEquals(true, actualResponse);
}

@Test
public void testCheckCellPhoneNumberFailure() {
    Scanner scan = new Scanner("08966552\n+27838968976\n");
    boolean actualResponse = AzimiseleZumaPart1.checkCellphoneNumber(scan,"08966553");
    assertEquals(true, actualResponse);
}


    @Test
    public void testLoginSuccessful() {
        AzimiseleZumaPart1.registerUser("kyl_1", "Ch&&sec@ke99!", "name", "surname");
        Login login = new Login();
        login.storedUsername = "kyl_1";
        login.storedPassword = "Ch&&sec@ke99!";
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login();
        assertFalse(login.loginUser("wrongUser", "wrongPass"));
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        Scanner scan = new Scanner("kyl_1");
        boolean result = AzimiseleZumaPart1.checkUsername(scan);
        assertTrue(result);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Scanner scan = new Scanner("kyle!!!!!!!\nkyl_1\n");
        boolean result = AzimiseleZumaPart1.checkUsername(scan);
        assertTrue(result);
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Scanner scan = new Scanner("Ch&&sec@ke99!\nCh&&sec@ke99!\n");
        boolean result = AzimiseleZumaPart1.checkPasswordComplexity(scan);
        assertTrue(result);
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Scanner scan = new Scanner("password\nCh&&sec@ke99!\n");
        boolean result = AzimiseleZumaPart1.checkPasswordComplexity(scan);
        assertTrue(result);
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        Scanner scan = new Scanner("+27838968976\n");
        boolean result = AzimiseleZumaPart1.checkCellphoneNumber(scan, "+27838968976");
        assertTrue(result);
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        Scanner scan = new Scanner("08966553\n+2783968976\n");
        boolean result = AzimiseleZumaPart1.checkCellphoneNumber(scan, "08966553");
        assertTrue(result);
    }
}

   


        

    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

