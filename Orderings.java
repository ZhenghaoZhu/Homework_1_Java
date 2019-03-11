//Zhenghao (Kevin) Zhu
//111736904
//zhenghao.zhu@stonybrook.edu
//HOMEWORK 1
//CSE 216
//R-03, Benjamin Michalowicz

import java.util.Comparator;

public class Orderings{
    public class compareByNumber implements Comparator<Phone>{ // It works
        public int compare(Phone p1, Phone p2){
            if(p1.number().phoneNumber > p2.number().phoneNumber){
                return 1; 
            }
            else if(p1.number().phoneNumber == p2.number().phoneNumber){
                return 0;
            }
            else{
                return -1;
            }
        }
    }

    public class compareByOwner implements Comparator<Phone>{ // It works
        public int compare(Phone p1, Phone p2){
            if(p1.getOwner().compareTo(p2.getOwner()) > 0){
                return 1;
            }
            else if(p1.getOwner().compareTo(p2.getOwner()) == 0){
                return 0;
            }
            else{
                return -1;
            }
        }
    }
        

    public class compareByScreenSize implements Comparator<Computer>{ // It works
        public int compare(Computer c1, Computer c2){
            if(c1.getScreenSize() > c2.getScreenSize()){
                return 1;
            }
            else if(c1.getScreenSize() == c2.getScreenSize()){
                return 0;
            }
            else{
                return -1;
            }
        }
    }

    public class compareByBrand implements Comparator<Computer>{ 
        public int compare(Computer c1, Computer c2){
            if((c1 instanceof SmartPhone) || (c2 instanceof SmartPhone)){ // Smartphones have no brand, therefore are placed at the end of the list.
                return -1;
            }
            if(((Laptop) c1).getBrand().compareTo(((Laptop) c2).getBrand()) > 0) {
                return 1;
            }
            else if(((Laptop) c1).getBrand().compareTo(((Laptop) c2).getBrand()) == 0){
                return 0;
            }
            else{
                return -1;
            }
        }
    }

    public class compareByRAM implements Comparator<Computer>{ // It works
        public int compare(Computer c1, Computer c2){
            if(c1.getRAM() > c2.getRAM()){
                return 1;
            }
            else if(c1.getRAM() == c2.getRAM()){
                return 0;
            }
            else{
                return -1;
            }
        }
    }
}