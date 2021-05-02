/**
 * Class Application to manage interation with user as well as menu control
 * consist of 6 main methods for each 
 * also method for input validation
 *
 * @author (Sunwit Petchoo)
 * @version (08/04/2019)
 */
import java.util.Scanner;
public class Application
{
    /**
     * method to run loop and run each menu
     * maintain interact with user
     *
     * @param  selectedMenu  store user selected option
     * @param  checkMenu  check whether program still continue or not
     */
    
    public static void main(String[] args) {
        
        //declare variable
        String selectedMenu;
        boolean checkMenu = true;
        Scanner sc = new Scanner(System.in);
        
        // ask user to choose menu 
        System.out.println("Welcome to Car parking management system !!!"+"\n"+"\n"+"Please enter function number to continue....");
        while(checkMenu)
        {
            System.out.println("[1] Add parking slot"+"\n"+"[2] Delete parking slot"+"\n"+"[3] View parking slot"+"\n"+"[4] Park car");
            System.out.println("[5] Find car"+"\n"+"[6] Remove car"+"\n"+"[7] Exit program");
            selectedMenu = sc.nextLine();
        
        //get user input and activate selected option    
        switch (selectedMenu) 
            {
              case "1":
                addParkingSlot();
                break;
              case "2":
                deleteParkingSlot();
                break;
              case "3":
                veiwParkingSlot();
                break;
              case "4":
                parkCarToSlot();
                break;
              case "5":
                findCarByRegisNo();
                break;
              case "6":
                removeCarByRegisNo();
                break;
              case "7":
                //Terminate program
                System.out.println("--------Program ended--------");
                System.exit(0);
                break;
              default:
                System.out.println("Please enter proper function number [1-7]");
                
        }
    }
    }
    
    /**
     * method to maintain add slot task
     * 
     */
    public static void addParkingSlot()
    {
        
        Scanner sc = new Scanner(System.in);
        CarPark c = new CarPark();
        boolean conFlag = true;
        System.out.println("...................Add Parking Slot..................."+"\n");
        
        //check to continue this method or exit
        while(conFlag)
        {
            String slotId,slotType,conKey;
     
            System.out.println("Please enter parking slot id (a cappital letter follow by 2 digits such as D01)");
            slotId = sc.nextLine();
            //check user input
            if(!isSlotId(slotId))
            {
                System.out.println(">>>>>>>>>>Invalid slot id<<<<<<<<<<");
                //back to re-enter
                continue;
            }else
            {
                //check duplicate slot
                int slotIndex = c.findSlot(slotId);
                
                if(slotIndex >=0)
                {
                    System.out.println(">>>>>>>>>>Slot id "+slotId+" already exist<<<<<<<<<<");
                    continue;
                }
            }
            
            do
            {  
            System.out.println("Please enter slot type"+"\n"+"(V for visitor S for staff members).");
            slotType = sc.nextLine();
            
            if(!isSlotType(slotType))
            {
                System.out.println(">>>>>>>>>>Invalid slot type<<<<<<<<<<");
            }
            }while(!isSlotType(slotType));
            
            slotType = slotType.toUpperCase();
            //create ParkingSlot object and initialize value
            ParkingSlot slot = new ParkingSlot(slotId,slotType);
            
            //put slot in the list 
            c.addSlot(slot);
            System.out.println("...................Slot added..................."+"\n"+"Press Y to continue or any key to exit to home menu.");
            conKey = sc.nextLine();
            
            //continue or exit
            if(!conKey.equalsIgnoreCase("Y"))
            {
                conFlag = false;
            }
            
            
        
       }
   
    }
    
    /**
     * method to maintain delete slot task
     * 
     */
    public static void deleteParkingSlot()
    {
        CarPark c = new CarPark();
        Scanner sc = new Scanner(System.in);
        boolean conFlag = true;
        System.out.println("...................Delete Parking Slot..................."+"\n");
        
        //check to continue this method or exit
        while(conFlag)
        {
            String slotId,msg,conKey;
            System.out.println("Please enter parking slot id to delete");
            slotId = sc.nextLine();
            
            //check user input
            if(!isSlotId(slotId))
            {
                System.out.println(">>>>>>>>>>Invalid slot id<<<<<<<<<<");
                continue;
            }
            
            //call deleteSlot 
            msg = c.deleteSlot(slotId);
            
            System.out.println(msg+"\n"+"Press Y to continue or any key to exit to home menu.");
            conKey = sc.nextLine();
            
            //continue or exit
            if(!conKey.equalsIgnoreCase("Y"))
            {
                conFlag = false;
            }
            
       }
    
    }
    
