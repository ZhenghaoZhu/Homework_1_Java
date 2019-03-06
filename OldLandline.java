//Zhenghao (Kevin) Zhu
//111736904
//zhenghao.zhu@stonybrook.edu
//HOMEWORK 1
//CSE 216
//R-03, Benjamin Michalowicz


public class OldLandline implements Phone{
  // Can only make or receive a phone call.
  // Restrictions are:
    // More than one simultaneous calls cannot be made from an old landline.
    // More than one simulataneous calls cannot be received by an old landline.
    // If the line is not busy and a call comes in, it will be picked up.
    
    
    private String ownerName;
    private PhoneNumber ownerPhoneNumber;
    public PhoneNumber callerPhoneNumber;
    public String callerName;
    public Phone callerPhone;
    public boolean lineOccupied;
    public boolean receiveCalledFromClass;
    public boolean receiveEndSignalCalledFromClass;

    public OldLandline(String ownerName, long phoneNumber){
      ownerPhoneNumber = new PhoneNumber(phoneNumber);
      this.ownerName = ownerName;
      lineOccupied = false;
    }

    public String getOwner(){
      return ownerName;
    }

    public String getCallerName(){
      return callerName;
    }

    public void setCallerName(String callerName){
      this.callerName = callerName;
    }
    
    public void call(Phone phone){
      if(this.isBusy()){ // User can't call itself and can't call when already in a call.
        System.out.println("Phone is already in a call. Please end your call to continue.");
        return;
      }
      if(phone == this){
        System.out.println("You can't call yourself.");
      }
      if(phone.isBusy()){
        System.out.println(this.getOwner() + " is unable to call " + phone.getOwner() + ". Line is currently busy.");
        return;
      }
      receiveCalledFromClass = true;
      phone.receive(this);
      return;
    }

    public void end(){ 
      if(callerPhone == null){ // User is trying to end a call they are not in.
        System.out.println(this.getOwner() + " is not in a call.");
        return;
      }
      String returnedMessage  = getOwner() + " has ended the call to " + getCallerName() + "."; // Ending the call.
      receiveEndSignalCalledFromClass = true;
      callerPhone.receiveEndSignal(this); // Other user also ending the call.
      callerPhoneNumber = null; // Reseting variables to null
      setCallerName("");
      callerPhone = null;
      this.lineOccupied = false;
      System.out.println(returnedMessage); // Finalizing end call.
      return;
    }

    public void receive(Phone from){
      if(!receiveCalledFromClass){
        System.out.println("You should call instead.");
        return;
      }
      if(this.isBusy() && from.isBusy()){ // Both values are now changed
        System.out.println(from.getOwner() + " is on the phone with " + this.getOwner() + ".");
        return;
      }
      if(!(this.isBusy())){ // If P2 is available and ON, P2.receive(P1)
        this.lineOccupied = true; // P2 is now occupied
        this.callerPhone = from; // Saving P1
        this.callerPhoneNumber = from.number(); // Saving P1's phone number.
        this.setCallerName(from.getOwner()); // Saving P1's name
        from.receive(this); // Recursion
        return;
      }
      return;
    }

    public boolean isBusy(){
      return lineOccupied;
    }

    public void receiveEndSignal(Phone from){
      if(!receiveEndSignalCalledFromClass){
        System.out.println("You should end the call instead.");
        return;
      }
      this.callerPhoneNumber = null; // Reseting variables to null
      callerPhone = null;
      this.lineOccupied = false;
      setCallerName(""); // Other caller setting variable to null
      return;
    }

    public PhoneNumber number(){
      return ownerPhoneNumber;
    }

}