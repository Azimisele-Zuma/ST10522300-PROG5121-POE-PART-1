/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package azimiselezumapart1;

/**
 *
 * @author Azimisele Zuma
 */
import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    // These variables store the information for one message.
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    // Keeps track of the next message number.
    private static int nextMessageNumber = 1;
    // Stores the user's selected action for the message.
    private int actionChoice;
    
    
    // Stores message IDs that have already been used. A HashSet helps prevent duplicate message IDs.
    private static final Set<String> usedMessageIDs = new HashSet<>();
    
     // Random is used to generate random message IDs.
    private static final Random random = new Random();
    
    // Stores messages that the user chooses to save.
    private static final ArrayList<Message> storedMessages = new ArrayList<>();
    
    // Stores messages that the user successfully sends.
    private static final ArrayList<Message> sentMessages = new ArrayList<>();
    
    
    public Message() {
        }
    // Checks whether the message ID contains exactly 10 digits.
    public boolean checkMessageID() {
    if (messageID != null && messageID.matches("\\d{10}")) {
        return true;
    } else {
        return false;
    }
}
    // Generates a unique, random 10-digit message ID.
    public String generateMessageID() {
    String generatedID;

    do {
        StringBuilder idBuilder = new StringBuilder();
        
        // Generate each of the 10 digits.
        for (int digit = 0; digit < 10; digit++) {
            idBuilder.append(random.nextInt(10));
        }

        generatedID = idBuilder.toString();
    // Generate another ID if this one has already been used.
    } while (!usedMessageIDs.add(generatedID));

    this.messageID = generatedID;

    return "Message ID generated: " + messageID;
}
     // Checks whether the message is not empty and does not exceed 250 characters.
    public String validateMessageLength(String text) {
    if (text != null && !text.trim().isEmpty()
            && text.length() <= 250) {
        return "Message ready to send.";
    } else if (text != null && text.length() > 250) {
        int excess = text.length() - 250;
        return "Message exceeds 250 characters by " + excess
                + "; please reduce the size.";
    } else {
        return "Please enter a message.";
    }
    }
// Creates a hash using the first two ID digits, message number, first word, and last word of the message.    
public String createMessageHash() {
     // A message ID and non-empty message text are required.
    if (messageID == null || messageText == null
            || messageText.trim().isEmpty()) {
        throw new IllegalStateException(
            "Message ID and message text are required."
        );
    }

    String[] words = messageText.trim().split("\\s+");
     // Get the first and last words.
    String firstWord = words[0];
    String lastWord = words[words.length - 1];
    
     // Combine the required information to create the hash.
    messageHash = messageID.substring(0, 2)
            + ":" + messageNumber
            + ":" + firstWord + lastWord;
     // Convert the hash to uppercase.
    messageHash = messageHash.toUpperCase();

    return messageHash;
}

       // Asks the user for a South African cellphone number and keeps asking until the number passes validation
       public String checkRecipientCell(Scanner input) {
       while (true) {
       System.out.println("Enter the recipient's cellphone number:");
       recipient = input.nextLine();
       
    // Check that the number starts with +27,has 12 characters, and contains digits after +27.
    if (recipient.startsWith("+27")&& recipient.length() == 12&& recipient.substring(3).matches("\\d+")) {

    return "Cell phone number successfully captured.";

    } else {
    System.out.println( "Cell phone number incorrectly formatted or does not contain an international code.");
           }
      }
    }
       
       // Validates a cellphone number that has already been provided.
       public String validateRecipientCell(String recipient) {
    if (recipient != null
            && recipient.startsWith("+27")
            && recipient.length() == 12
            && recipient.substring(3).matches("\\d+")) {

        return "Cell phone number successfully captured.";
    } else {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again";
    }
}
        // Main menu for the QuickChat messaging system.
        public void quickchat(Login login, Scanner  input){

        System.out.println("===== QuickChat =====");
            System.out.println("Welcome to QuickChat.");
            
            int choice = 0;
            
            // The menu repeats until the user chooses option 3.
            while(choice !=3){
                System.out.println("\nQuickChat Menu");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages.");
                System.out.println("3. Quit");
                System.out.println("Enter choice");
                
                if(input.hasNextInt()){
                    choice = input.nextInt();
                    input.nextLine();
                    
                   switch (choice) {

                        case 1:
                            // Ask how many messages the user wants to enter.
                            System.out.println(
                                "How many messages would you like to enter?"
                            );

                            if (input.hasNextInt()) {
                                int numberOfMessages = input.nextInt();
                                input.nextLine();

                                if (numberOfMessages > 0) {

                                    for (int i = 1;
                                         i <= numberOfMessages; i++) {
                                        

                                        
                                        //a new message object.
                                        Message message = new Message();
                                        String result = message.generateMessageID();
 // Give this message its number.
message.messageNumber = i;

System.out.println("Message attempt " + i);
System.out.println(result);
                                        
                                        
                                         // Stop this attempt if the ID is invalid.
                                        if (!message.checkMessageID()) {
                                        System.out.println("Invalid message ID.");
                                        continue;
                                            }
                                        
                                        // Ask for and validate the recipient's number.
                                        System.out.println(message.checkRecipientCell(input));
                                      
                        // Keep asking for text until it is valid.               
                        boolean validMessage = false;

                        while (!validMessage) {
                            System.out.println("Enter your message:");
                           
 // Accept messages that are not empty and contain no more than 250 characters.                          
message.messageText = input.nextLine();

String validationResult =
        message.validateMessageLength(message.messageText);

if (validationResult.equals("Message ready to send.")) {

    validMessage = true;

    System.out.println("Message Hash: "
            + message.createMessageHash());

    
    // Ask the user what to do with the message.
    boolean validAction = false;
    while(!validAction){
    System.out.println("\nChoose an action:");
    System.out.println("1. Send Message");
    System.out.println("2. Disregard Message");
    System.out.println("3. Store Message");
    System.out.print("Enter your choice: ");

    if (input.hasNextInt()) {
        message.actionChoice = input.nextInt();
        input.nextLine();

       
if (message.actionChoice >= 1 && message.actionChoice <= 3) {
    String actionResult = message.SentMessage();
   System.out.println(actionResult);
    validAction = true;
} else {
    System.out.println("Invalid action choice.");
}

    } else {
        System.out.println(
            "Invalid input. Please enter a number."
        );
        input.nextLine();
    }
    }

} else {
    System.out.println(
        "Please enter a message of less than 250 or fewer ."
    );
}

}
                                    }
                                  System.out.println("Total messages sent: "
        + Message.returnTotalMessages());
                                    

                                } else {
                                    System.out.println(
                                        "Please enter a number greater than 0."
                                    );
                                }

                            } else {
                                System.out.println(
                                    "Invalid input. Please enter a number."
                                );
                                input.nextLine();
                            }

                            break;
                            
    case 2:
    System.out.println("Coming Soon.");
    break;


                        case 3:
                            System.out.println(
                                "Thank you for using QuickChat."
                            );
                            break;

                        default:
                            System.out.println(
                                "Invalid choice. Please select 1, 2, or 3."
                            );
                            break;
                    }
                }else{
                    System.out.println("Invalid input.Please enter a number.");
                    input.nextLine();
                }
            }
        }

 // Performs the action selected by the user.
