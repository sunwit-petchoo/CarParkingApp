
/**
 * CarPark class to manage parking slot that can 
 * add slot to the list 
 * delete slot from the list 
 * find slot 
 * remove slot 
 *
 * @author (Sunwit Petchoo)
 * @version (08/04/2019)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Formatter;
public class CarPark
{
    
    //list of parking slot 
    private static ArrayList<ParkingSlot> carParkList = new ArrayList<ParkingSlot>();
    
    /**
     * Method to check whether already have a car in slot
     * 
     * @return  list of parking slot 
     */
    public ArrayList<ParkingSlot> getCarParkList() 
    {
        return carParkList;
    }

    /**
     * Method to find slot that in the list 
     * 
     * @param  searchSlotId store slot id input from user 
     * 
     * @return  index of the list when found, -1 when not found 
     */
    public int findSlot(String searchSlotId)
    {
        //initial index in the list 
        int listIndex = -1;
        
        //initial index for searching 
        int index = 0;
        
        //initial result(false if not found)
        boolean found = false;
        
        while(index < carParkList.size() && !found) 
        {
            //get slotid from the list 
            String slotId = carParkList.get(index).getSlotId();
            
            //compare slot id 
            if(slotId.contains(searchSlotId)) 
            {
                // We don't need to keep looking.
                found = true;
                listIndex = index;
            }
            else {
                index++;
            }
        }
        return listIndex;
    }
    
    /**
     * Method to add parking slot into arraylist
     * 
     * @param  slot ParkingSlot object to store slot information
     * 
     */
    public void addSlot(ParkingSlot slot)
    {
        carParkList.add(slot);   
    }
    
    /**
     * Method to remove slot from the list 
     * 
     * @param  removeSlotId store slot id input from user
     * 
     * @return  result message
     */
    public String deleteSlot(String removeSlotId)
    {
      //use iterator with the list 
      Iterator <ParkingSlot> it = carParkList.iterator();
      String msg = "";
      
      while(it.hasNext()) 
      {
          //get next ParkingSlot
          ParkingSlot slot = it.next();
          
          //get slot id and occupied status
          String slotId = slot.getSlotId();
          String occStatus = slot.getOccStatus();
          
          //check whether removeSlotId in the list 
          if(slotId.equals(removeSlotId))  
          {
              //cannot remove occupied slot
              if(!occStatus.equals("Occ"))
              {
                  it.remove();
                  msg = "..........Delete slot id "+ slotId+" sucessfully.........";
                  break;
              }else
              {
                  msg = ">>>>>>>>>>Cannot delete occupied parking slot<<<<<<<<<<";
                  break;
              }
          }else
          {
            msg = ">>>>>>>>>>There is no slot id "+removeSlotId+"<<<<<<<<<<";
          }
      }

        return msg;
    }
    
    /**
     * Method to display all ParkingSlot in the list
     * by getting all inforamtion in the list and print out
     * 
     */
    public void getSlotList()
    {
        //loop to get value in the list 
        for (int index = 0; index < carParkList.size(); index++) 
        {
            //get all display value
            String slotId = carParkList.get(index).getSlotId();
            String slotType = carParkList.get(index).getSlotType();
            String occStatus = carParkList.get(index).getOccStatus();
            String regisNo = carParkList.get(index).getCar().getRegisNo();
            String owner = carParkList.get(index).getCar().getCarOwner();
            
            //Change stored value to be meaningful
            if(owner.equals(""))
            {
                owner = "-";
            }
            if(regisNo.equals(""))
            {
                regisNo = "-";
            }
            if(slotType.equals("V"))
            {
                slotType = "Visitor";
            }else
            {
                slotType = "Staff";
            }
            if(occStatus.equals("Emp"))
            {
                occStatus = "Empty";
            }else
            {
                occStatus = "Occupied";
            }
           
            //set format and display
            String format = "|%-8s|%-10s|%-9s|%-18s|%-15s|\n";
            String body[] ={ slotId, slotType, occStatus, regisNo , owner  }; 
            System.out.format(String.format(format, (Object[]) body));
            
        }   
 
    }
}