    /**
     * method to display all slot in the list
     * 
     */
    public static void veiwParkingSlot()
    {
        CarPark c = new CarPark();
        Scanner sc = new Scanner(System.in);
        String slotId,msg,conKey;
        
        //set up header for each value
        System.out.println("...................Parking Slot List..................."+"\n");
        String format = "|%-8s|%-10s|%-9s|%-15s|%-15s|\n";
        String header[] ={ "Slot ID", "Slot Type", "Status", "Registration Plate" , "Owner"  }; 
        
        //print header 
        System.out.format(String.format(format, (Object[]) header));
        
        //call getSlotList to display details
        c.getSlotList();
        System.out.println("\n"+"Press 0 to go home menu or any key to exit");
        conKey = sc.nextLine();
        
        //continue or exit
        if(!conKey.equals("0"))
        {
            System.out.println("--------Program ended--------");
            System.exit(0);
        }
       
    }
    
    /**
     * method to display to maintain parking car to slot
     * 
     */
    public static void parkCarToSlot()
    {
        Scanner sc = new Scanner(System.in);
        CarPark cp = new CarPark();
        boolean conFlag = true;
        String slotId,reigsNo,owner,ownerStatus,msg,conKey,ans,con;
        System.out.println("...................Park Car To Slot..................."+"\n");
        
        //continue or exit
        while(conFlag)
        {   
            
            System.out.println("Please enter slot id");
            slotId = sc.nextLine();
            
            //search slot id 
            int listIndex = cp.findSlot(slotId);
            
            //if found
            if(listIndex >= 0)
            {
                
                String slotStatus = cp.getCarParkList().get(listIndex).getOccStatus();
                
                //park in empty slot only
                if(!slotStatus.equals("Emp"))
                {
                    System.out.println(">>>>>>>>>>Cannot park in occupied slot<<<<<<<<<<");
                    continue;
                }
                
                do{
                System.out.println("Please enter registration number (a cappital letter follow by 4 digits such as T2345)");
                reigsNo = sc.nextLine();
                
                //check registration number pattern
                if(!isRegisNo(reigsNo))
                {
                    System.out.println(">>>>>>>>>>Invalid registration number<<<<<<<<<<");
                }
                
                }while(!isRegisNo(reigsNo));
                
                //check the car already park or not
                if(cp.getCarParkList().get(listIndex).isAlreadyParked(cp, reigsNo))
                {
                    System.out.println(">>>>>>>>>>Car registration number "+ reigsNo +" already parked<<<<<<<<<<");
                    System.out.println("Press Y to continue or any key to exit to home menu.");
                    con = sc.nextLine();
                    
                    if(con.equalsIgnoreCase("Y"))
                    {
                        continue;
                    }else
                    {
                        break;
                    }
                    
                    
                }
                
                System.out.println("Please enter car owner name");
                owner = sc.nextLine();
                
                do
                {
                System.out.println("Is car owner a staff? (Y/N)");
                ans = sc.nextLine();
                
                if(!isYesNo(ans))
                {
                   System.out.println(">>>>>>>>>>Invalid answer (Y/N)<<<<<<<<<<<"); 
                }
                
                }while(!isYesNo(ans));
                
                if(ans.equalsIgnoreCase("Y"))
                {
                    ownerStatus = "S";
                }else
                { 
                    ownerStatus = "V";
                }
                
                
                String slotType = cp.getCarParkList().get(listIndex).getSlotType();
                //park in correct slot only
                if(!(slotType.equals(ownerStatus)))
                {
                    msg = ">>>>>>>>>>Cannot park in diffent slot type<<<<<<<<<<";
                }else
                {
                    //park car to slot
                    msg = cp.getCarParkList().get(listIndex).parkCar(cp, slotId, reigsNo, owner, ownerStatus,listIndex);
                }
                
            }else
            {
                msg = ">>>>>>>>>>There is no slot id "+slotId+" <<<<<<<<<<";
            }
            
            System.out.println(msg+"\n"+"Press Y to continue or any key to exit to home menu.");
            conKey = sc.nextLine();
            
            //continue or exit
            if(!conKey.equalsIgnoreCase("Y"))
            {
                conFlag = false;
            }
        }
        
    }
    
