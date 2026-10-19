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
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int nextMessageNumber = 1;
    private int actionChoice;
    
    private static final Set<String> usedMessageIDs = new HashSet<>();
    private static final Random random = new Random();
    
    private static final ArrayList<Message> storedMessages = new ArrayList<>();
    private static final ArrayList<Message> sentMessages = new ArrayList<>();
    
    
    public Message() {
        }
    
    public boolean checkMessageID() {
    if (messageID != null && messageID.matches("\\d{10}")) {
        return true;
    } else {
        return false;
    }
}
    public String generateMessageID() {
    String generatedID;

    do {
        StringBuilder idBuilder = new StringBuilder();

        for (int digit = 0; digit < 10; digit++) {
            idBuilder.append(random.nextInt(10));
        }

        generatedID = idBuilder.toString();

    } while (!usedMessageIDs.add(generatedID));

    this.messageID = generatedID;

    return "Message ID generated: " + messageID;
}
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
    
public String createMessageHash() {
    if (messageID == null || messageText == null
            || messageText.trim().isEmpty()) {
        throw new IllegalStateException(
            "Message ID and message text are required."
        );
    }

    String[] words = messageText.trim().split("\\s+");

    String firstWord = words[0];
    String lastWord = words[words.length - 1];

    messageHash = messageID.substring(0, 2)
            + ":" + messageNumber
            + ":" + firstWord + lastWord;

    messageHash = messageHash.toUpperCase();

    return messageHash;
}

      
       public String checkRecipientCell(Scanner input) {
       while (true) {
       System.out.println("Enter the recipient's cellphone number:");
       recipient = input.nextLine();

    if (recipient.startsWith("+27")&& recipient.length() == 12&& recipient.substring(3).matches("\\d+")) {

    return "Cell phone number successfully captured.";

    } else {
    System.out.println( "Cell phone number incorrectly formatted or does not contain an international code.");
           }
      }
    }
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

        public void quickchat(Login login, Scanner  input){

        System.out.println("===== QuickChat =====");
            System.out.println("Welcome to QuickChat.");
            
            int choice = 0;
            
            while(choice !=3){
                System.out.println("\nQuickChat Menu");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                System.out.println("Enter choice");
                
                if(input.hasNextInt()){
                    choice = input.nextInt();
                    input.nextLine();
                    
                   switch (choice) {

                        case 1:
                            System.out.println(
                                "How many messages would you like to enter?"
                            );

                            if (input.hasNextInt()) {
                                int numberOfMessages = input.nextInt();
                                input.nextLine();

                                if (numberOfMessages > 0) {

                                    for (int i = 1;
                                         i <= numberOfMessages; i++) {
                                        

                  

                                        
                                        

                                        Message message = new Message();
                                        String result = message.generateMessageID();

message.messageNumber = nextMessageNumber++;

System.out.println("Message attempt " + i);
System.out.println(result);
                                        
                                        
                                        
                                        if (!message.checkMessageID()) {
                                        System.out.println("Invalid message ID.");
                                        continue;
                                            }
                                        
                                    
                                        System.out.println(message.checkRecipientCell(input));
                                      
                                        
                        boolean validMessage = false;

                        while (!validMessage) {
                            System.out.println("Enter your message:");
                            message.messageText = input.nextLine();

                           
if (message.messageText.length() <=250
        && !message.messageText.trim().isEmpty()) {

    validMessage = true;

    System.out.println("Message Hash: "
            + message.createMessageHash());

    
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
        "Please enter a message of less than 250 or fewer characters."
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
    System.out.println("Recently Sent Messages:");

    if (sentMessages.isEmpty()) {
        System.out.println("No messages have been sent yet.");
    } else {
        System.out.println(printMessages());
    }
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





public static int returnTotalMessages() {
    return sentMessages.size();
}


                
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





public void storeMessage() {
    storedMessages.add(this);

    try (FileWriter writer = new FileWriter("messages.json")) {
        writer.write("[\n");

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
        System.out.println("Error saving message: " + e.getMessage());
    }
}

private String escapeJson(String text) {
    return text.replace("\\", "\\\\")
               .replace("\"", "\\\"")
               .replace("\n", "\\n")
               .replace("\r", "\\r")
               .replace("\t", "\\t");
}


}
    

