public class Exchange //that will form the nodes of the routing map structure
{
	public int identity;
	public Exchange next;
	public Exchange par;
	ExchangeList Children=new ExchangeList();
	MobilePhoneSet Resident=new MobilePhoneSet();
	
	Exchange() //constructor to create an exchange Unique identifier for an exchange is an integer
	{
		identity=0;
		//next=null;
		//par=null;
		//Children=null;
		//Resident=null;
	}
	
	Exchange(int number) //constructor to create an exchange Unique identifier for an exchange is an integer
	{
		identity=number;
		//next=null;
		//par=null;
		//Children=null;
		//Resident=null;
		
	}
	
	public Exchange parent()
	{
		return par;
	}
	public int numChildren()// (for number of children)
	{
		return Children.N;
	}
	public Exchange child(int i) //(returns the ith child)
	{
		if(i==0)
		{
			return Children.head;
		}
		else
		{
			Children.curr=Children.head;
			for(int j=0;j<i;j++)
			{
				Children.curr=Children.curr.next;
			}
			return Children.curr;
		}
	}
	public Boolean isRoot()
	{
		if(identity==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public RoutingMapTree subtree(int i) // (returns the ith subtree)
	{
		RoutingMapTree sub=new RoutingMapTree();
		sub.root=child(i);
		return sub;
	}
	public MobilePhoneSet residentSet() //This returns the resident set of mobile phones of the exchange.
	{
		return Resident;
	}
}