    /**
     * method to maintain search car by registration number task  
     * 
     */
    public static void findCarByRegisNo()
    {
        Scanner sc = new Scanner(System.in);
        ParkingSlot psCall = new ParkingSlot();
        CarPark cp = new CarPark();
        boolean conFlag = true;
        String reigsNo,conKey;
        String msg = "";
        
        //continue or exit
        while(conFlag)
        {   
            System.out.println("...................Find Car By Registration Number..................."+"\n");
            
            do{
                System.out.println("Please enter registration number (a cappital letter follow by 4 digits such as T2345)");
                reigsNo = sc.nextLine();
                
                //Check regis number pattern
                if(!isRegisNo(reigsNo))
                {
                    System.out.println(">>>>>>>>>>Invalid registration number<<<<<<<<<<");
                }
                
            }while(!isRegisNo(reigsNo));
            
            ParkingSlot ps = new ParkingSlot();
            //search car
            ps = psCall.findCarByRegisNo(cp,reigsNo);
            
            //display message if not found
            if(ps == null)
            {
                System.out.println(">>>>>>>>>>Car registration number: "+reigsNo+" is not in parking slot<<<<<<<<<<");
            }else
            {
                //display result
                psCall.showCarInfo(ps);
            }
            
            System.out.println("Press Y to continue or any key to exit to home menu.");
            conKey = sc.nextLine();
            
            //continue or exit
            if(!conKey.equalsIgnoreCase("Y"))
            {
                conFlag = false;
            }
        }
       
    }
    
    /**
     * method to maintain removing car from slot 
     * by get regis numeber from user and check it
     * 
     */
    public static void removeCarByRegisNo()
    {
        
        Scanner sc = new Scanner(System.in);
        ParkingSlot psCall = new ParkingSlot();
        CarPark cp = new CarPark();
        boolean conFlag = true;
        String reigsNo,conKey;
        String msg = "";
        System.out.println("...................Remove Car By Registration Number..................."+"\n");
        
        //continue or exit
        while(conFlag)
        {   
            
            do{
                System.out.println("Please enter registration number (a cappital letter follow by 4 digits such as T2345)");
                reigsNo = sc.nextLine();
                
                //check regis number pattern
                if(!isRegisNo(reigsNo))
                {
                    System.out.println(">>>>>>>>>>Invalid registration number<<<<<<<<<<");
                }
                
            }while(!isRegisNo(reigsNo));
            
            ParkingSlot ps = new ParkingSlot();
            
            //search car 
            ps = psCall.findCarByRegisNo(cp,reigsNo);
            
            //check if not found
            if(ps == null)
            {
                System.out.println(">>>>>>>>>>Car registration number: "+reigsNo+" is not in parking slot<<<<<<<<<<");
            }else
            {
                //remove car from slot if found and set result message
                psCall.removeCarFromSlot(cp,ps.getSlotId());
                msg = "..........Car registration number: "+reigsNo+" was removed..........";
            }
            
            System.out.println(msg+"\n"+"Press Y to continue or any key to exit to home menu.");
            conKey = sc.nextLine();
            
            //continue or exit
            if(!conKey.equalsIgnoreCase("Y"))
            {
                conFlag = false;
            }
        }
        
    }
    
    /**
     * Method to check slot id pattern by using regular expression
     * 
     * @param  slotId store slot id input from user
     * 
     * @return  true for correct input, false if not
     */
    public static boolean isSlotId(String slotId)
    {
         return slotId.matches("[A-Z]\\d{2}");
    }
    
    /**
     * Method to check slot type pattern by using regular expression
     * 
     * @param  slotType store slot type input from user
     * 
     * @return  true for correct input, false if not
     */
    public static boolean isSlotType(String slotType)
    {
         return slotType.matches("[V|Sv|s]");
    }
    
    /**
     * Method to check registration number pattern by using regular expression
     * 
     * @param  regisNo store registration number input from user
     * 
     * @return  true for correct input, false if not
     */
    public static boolean isRegisNo(String regisNo)
    {
         return regisNo.matches("[A-Z]\\d{4}");
    }
    
    /**
     * Method to check user input for Yes/No question
     * 
     * @param  ans store user answer
     * 
     * @return  true for correct input, false if not
     */
    public static boolean isYesNo(String ans)
    {
         return ans.matches("[Y|Ny|n]");
    }

    }

