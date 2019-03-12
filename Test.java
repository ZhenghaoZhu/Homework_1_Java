//Zhenghao (Kevin) Zhu
//111736904
//zhenghao.zhu@stonybrook.edu
//HOMEWORK 1
//CSE 216
//R-03, Benjamin Michalowicz

import java.util.ArrayList;
import java.util.Collections;

public class Test{
  public static void main(String[] args) {
    System.out.println("============================PhoneNumber Test============================");

    PhoneNumber pn1 = new PhoneNumber(5647035639L);
    try{
      PhoneNumber pn2 = new PhoneNumber(76470356390L);
    } catch (Exception e){
      System.out.println("Caught exception for too long of a long.");
    }

    try{
      PhoneNumber pn3 = new PhoneNumber(364703563L);
    } catch (Exception e){
      System.out.println("Caught exception for too short of a long.");
    }
    PhoneNumber pn4 = new PhoneNumber(1647035639L);
    PhoneNumber pn5 = new PhoneNumber(2647035639L);

    System.out.println("========================================================================\n");
    System.out.println("============================OldLandLine Test============================");
    
    OldLandline ol1 = new OldLandline("ol1", 1234567890L);
    OldLandline ol2 = new OldLandline("ol2", 2234567890L);
    OldLandline ol3 = new OldLandline("ol3", 3234567890L);
    OldLandline ol4 = new OldLandline("ol4", 4234567890L);

    ol1.call(ol2);
    ol2.call(ol3);
    ol3.call(ol1);
    ol2.end();
    ol4.call(ol3);
    ol4.end();
    
    System.out.println("========================================================================\n");

    System.out.println("============================LandLine Test============================");

    Landline l1 = new Landline("l1", 4678324041L);
    Landline l2 = new Landline("l2", 1678324041L);
    OldLandline oll1 = new Landline("oll1", 2344567894L); // Real and apparent type test
    Phone pl1 = new OldLandline("pl1", 5674823451L);
    Landline l3 = new Landline("l3", 2678324041L);
    Landline l4 = new Landline("l4", 3678324041L);
    Landline l5 = new Landline("l5", 5678324041L);

    oll1.call(l2);
    l1.call(l2);
    l3.call(l2);
    l3.call(oll1);
    l4.call(oll1);
    l4.call(l2);
    l5.call(l4);
    l3.end();
    l5.end();
    pl1.call(l1);
    l2.end();
    l5.call(l2);
    l2.readMessages(Landline.MSG_STATUS.READ);
    l2.end();
    l2.readMessages();
    ((Landline) oll1).readMessages(); // Real and apparent type test

    System.out.println("=====================================================================\n");

    System.out.println("============================SmartPhone Test============================");

    OldLandline spoll1 = new SmartPhone("spoll1", 8769502341L, 320, 4, 4, "ON");
    Landline spoll2 = new SmartPhone("spoll2", 8769504341L, 320, 4, 4, "ON");
    SmartPhone sp1 = new SmartPhone("sp1", 8764323324L, 420, 5, 6, "OFF");
    try{
      SmartPhone sp2 = new SmartPhone("sp2", 1764323324L, 420, 6, 6, "ofF");
    } catch (Exception e){
      System.out.println("SmartPhone exception caught.");
    }
    SmartPhone sp2 = new SmartPhone("sp2", 1764323324L, 420, 1, 6, "off");
    SmartPhone sp3 = new SmartPhone("sp3", 2764323324L, 420, 2, 6, "on");
    SmartPhone sp4 = new SmartPhone("sp4", 3764323324L, 420, 3, 6, "on");
    Computer csp1 = new SmartPhone("csp1", 3452134527L, 540, 4, 8, "on");

    sp2.call(sp1);
    sp3.call(sp4);
    sp2.setState("ON");
    sp2.call(sp1);
    sp1.end();
    sp1.setState("ON");
    sp1.installGame("Minecraft");
    sp1.playGame("Fortnite");
    sp1.installGame("Fortnite");
    sp1.playGame("Fortnite");
    sp1.installGame("League of Legends");
    sp1.installGame("Terraria");
    sp1.installGame("Game 1");
    sp1.installGame("Game 2"); // Unable to install, memory full
    sp2.readMessages();
    spoll1.end();
    ((SmartPhone) spoll1).readMessages(); // Real and apparent type test
    spoll1 = (SmartPhone) spoll1;
    ((SmartPhone) spoll1).installGame("Minecraft"); // Real and apparent type test
    ((SmartPhone) spoll1).playGame("Minecraft"); // Real and apparent type test
    ((Landline) csp1).call(sp1); // Real and apparent type test
    ((Landline) csp1).readMessages(); // Real and apparent type test

    System.out.println("=====================================================================\n");

    System.out.println("============================Laptop Test============================");

    Laptop lp1 = new Laptop("lp1", "Dell", "ON", 520, 10, 3);
    Laptop lp2 = new Laptop("lp2", "Asus", "ON", 620, 10, 3);
    Laptop lp3 = new Laptop("lp3", "Alienware", "ON", 720, 10, 3);
    Laptop lp4 = new Laptop("lp4", "HP", "ON", 820, 10, 3);
    Laptop lp5 = new Laptop("lp5", "Samsung", "ON", 920, 10, 3);
    Laptop lp6 = new Laptop("lp6", "Apple", "ON", 520, 10, 3);
    Computer cl6 = new Laptop("cl6", "Old Apple", "ON", 520, 6, 3);

    cl6.installGame("Minecraft");
    try{
      cl6.setState("oFf");
    } catch (Exception e){
      System.out.println("Laptop exception caught.");
    }
    lp1.playGame("Fortnite");
    lp2.installGame("Minecraft");
    lp2.installGame("League of Legends");
    lp2.installGame("CS GO");
    lp2.installGame("Terraria");
    lp2.installGame("APEX Legends");

    lp2.playGame("APEX Legends");

    System.out.println("===================================================================\n");

    System.out.println("============================Ordering Test============================");

    ArrayList<Phone> phoneList = new ArrayList<Phone>();
    phoneList.add(sp1);
    phoneList.add(ol1);
    phoneList.add((SmartPhone)csp1); 
    phoneList.add(l1);
    phoneList.add(l2);
    phoneList.add(l3);
    phoneList.add(l4);

    int count = 1; // Number Comparison
    for(int i = 0; i < phoneList.size(); i++){ // Unsorted list
      System.out.println("#" + count + ": " + phoneList.get(i).number());
      count++;
    }

    System.out.println("\n");
    Collections.sort(phoneList, new Orderings(). new compareByNumber());

    count = 1;
    for(int i = 0; i < phoneList.size(); i++){ // Sorted list
      System.out.println("#" + count + ": "  + phoneList.get(i).number());
      count++;  
    }

    System.out.println("\n"); 

    count = 1; // Owner Comparison
    for(int i = 0; i < phoneList.size(); i++){ // Unsorted list
      System.out.println("#" + count + ": " + phoneList.get(i).getOwner());
      count++;
    }

    System.out.println("\n");
    Collections.sort(phoneList, new Orderings(). new compareByOwner());

    count = 1;
    for(int i = 0; i < phoneList.size(); i++){ // Sorted list
      System.out.println("#" + count + ": "  + phoneList.get(i).getOwner());
      count++;  
    }

    System.out.println("\n"); 

    ArrayList<Computer> computerList = new ArrayList<Computer>();

    computerList.add(sp1);
    computerList.add(lp6);
    computerList.add(sp2);
    computerList.add(cl6);
    computerList.add(lp5);
    computerList.add(sp3);
    computerList.add(lp2);
    computerList.add(lp1);
    computerList.add(lp3);
    computerList.add(sp4);
    
    count = 1; // Screensize Comparison
    for(int i = 0; i < computerList.size(); i++){ // Unsorted list
      System.out.println("#" + count + ": " + computerList.get(i).getScreenSize());
      count++;
    }

    System.out.println("\n");
    Collections.sort(computerList, new Orderings(). new compareByScreenSize());

    count = 1;
    for(int i = 0; i < computerList.size(); i++){ // Sorted list
      System.out.println("#" + count + ": "  + computerList.get(i).getScreenSize());
      count++;  
    }

    System.out.println("\n"); 

    count = 1; // Brand Comparison
    for(int i = 0; i < computerList.size(); i++){ // Unsorted list
      if(computerList.get(i) instanceof SmartPhone){
        System.out.println("#" + count + ": " + ((SmartPhone)computerList.get(i)).getOwner() + "'s Smartphone");
        count++;
      }
      else{
        System.out.println("#" + count + ": " + ((Laptop)computerList.get(i)).getBrand());
        count++;
      }
    }

    System.out.println("\n");
    Collections.sort(computerList, new Orderings(). new compareByBrand());

    count = 1; // Brand Comparison
    for(int i = 0; i < computerList.size(); i++){ // Unsorted list
      if(computerList.get(i) instanceof SmartPhone){
        System.out.println("#" + count + ": " + ((SmartPhone)computerList.get(i)).getOwner() + "'s Smartphone");
        count++;
      }
      else{
        System.out.println("#" + count + ": " + ((Laptop)computerList.get(i)).getBrand());
        count++;
      }
    }

    System.out.println("\n"); 

    count = 1; // RAM Comparison
    for(int i = 0; i < computerList.size(); i++){ // Unsorted list
      System.out.println("#" + count + ": " + computerList.get(i).getRAM());
      count++;
    }

    System.out.println("\n");
    Collections.sort(computerList, new Orderings(). new compareByRAM());

    count = 1;
    for(int i = 0; i < computerList.size(); i++){ // Sorted list
      System.out.println("#" + count + ": "  + computerList.get(i).getRAM());
      count++;  
    }
    System.out.println("=====================================================================\n");
  }
}