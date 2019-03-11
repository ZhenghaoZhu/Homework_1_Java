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
  

  @Override // Overwrote because Landline has the message feature, which is implementedin receive().
  public void call(Phone phone){
    if(this.isBusy()){
      System.out.println("Phone is already in a call. Please end your call to continue.");
      return;
    }
    if(phone == this){
      System.out.println("You can't call yourself.");
      return;
    }
    phone.receive(this);
    return;
  }

  @Override
  public void receive(Phone from){
    if(this.isBusy() && from.isBusy()){
      System.out.println(from.getOwner() + " is on the phone with " + this.getOwner() + ".");
      return;
    }
    if(!(this.isBusy())){ // Phone calling the method is not occupied.
      this.callerPhone = from;
      this.lineOccupied = true; // P2 is now occupied
      this.callerPhoneNumber = from.number();
      this.setCallerName(from.getOwner()); // Saving P1's name
      from.receive(this); // Recursion? At this time of year? At this time of day? In this part of the country? Localized entirely within your CSE 216 Homework?
      return;
    }
    else{
      Scanner in = new Scanner(System.in);
      System.out.println(from.getOwner() + " is unable to call " + this.getOwner() + ".");
      System.out.println("Does " + from.getOwner() + " want to leave a message? [y/n]");
      String msgInquiryAnswer = in.nextLine();
      if(msgInquiryAnswer.toLowerCase().equals("y")){
        System.out.println("Please input your message:");
        String newMessage = in.nextLine();
        newMessage = from.getOwner() + ": " + newMessage;
        this.callerMessages.add(newMessage); // Adding message
        this.msgsStatus.add(MSG_STATUS.UNREAD); // Updating new message status
        return;
      }
    }
    return;
  }
  public void readMessages(){ // Prints out all messages
    if(isBusy()){
      System.out.println("You can't listen to messages when on a call.");
      return;
    }
    System.out.println(getOwner() + "'s requested messages:" + "\n");
    int msgCount = 0;
    for(int i = 0; i < callerMessages.size(); i++){
      msgCount++;
      System.out.println("Message #" + msgCount + " from "  + callerMessages.get(i));
    }
    if(msgCount == 0){
      System.out.println("[No messages.]");
    }
    System.out.println("\n");
    return;
  }

  public void readMessages(MSG_STATUS status){
    if(isBusy()){
      System.out.println("You can't listen to messages when on a call.");
      return;
    }
    System.out.println(getOwner() + "'s requested messages: " + "\n");
    int msgCount = 0;
    if(status == MSG_STATUS.UNREAD){ // enum parameter is UNREAD.
      for(int i = 0; i < msgsStatus.size(); i++){ // Prints out UNREAD messages.
        if(msgsStatus.get(i) == MSG_STATUS.UNREAD){
          msgCount++;
          System.out.println("Message #" + msgCount + " from "  + callerMessages.get(i));
          msgsStatus.set(i, MSG_STATUS.READ);
        }
      }
    }
    else{ // enum parameter is READ.
      for(int i = 0; i < callerMessages.size(); i++){ // Prints out ALL messages
        if(msgsStatus.get(i) == MSG_STATUS.READ){
          msgCount++;
          System.out.println("Message #" + msgCount + " from "  + callerMessages.get(i));
        }
      }
    }
    if(msgCount == 0){
      System.out.println("[No messages.]"); // If no messages were printed, print this.
    }
    msgCount = 0;
    System.out.println("\n");
    return;
  }

}