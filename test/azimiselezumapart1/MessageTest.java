package azimiselezumapart1;

// Import JUnit so we can create and run unit tests.
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;

public class MessageTest {

    
    // This helper method allows us to set private variables inside a Message object for testing purposes.
    private void setField(Message message, String fieldName,
                          Object value) throws Exception {
        Field field = Message.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        // Allow the test to access the private variable.
        field.set(message, value);
    }

    // Test 1: Check whether a message can be sent successfully.
    @Test
    public void testTask1SendMikeMessage() throws Exception {
 
        // Create a new Message object.
        Message message1 = new Message();

        // Set the message details for the test.
        setField(message1, "messageID", "1234567890");
        setField(message1, "messageNumber", 1);
        setField(message1, "recipient", "+27718693002");
        setField(message1, "messageText",
                "Hi Mike, can you join us for dinner tonight?");
        setField(message1, "actionChoice", 1);

        
        // Generate the hash
        String hash1 = message1.createMessageHash();
        assertEquals("12:1:HITONIGHT?", hash1);

        int totalBefore = Message.returnTotalMessages();

        String result1 = message1.SentMessage();

        assertEquals("Message successfully sent.", result1);
        
         // Check that the total number of sent messages increased by one.
        assertEquals(totalBefore + 1,
                Message.returnTotalMessages());
    }

    
     // Test 2: Check what happens when the user disregards a message.
    @Test
    public void testTask2DiscardKeeganMessage() throws Exception {

        Message message2 = new Message();

        
         // Set the details of Keegan's message.
        setField(message2, "messageID", "9876543210");
        setField(message2, "messageNumber", 2);
        setField(message2, "recipient", "08575975889");
        setField(message2, "messageText",
                "Hi Keegan, did you receive the payment?");
        setField(message2, "actionChoice", 2);

         // Generate the message
        String hash2 = message2.createMessageHash();
        assertEquals("98:2:HIPAYMENT?", hash2);

        int totalBefore = Message.returnTotalMessages();

        String result2 = message2.SentMessage();

        assertEquals("Press 0 to delete the message.", result2);
         // Confirm that disregarding the message does not increas the total number of sent messages.
        assertEquals(totalBefore,
                Message.returnTotalMessages());
    }
      // Test 3: Check whether a message within the character limit is accepted.
    @Test
    public void testMessageLengthSuccess() {
        Message message = new Message();

        
        String result = message.validateMessageLength("Hello Mike");

        assertEquals("Message ready to send.", result);
    }

     // Test 4: Check whether a message exceeding 250 characters is rejected.
    @Test
    public void testMessageLengthFailure() {
        Message message = new Message();
 // Build a message containing 251 characters.
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < 251; i++) {
            text.append("A");
        }

        String result = message.validateMessageLength(text.toString());

        assertEquals(
            "Message exceeds 250 characters by 1; please reduce the size.",
            result
        );
    }
    
    // Test 5: Check whether a correctly formatted cellphone number is accepted.
    @Test
public void testValidRecipientCell() {
    Message message = new Message();

    String result = message.validateRecipientCell("+27718693002");

    assertEquals("Cell phone number successfully captured.", result);
}
    // Test 6: Check whether an incorrectly formatted cellphone number is rejected.
@Test
public void testInvalidRecipientCell() {
    Message message = new Message();

    String result = message.validateRecipientCell("08575975889");

    assertEquals(
        "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again",
        result
    );
}

// Test 7: Check whether the message hash is generated correctly.
@Test
public void testMessageHashTestCase1() throws Exception {
    Message message = new Message();

    // Set the message details needed to create the hash.
    setField(message, "messageID", "0012345678");
    setField(message, "messageNumber", 0);
    setField(message, "messageText", "Hi Mike, see you tonight");

    String result = message.createMessageHash();

    assertEquals("00:0:HITONIGHT", result);
}

 // Test 8: Check multiple message hashes using a loop.
@Test
public void testRemainingMessageHashesInLoop() throws Exception {

    String[] messageIDs = {
        "1234567890",
        "9876543210",
        "4512345678"
    };

    int[] messageNumbers = {1, 2, 3};

    String[] messageTexts = {
        "Hi Mike, see you tonight",
        "Hello Keegan, enjoy your day",
        "Hi Zuma, see you tomorrow"
    };

    String[] expectedHashes = {
        "12:1:HITONIGHT",
        "98:2:HELLODAY",
        "45:3:HITOMORROW"
    };

    for (int i = 0; i < messageIDs.length; i++) {

        Message message = new Message();

        setField(message, "messageID", messageIDs[i]);
        setField(message, "messageNumber", messageNumbers[i]);
        setField(message, "messageText", messageTexts[i]);

        String result = message.createMessageHash();

        assertEquals(expectedHashes[i], result);
    }
}

    // Test 9: Check whether a message ID containing 10 digits is valid.
@Test
public void testMessageIDIsValid() throws Exception {
    Message message = new Message();

    setField(message, "messageID", "1234567890");

    assertTrue(message.checkMessageID());
}

    // Test 10: Check whether a message ID with too few digits is invalid.
@Test
public void testMessageIDIsInvalid() throws Exception {
    Message message = new Message();

    setField(message, "messageID", "12345");

    assertFalse(message.checkMessageID());
}

}


