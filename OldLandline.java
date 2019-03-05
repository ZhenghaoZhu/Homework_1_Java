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
      if(phone == this || this.lineOccupied == true){ // User can't call itself and can't call when already in a call.
        System.out.println("Call unable to happen.");
        return;
      }
      if(!phone.isBusy() && !(this.isBusy())){
        lineOccupied = true;
        callerPhone = phone;
        callerName = callerPhone.getOwner();
        callerPhone.receive(this);
        String returnedMessage = this.getOwner() + " is on the phone with " + phone.getOwner();
        System.out.println(returnedMessage);
        
      }
      else{
        System.out.println(this.getOwner() + " is unable to call " + phone.getOwner() + ". Line is currently busy.");
        phone.receive(this);
      }
      return;
      
    }

    public void end(){ 
      String returnedMessage  = getOwner() + " has ended the call to " + getCallerName();
      callerPhone.receiveEndSignal(this);
      setCallerName("");
      this.lineOccupied = false;
      System.out.println(returnedMessage);
      return;
    }

    public void receive(Phone from){
      this.callerPhone = from;
      this.lineOccupied = true;
      this.callerPhoneNumber = from.number();
      setCallerName(from.getOwner());
    }

    public boolean isBusy(){
      return lineOccupied;
    }

    public void receiveEndSignal(Phone from){
      lineOccupied = false;
      setCallerName("");
    }

    public PhoneNumber number(){
      return ownerPhoneNumber;
    }

}