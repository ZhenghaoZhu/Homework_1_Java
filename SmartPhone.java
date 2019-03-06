//Zhenghao (Kevin) Zhu
//111736904
//zhenghao.zhu@stonybrook.edu
//HOMEWORK 1
//CSE 216
//R-03, Benjamin Michalowicz

import java.util.HashSet;
import java.util.Scanner;

public class SmartPhone extends Landline implements Computer{

  private int screenSize;
  private int RAM;
  private int processorSpeeed;
  private State smartPhoneState;
  HashSet<String> games;

  public SmartPhone(String owner, long phoneNumber, int screenSize, int RAM, int processorSpeeed, String smartPhoneState){
    super(owner, phoneNumber);
    this.screenSize = screenSize;
    this.RAM = RAM;
    this.processorSpeeed = processorSpeeed;
    setState(smartPhoneState);
    games = new HashSet<String>(5); // 5 game limit
  }

  public int getScreenSize(){
    return screenSize;
  }

  public int getRAM(){
    return RAM;
  }

  public int getProcessorSpeeed(){
    return processorSpeeed;
  }

  public State getState(){
    return smartPhoneState;
  }

  // Get call, check if P1 is turned on, then check if P2 is turned on. Check if P1 is available, check if P2 is available. If P1 is not available, print out statement. IF P2 is not available, message feature.
  @Override // Overwrote because SmartPhone has a STATE, need to check it before making a call.
  public void call(Phone phone){
    if(this.isBusy()){ // User can't call when already in a call.
      System.out.println("Phone is already in a call. Please end your call to continue.");
      return;
    }
    if(phone == this){ // User can't call itself.
      System.out.println("You can't call yourself");
      return;
    }
    if(this.getState() == State.OFF){ // User can't call when smartphone is OFF.
      System.out.println(getOwner() + "'s smartphone is not turned ON. Please turn ON to make calls.");
      return;
    }
    // P1 is not busy and P1 is turned ON.
    receiveCalledFromClass = true;
    phone.receive(this); // Checking if P2 is available, this is P1 and phone is P2
    return;
  }

  @Override
  public void receive(Phone from){ // this is P2, from is P1
    if(!receiveCalledFromClass){
      System.out.println("You should call instead.");
      return;
    }
    if(this.isBusy() && from.isBusy()){ // Both values are now changed
      System.out.println(from.getOwner() + " is on the phone with " + this.getOwner() + ".");
      return;
    }
    if(!(this.isBusy()) && (this.getState() == State.ON)){ // If P2 is available and ON, P2.receive(P1)
      this.callerPhone = from;
      this.lineOccupied = true; // P2 is now occupied
      this.callerPhoneNumber = from.number();
      this.setCallerName(from.getOwner()); // Saving P1's name
      from.receive(this); // Recursion? At this time of year? At this time of day? In this part of the country? Localized entirely within your CSE 216 Homework?
      return;
    }
    else{ // P2 is either busy, OFF, or both.
      Scanner in = new Scanner(System.in);
      if(this.isBusy()){
        System.out.println(from.getOwner() + " is unable to call " + this.getOwner() + ". Line is currently busy.");
      }
      else if(this.getState() == State.OFF){
        System.out.println(from.getOwner() + " is unable to call " + this.getOwner() + ". " + this.getOwner() + "'s phone is turned OFF.");
      }
      System.out.println("Does " + from.getOwner() + " want to leave a message? [y/n]");
      String msgInquiryAnswer = in.nextLine();
      if(msgInquiryAnswer.toLowerCase().equals("y")){
        System.out.println("Please input your message:");
        String newMessage = in.nextLine();
        this.callerMessages.add(newMessage); // Adding message
        this.msgsStatus.add(MSG_STATUS.UNREAD); // Updating new message status
        return;
      }
    }
    return;
  }
  

  public void setState(String to){
    to = to.toUpperCase();
    if(to.contentEquals("ON") || to.contentEquals("OFF")){
      State newState = State.valueOf(to); 
      smartPhoneState = newState;
      System.out.println(getOwner() + "'s smartphone is now turned " + getState() + ".");
      return;
    }
    throw new IllegalArgumentException("Wrong smartphone state.");
  }

  public void installGame(String gameName){
    if(smartPhoneState == State.OFF){
      System.out.println(getOwner() + "'s smartphone is not turned ON. Please turn smartphone ON to install games.");
      return;
    }
    gameName = gameName.toUpperCase();
    if(games.size() == 5){
      System.out.println("Memory full, unable to install " + gameName);
      return;
    }
    if(getState() == State.ON){
      if(hasGame(gameName)){
        return; // Silently do nothing when game is installed.
      }
      System.out.println("Installing " + gameName + " on " + getOwner() + "'s smartphone.");
      games.add(gameName); // Adding game, upper casing to take care of scenarios such as 'minecraft' vs. 'Minecraft'
    }
    else{
      System.out.println("The smartphone is not powered ON, unable to install " + gameName + ".");
    }
    return;
  }

  public boolean hasGame(String gameName){
    return (games.contains(gameName.toUpperCase())); // Hash set contains() method is O(1)
  }

  public void playGame(String gameName){
    if(smartPhoneState == State.OFF){
      System.out.println(getOwner() + "'s smartphone is not turned ON. Please turn smartphone ON to play games.");
      return;
    }
    gameName = gameName.toUpperCase();
    if(getState() == State.ON){
      if(!(hasGame(gameName))){
        String returnedMessage = "Cannot play " + gameName + " on " + getOwner() + " 's smartphone. Install it first.";
        System.out.println(returnedMessage);
        return;
      }
      else{
        String returnedMessage = getOwner() + " is playing " + gameName + ".";
        System.out.println(returnedMessage);
        return;
      }
    }
    else{
      System.out.println("The smartphone is not powered ON, unable to play " + gameName + ".");
    }
  }

  public void readMessages(MSG_STATUS status){
    if(smartPhoneState == State.ON){
      super.readMessages(status);
      return;
    }
    else{
      System.out.println("Please turn ON smartphone to access messages.");
      return;
    }
  }
}