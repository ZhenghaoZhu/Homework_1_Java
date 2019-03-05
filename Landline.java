//Zhenghao (Kevin) Zhu
//111736904
//zhenghao.zhu@stonybrook.edu
//HOMEWORK 1
//CSE 216
//R-03, Benjamin Michalowicz

import java.util.ArrayList;
import java.util.Scanner;

public class Landline extends OldLandline{
  
  public ArrayList<String> callerMessages; // To store messages
  public ArrayList<MSG_STATUS> msgsStatus; // To store message UNREAD and READ statuses

  enum MSG_STATUS{ // Message enum
    UNREAD,
    READ
  }

  public Landline(String owner, long phoneNumber){
    super(owner, phoneNumber);
    callerMessages = new ArrayList<String>(); // No size as we don't know the number of messages that will be stored.
    msgsStatus = new ArrayList<MSG_STATUS>(); // Same logic as previous line.
  }

  public void receive(Phone from){
    if(!(this.lineOccupied)){ // Phone calling the method is not occupied.
      this.lineOccupied = true; // Set it to occupied
      setCallerName(from.getOwner()); // Set caller name
      return;
    }
    else{
      Scanner in = new Scanner(System.in);
      System.out.println("Does " + from.getOwner() + " want to leave a message? [y/n]");
      String msgInquiryAnswer = in.nextLine();
      if(msgInquiryAnswer.equals("y")){
        System.out.println("Please input your message:");
        String newMessage = in.nextLine();
        this.callerMessages.add(newMessage); // Adding message
        this.msgsStatus.add(MSG_STATUS.UNREAD); // Updating new message status
        return;
      }
      else{
        return;
      }
    }
  }

  public void readMessages(MSG_STATUS status){
    // Users can call and read messages at the same time.
    System.out.println(getOwner() + "'s requested messages: " + "\n");
    if(status == MSG_STATUS.UNREAD){
      for(int i = 0; i < msgsStatus.size(); i++){ // Prints out UNREAD messages.
        if(msgsStatus.get(i) == MSG_STATUS.UNREAD){
          System.out.println(callerMessages.get(i));
          msgsStatus.set(i, MSG_STATUS.READ);
        }
      }
    }
    else{
      for(int i = 0; i < callerMessages.size(); i++){ // Prints out ALL messages
        System.out.println(callerMessages.get(i));
        msgsStatus.set(i, MSG_STATUS.READ);
      }
    }
    System.out.println("\n");
    return;
  }

}