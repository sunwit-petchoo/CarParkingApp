
/**
 * Class Car to store car information, update and return value 
 *
 * @author (Sunwit Petchoo 101318759)
 * @version (08/04/2019)
 */

public class Car
{
    // declare variable 
    private String regisNo;
    private String carOwner;
    private String ownerStatus;

    /**
     * Constructor for objects of class Car
     * @param  regisNo  registrarion number
     * @param  carOwner   name of car owner
     * @param  ownerStatus  staffs or visitors
     */
    public Car(String regisNo,String carOwner,String ownerStatus)
    {
        this.regisNo = regisNo;
        this.carOwner = carOwner;
        this.ownerStatus = ownerStatus;
    }
    
    /**
     * set method for regisNo variable
     * 
     * @param  regisNo  store registration number value get from user
     *
     */
    public void setRegisNo(String regisNo)
    {
        // put your code here
        this.regisNo = regisNo;
    }
   
    /** 
     * @return  current registration number
     */
    public String getRegisNo() 
    {
        return regisNo;
    }
    
    /**
     * set method for carOwner variable
     * 
     * @param  carOwner  store owner name value get from user
     *
     */
    public void setCarOwner(String carOwner)
    {
        this.carOwner = carOwner;
    }
   
    /** 
     * @return  current owner name 
     */
    public String getCarOwner() 
    {
        return carOwner;
    }
    
    /**
     * set method for ownerStatus variable
     * 
     * @param  ownerStatus  store owner status value get from user
     *
     */
    public void setOwnerStatus(String ownerStatus)
    {
        // put your code here
        this.ownerStatus = ownerStatus;
    }
   
    /** 
     * @return  current owner status 
     */
    public String getOwnerStatus() 
    {
        return ownerStatus;
    }
   
    

}