public String SentMessage() {
    if (actionChoice == 1) {
        sentMessages.add(this);
        return "Message successfully sent.";
    } else if (actionChoice == 2) {
        return "Press 0 to delete the message.";
    } else if (actionChoice == 3) {
        storeMessage();
        return "Message successfully stored.";
    } else {
        return "Invalid action choice.";
    }
}




// Returns the number of messages that have been sent.
public static int returnTotalMessages() {
    return sentMessages.size();
}


// Builds a readable report of all successfully sent messages.              
public static String printMessages() {
    StringBuilder messages = new StringBuilder();

    for (Message message : sentMessages) {
        messages.append("Message ID: ")
                .append(message.messageID).append("\n");
        messages.append("Message Number: ")
                .append(message.messageNumber).append("\n");
        messages.append("Recipient: ")
                .append(message.recipient).append("\n");
        messages.append("Message: ")
                .append(message.messageText).append("\n");
        messages.append("Message Hash: ")
                .append(message.messageHash).append("\n");
    }

    return messages.toString();
}




// Stores the message in memory and writes all stored messages to JSON.
public void storeMessage() {
    storedMessages.add(this);

    // Open messages.json and replace its contents with the stored messages.
    try (FileWriter writer = new FileWriter("messages.json")) {
        writer.write("[\n");

        
       //stored messages are written as a JSON object
        for (int i = 0; i < storedMessages.size(); i++) {
            Message message = storedMessages.get(i);

            writer.write("  {\n");
            writer.write("    \"messageID\": \"" + message.messageID + "\",\n");
            writer.write("    \"messageNumber\": " + message.messageNumber + ",\n");
            writer.write("    \"recipient\": \"" + message.recipient + "\",\n");
            writer.write("    \"messageText\": \"" + escapeJson(message.messageText) + "\",\n");
            writer.write("    \"messageHash\": \"" + message.messageHash + "\"\n");
            writer.write("  }");

            if (i < storedMessages.size() - 1) {
                writer.write(",");
            }

            writer.write("\n");
        }

        writer.write("]\n");

    } catch (IOException e) {
        // Display an error if the file cannot be written.
        System.out.println("Error saving message: " + e.getMessage());
    }
}
 // Avoids special characters so the message text can be written safely in JSON.
private String escapeJson(String text) {
    return text.replace("\\", "\\\\")
               .replace("\"", "\\\"")
               .replace("\n", "\\n")
               .replace("\r", "\\r")
               .replace("\t", "\\t");
}


}
    

