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

  @Override
  public void call(Phone phone){
    if(phone == this || this.isBusy()){
      System.out.println(getOwner() + "'s call unable to happen.");
      return;
    }
    receiveCalledFromClass = true;
    phone.receive(this);
    return;
  }

  @Override
  public void receive(Phone from){
    if(!receiveCalledFromClass){
      System.out.println("You should call instead.");
      return;
    }
    if(this.isBusy() && from.isBusy()){
      System.out.println(from.getOwner() + " is on the phone with " + this.getOwner());
      return;
    }
    if(!(this.isBusy())){ // Phone calling the method is not occupied.
      this.callerPhone = from;
      this.lineOccupied = true; // P2 is now occupied
      this.callerPhoneNumber = from.number();
      this.setCallerName(from.getOwner()); // Saving P1's name
      from.receive(this);
      return;
    }
    else{
      Scanner in = new Scanner(System.in);
      System.out.println(from.getOwner() + " is unable to call " + this.getOwner());
      System.out.println("Does " + from.getOwner() + " want to leave a message? [y/n]");
      String msgInquiryAnswer = in.nextLine();
      if(msgInquiryAnswer.equals("y")){
        System.out.println("Please input your message:");
        String newMessage = in.nextLine();
        this.callerMessages.add(newMessage); // Adding message
        this.msgsStatus.add(MSG_STATUS.UNREAD); // Updating new message status
        return;
      }
    }
    return;
  }

  public void readMessages(MSG_STATUS status){
    // Users can call and read messages at the same time.
    System.out.println(getOwner() + "'s requested messages: " + "\n");
    int count = 0;
    if(status == MSG_STATUS.UNREAD){
      for(int i = 0; i < msgsStatus.size(); i++){ // Prints out UNREAD messages.
        if(msgsStatus.get(i) == MSG_STATUS.UNREAD){
          System.out.println(callerMessages.get(i));
          msgsStatus.set(i, MSG_STATUS.READ);
          count++;
        }
      }
    }
    else{
      for(int i = 0; i < callerMessages.size(); i++){ // Prints out ALL messages
        System.out.println(callerMessages.get(i));
        msgsStatus.set(i, MSG_STATUS.READ);
        count++;
      }
    }
    if(count == 0){
      System.out.println("[No messages.]");
    }
    System.out.println("\n");
    return;
  }

}