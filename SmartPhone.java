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
  public void call(Phone phone){
    if(phone == this || this.lineOccupied == true || this.smartPhoneState == State.OFF){ // User can't call itself and can't call when already in a call
      System.out.println("Call unable to happen.");
      return;
    }
    // P1 is not busy and P1 is turned ON.
    phone.receive(this); // Checking if P2 is available, this is P1 and phone is P2
    return;
  }
  public void receive(Phone from){ // this is P2, from is P1
    if(this.isBusy() && from.isBusy()){ // Both values are now changed
      return;
    }
    if(!(this.isBusy()) && (this.getState() == State.ON)){ // If P2 is available and ON, P2.receive(P1)
      this.callerPhone = from;
      this.lineOccupied = true; // P2 is now occupied
      this.callerPhoneNumber = from.number();
      this.setCallerName(from.getOwner()); // Saving P1's name
      from.receive(this);
      return;
    }
    else if(this.isBusy() || this.getState() == State.OFF){ // P2 is unavailable
      ;
    }
  }
  

  public void setState(String to){
    to = to.toUpperCase();
    if(!(to.equals("ON")) && !(to.equals("OFF"))){
      throw new IllegalArgumentException("Wrong smartphone state.");
    }
    State newState = State.valueOf(to); 
    smartPhoneState = newState;
    return;
  }

  public void installGame(String gameName){
    gameName = gameName.toUpperCase();
    if(games.size() == 5){
      System.out.println("Memory full, unable to install " + gameName);
      return;
    }
    if(getState() == Computer.State.ON){
      if(hasGame(gameName)){
        return; // Silently do nothing when game is installed.
      }
      System.out.println("Installing " + gameName + " on " + getOwner() + "'s smartphone.");
      games.add(gameName); // Adding game, upper casing to take care of scenarios such as 'minecraft' vs. 'Minecraft'
    }
    else{
      System.out.println("The smartphone is not powered on, unable to install " + gameName + ".");
    }
    return;
  }

  public boolean hasGame(String gameName){
    return (games.contains(gameName.toUpperCase())); // Hash set contains() method is O(1)
  }

  public void playGame(String gameName){
    gameName = gameName.toUpperCase();
    if(getState() == Computer.State.ON){
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
      System.out.println("The smartphone is not powered on, unable to play " + gameName + ".");
    }
  }
}