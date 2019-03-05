//Zhenghao (Kevin) Zhu
//111736904
//zhenghao.zhu@stonybrook.edu
//HOMEWORK 1
//CSE 216
//R-03, Benjamin Michalowicz

public class PhoneNumber{
  
  private final long phoneNumber;

  public PhoneNumber(long phoneNumber){
    String phoneNumberString = Long.toString(phoneNumber);
    if(phoneNumberString.length() != 10){
      throw new IllegalArgumentException("Phone Number is not correct length.");
    }
    this.phoneNumber = phoneNumber;
  }

  public String toString(){ // Overrides toString method to ouput phoneNumber instead the object's hash code
    return Long.toString(phoneNumber);
  }
}