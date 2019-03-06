class Main {
  public static void main(String[] args) {
    
    
    SmartPhone sp1 = new SmartPhone("sp1", 1434567891L, 320, 4, 3, "off");
    SmartPhone sp2 = new SmartPhone("sp2", 2434567891L, 320, 4, 3, "on");
    SmartPhone sp3 = new SmartPhone("sp3", 3434567891L, 320, 4, 3, "on");
    sp1.call(sp2);
    sp3.call(sp1);
    sp3.call(sp2);
    sp1.end();
    sp3.call(sp1);
    sp2.call(sp3);

    
    Phone p1 = new SmartPhone("p1" , 1234567891L, 320, 4, 3, "on");
    SmartPhone newSp1 = (SmartPhone) sp1; // typecasting
    newSp1.installGame("MINEcraft");
    newSp1.playGame("minecraft");
    System.out.println(newSp1.number());
    
    OldLandline oll1 = new OldLandline("oll1", 5345654678L);
    Landline l6 = new Landline("l6", 1234567891L);
    Landline l7 = new Landline("l7", 2234567891L);
    Landline l8 = new Landline("l8", 3234567891L);
    SmartPhone s1 = new SmartPhone("s1" , 1234567891L, 320, 4, 3, "on");
    l6.call(l7);
    l8.call(p1);
    p1.end();
    p1.call(l6);
    s1.call(p1);
    s1.installGame("miNeCraft"); // Smartphones can intall games when making a call.
    s1.playGame("minECRAFT"); // Smartphones can play games when making a call.
    
    System.out.println("\n" +"OLDLANDLINE TEST"); // Working
    OldLandline olll1 = new OldLandline("olll1", 5345654678L);
    OldLandline p2 = new OldLandline("p2", 1345654678L);
    OldLandline p3 = new OldLandline("p3", 2345654678L);
    OldLandline p4 = new OldLandline("p4", 7345654678L);
    OldLandline p5 = new OldLandline("p5", 7445654678L);
    p1.call(p1);
    p1.call(p2); // P1 should be able to call P2
    p3.call(p2); // P3 should not be able to call P2
    p4.call(p3); // P4 should be able to call P3
    p1.end(); // P1 ends call with P2
    p5.call(p1); // P5 should be able to call P1
    p4.end(); // P4 ends call with P3
    p3.call(p2); // P3 should be able to call P2
    
    
    System.out.println("\n" + "LANDLINE TEST"); // Not working yet
    Landline p6 = new Landline("p6", 1234567891L);
    Landline p7 = new Landline("p7", 2234567891L);
    Landline p8 = new Landline("p8", 3234567891L);
    p6.call(p7); // p6 calls p7
    p8.call(p7); // p8 unable to call p7, leave message, p7 has 1 message
    p8.call(p6); // p8 unable to call p6, leave message, p6 has 1 message
    p6.end(); // p6 ends call wth p7
    p6.call(p8); // p6 calls p8
    p6.call(p7); // p6 can't call when calling
    p7.call(p6); // p7 unable to call p6, leave message, p6 has 2 messages
    p6.readMessages(Landline.MSG_STATUS.UNREAD); // Print out all of p6's unread messages
    p6.end();
    p8.call(p6); // p8 unable to call p6, leaves message, p6 has 1 unread message
    p6.readMessages(Landline.MSG_STATUS.READ);
    p6.end();
    p6.readMessages();
    p6.call(p8);
    p7.call(p6);
    p6.readMessages();
    
    System.out.println("\n" + "LAPTOP TEST"); // Working
    Laptop l1 = new Laptop(100, 6, 2, "L1", "DC", "ON");
    System.out.println(l1.getScreenSize());
    System.out.println(l1.getRAM());
    System.out.println(l1.getProcessorSpeeed());
    System.out.println(l1.getHostName());
    System.out.println(l1.getState());
    l1.setState("off");
    System.out.println(l1.getState());
    l1.setState("oN");
    l1.installGame("Minecraft");
    l1.installGame("Fortnite");
    l1.installGame("minecraft");
    l1.playGame("MinEcraft");
    l1.installGame("PUBG");
    l1.installGame("Terraria");
    l1.installGame("League of Legends");
    l1.installGame("Steam");
    l1.installGame("Counter-Strike");
    
    
    System.out.println("\n" + "SMARTPHONE AND LANDLINE TEST");
    SmartPhone spp1 = new SmartPhone("spp1" , 1234567891L, 320, 4, 3, "on");
    Landline lll6 = new Landline("lll6", 1234567891L);
    Landline lll7 = new Landline("lll7", 2234567891L);
    lll6.call(spp1);
    lll6.call(lll7);
    spp1.end();
    lll7.call(spp1);
    spp1.end();
    spp1.end();
    

    
    

  }
}