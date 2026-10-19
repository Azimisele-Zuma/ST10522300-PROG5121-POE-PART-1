package azimiselezumapart1;

import static azimiselezumapart1.AzimiseleZumaPart1.name;
import static azimiselezumapart1.AzimiseleZumaPart1.password;
import static azimiselezumapart1.AzimiseleZumaPart1.surname;
import static azimiselezumapart1.AzimiseleZumaPart1.username;
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
        assertTrue(AzimiseleZumaPart1.checkUsername(input));
    }

    @Test
    public void testCheckUserNameInvalid() {
        Scanner input = new Scanner("kyle!!!!!!!\nkyl_1\n");
        assertTrue(AzimiseleZumaPart1.checkUsername(input));
    }

    @Test
    public void testCheckPasswordValid() {
        Scanner input = new Scanner("Ch&&sec@ke99!\n");
        assertTrue(AzimiseleZumaPart1.checkPasswordComplexity(input));
    }

    @Test
    public void testCheckPasswordInvalid() {
        Scanner input = new Scanner("password\nCh&&sec@ke99!\n");
        assertTrue(AzimiseleZumaPart1.checkPasswordComplexity(input));
    }

    @Test
    public void testRegisterUserSuccess() {
        String actualResponse = AzimiseleZumaPart1.registerUser("kyl_1", "Ch&&sec@ke99!", "name", "surname");
        assertEquals("The two above conditions have been met, and the user has been registered successfully.", actualResponse);
    }

    @Test
    public void testRegisterUserInvalidUsernameOutput() {
        String actualResponse = AzimiseleZumaPart1.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "name", "surname");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", actualResponse);
    }

    @Test
    public void testRegisterUserInvalidPasswordOutput() {
        String actualResponse = AzimiseleZumaPart1.registerUser("kyl_1", "password", "name", "surname");
        assertEquals("The password does not meet the complexity requirements.", actualResponse);
    }

    @Test
    public void testLoginSuccessful() {
        AzimiseleZumaPart1.registerUser("kyl_1", "Ch&&sec@ke99!", "name", "surname");
        Login login = new Login("name", "surname", "kyl_1", "Ch&&sec@ke99!");
        login.storedUsername = "kyl_1";
        login.storedPassword = "Ch&&sec@ke99!";
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("name", "surname", "kyl_1", "Ch&&sec@ke99!");
        assertFalse(login.loginUser("wrongUser", "wrongPass"));
    }

    @Test
    public void testReturnLoginStatusSuccessMessage() {
        Login login = new Login("name", "surname", "kyl_1", "Ch&&sec@ke99!");
        login.storedUsername = "kyl_1";
        login.storedPassword = "Ch&&sec@ke99!";
        boolean isLoggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String expectedMessage = "Welcome name surname it is great to see you again";
        String actualMessage = login.returnLoginStatus(isLoggedIn, "name", "surname");
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        Scanner scan = new Scanner("+27838968976\n");
        assertTrue(AzimiseleZumaPart1.checkCellphoneNumber(scan));
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        Scanner scan = new Scanner("08966553\n+27838968976\n");
        assertTrue(AzimiseleZumaPart1.checkCellphoneNumber(scan));
    }
}
