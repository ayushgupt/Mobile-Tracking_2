public class MobilePhone 
{
	public int identity;
	public boolean status;
	public Exchange base;  
	MobilePhone(int number)//: constructor to create a mobile phone. Unique identifier for a mobile phone is an integer.
	{
		identity=number;
	}
	public int number()	//: returns the id of the mobile phone.
	{
		return identity;
	}
	public Boolean status()//: returns the status of the phone, i.e. switched on or switched off
	{
		return status;
	}
	public void switchOn()//: Changes the status to switched on
	{
		status=true;
	}
	public void switchOff()//: Changes the status to switched off
	{
		status=false;
	}
	public Exchange location()//: returns the base station with which the phone is registered if the phone is switched on and an exception if the phone is off. The class Exchange will be described next.
	{
		if(status==false)
		{
			throw new RuntimeException("Phone is Off");
		}
		else
		{
			return base;
		}
		
	}
	
	
		
}
