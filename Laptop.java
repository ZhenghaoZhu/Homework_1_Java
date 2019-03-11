//Zhenghao (Kevin) Zhu
//111736904
//zhenghao.zhu@stonybrook.edu
//HOMEWORK 1
//CSE 216
//R-03, Benjamin Michalowicz

import java.util.HashSet;

public class Laptop implements Computer{

  private int screenSize;
  private int RAM;
  private int processorSpeeed;
  private int gamesCapacity;
  private String laptopOwner;
  private String brand;
  private String hostname;
  private State laptopState;
  HashSet<String> games;



  public Laptop(String laptopOwner, String brand, String laptopState, int screenSize, int RAM, int processorSpeeed){ // Constructor
    this.laptopOwner = laptopOwner;
    this.brand = brand;
    setState(laptopState);
    this.screenSize = screenSize;
    this.RAM = RAM;
    this.processorSpeeed = processorSpeeed;
    this.hostname = this.laptopOwner + "'s " + this.brand + " laptop";
    this.gamesCapacity = 20; // Game capacity, no unlimited memory
    games = new HashSet<String>(gamesCapacity); // Laptops don't have unlimited storage
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

  public String getLaptopOwner(){
    return laptopOwner;
  }

  public String getBrand(){
    return brand;
  }

  public String getHostName(){
    return hostname;
  }

  public State getState(){
    return laptopState;
  }

  public void setState(String to){
    if(to.equals("on") || to.equals("off") || to.equals("ON") || to.equals("OFF")){ // Checking if its the right state
      State newState = State.valueOf(to.toUpperCase()); // Upper casing to match written state
      laptopState = newState;
      System.out.println(laptopOwner + "'s laptop is now turned " + getState() + ".");
      return;
    }
    throw new IllegalArgumentException("Wrong laptop state.");
  }

  public void installGame(String gameName){
    if(games.size() == gamesCapacity){
      System.out.println("Memory full, unable to install " + gameName);
      return;
    }
    if(getState() == State.ON){
      if(hasGame(gameName)){
        return; // Silently do nothing when game is installed.
      }
      System.out.println("Installing " + gameName + " on " + laptopOwner + "'s laptop.");
      games.add(gameName); // Adding game, upper casing to take care of scenarios such as 'minecraft' vs. 'Minecraft'
    }
    else{
      System.out.println("The laptop is not powered ON, unable to install " + gameName + ".");
    }
    return;
  }

  public boolean hasGame(String gameName){
    return (games.contains(gameName));
  }

  public void playGame(String gameName){
    if(getState() == State.ON){
      if(!(hasGame(gameName))){
        String returnedMessage = "Cannot play " + gameName + " on " + getLaptopOwner() + " 's laptop. Install it first.";
        System.out.println(returnedMessage);
        return;
      }
      else{
        String returnedMessage = getLaptopOwner() + " is playing " + gameName + ".";
        System.out.println(returnedMessage);
        return;
      }
    }
    else{
      System.out.println("The laptop is not powered ON, unable to play " + gameName + ".");
    }
  }
}