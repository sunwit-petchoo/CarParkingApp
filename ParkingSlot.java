/**
 * Class Parking slot to manage slot activity such as park car to slot 
 * remove car and also get car information from slot
 *
 * @author (Sunwit petchoo)
 * @version (08/04/2019)
 */
import java.util.ArrayList;
public class ParkingSlot
{
    //declare varible for ParkingSlot class 
    private String slotId;
    private String slotType;
    private String occStatus;
    Car car;
    
    /**
     * Constructor for objects of class ParkingSlot
     * @param  slotId  slot id
     * @param  slotType   slot type for (staffs or visitors)
     */
    public ParkingSlot(String slotId,String slotType)
    {
        //initialize slot variable when created as well as Car
        this.slotId = slotId;
        this.slotType = slotType;
        this.occStatus = "Emp";
        this.car = new Car("","","");
    }
    
    public ParkingSlot()
    {
   
    }
    
    /**
     * Method for remove car from slot by providing slot id 
     * @param  slotId  slot id
     * @param  cp   CarPark object to get access CarPark class
     */
    public void removeCarFromSlot(CarPark cp,String slotId)
    {
        //find array index in the list by slot Id
        int listIndex = cp.findSlot(slotId);
        
        //set occupied status to be empty 
        cp.getCarParkList().get(listIndex).setOccStatus("Emp");
        
        //Clear all car value in slot
        cp.getCarParkList().get(listIndex).removeCar();
        
    }
    
    /**
     * Method to display car information in specific slot id 
     * 
     * @param  ps   ParkingSlot object to get access data
     */
    public void showCarInfo(ParkingSlot ps)
    {
        //display registration number 
        System.out.print("Car registration number:"+ps.getCar().getRegisNo()+"\t");
        
        //display car owner 
        System.out.print("Owner:"+ps.getCar().getCarOwner()+"\t");
        
        //display parking slot
        System.out.print("Parking location:"+ps.getSlotId()+"\n");
    }
    
    /**
     * Method to park a car in slot by providing slot and car information as well as index in the list  
     * 
     * @param  cp   CarkPark object to get access data
     * @param  slotId   slot id to park a car
     * @param  regisNo   registration number
     * @param  carOwner   car owner name
     * @param  ownerStatus   staffs or visitors
     * @param  listIndex   index in the list
     * 
     * @return  successful message
     */
    public String parkCar(CarPark cp,String slotId,String regisNo,String carOwner,String ownerStatus, int listIndex)
    {
        // message for return
        String msg = "";
        
        // update car information 
        car.setCarOwner(carOwner);
        car.setOwnerStatus(ownerStatus);
        car.setRegisNo(regisNo);
        
        //update slot to be occupied
        cp.getCarParkList().get(listIndex).getCar().setRegisNo(regisNo);
        cp.getCarParkList().get(listIndex).setOccStatus("Occ");
        msg = "..........Park successfully..........";
        
        return msg;
    }
    
    /**
     * Method to check whether already have a car in slot
     * 
     * @param  cp   CarkPark object to get access data
     * @param  regisNo   registration number
     * 
     * @return  result (true/false)
     */
    public boolean isAlreadyParked(CarPark cp,String regisNo)
    {
        //initial result to be true
        boolean isPark = true;
        
        //find car(condition will be true if not found)
        if(findCarByRegisNo(cp,regisNo) == null)
        {
            //result will be false  
            isPark = false;
        }
    
        return isPark;
    }
    
    /**
     * Method to search car by using regitration number 
     * 
     * @param  cp   CarkPark object to get access data
     * @param  searchRegisNo   registration number
     * 
     * @return  ParkingSlot object
     */
    public ParkingSlot findCarByRegisNo(CarPark cp,String searchRegisNo)
    {
        
        String slotId = "";
        //initial start index and get list size
        int index = 0;
        int size = cp.getCarParkList().size();
        
        //initial result to be false if not found
        boolean found = false;
        while(index < size && !found) 
        {
            //get registration number from the list 
            String regisNo = cp.getCarParkList().get(index).getCar().getRegisNo();
            
            //check compare registration number
            if(regisNo.contains(searchRegisNo)) 
            {
                // We don't need to keep looking.
                //found = true;
                return cp.getCarParkList().get(index);
            }
            else {
                index++;
            }
        }
        return null;
    }
    
    /**
     * Method to get current Car object 
     * 
     * @return  Car object
     */
    public Car getCar() 
    {
        return this.car;
    }
    
    /**
     * Method to clear car attribute when remove car from slot 
     * 
     */
    public void removeCar() 
    {
        //Clear all car value
        this.car.setCarOwner("");
        this.car.setOwnerStatus("");
        this.car.setRegisNo("");
        
    }
    
    /**
     * set method for slotId variable
     * 
     * @param  slotId  store slot id value get from user
     *
     */
    public void setSlotId(String slotId)
    {
        this.slotId = slotId;
    }
   
    /** 
     * @return  current slot id 
     */
    public String getSlotId() 
    {
        return slotId;
    }
    
    /**
     * set method for slotType variable
     * 
     * @param  slotType  store slot type value get from user
     *
     */
    public void setSlotType(String slotType)
    {
        this.slotType = slotType;
    }
   
    /** 
     * @return  current slot type 
     */
    public String getSlotType() 
    {
        return slotType;
    }
    
     /**
     * set method for occStatus variable
     * 
     * @param  occStatus  store occupied status value get from user
     *
     */
    public void setOccStatus(String occStatus)
    {
        this.occStatus = occStatus;
    }
   
    /** 
     * @return  current occupiec status 
     */
    public String getOccStatus() 
    {
        return occStatus;
    }
   
}
