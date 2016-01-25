public class Myset 
{
	LinkedList ll=new LinkedList();
	public Boolean IsEmpty() //returns true if the set is empty
	{
		if(ll.N==0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public Boolean IsMember(MobilePhone o) //: Returns true if o is in the set, false otherwise.
	{
		if(IsEmpty())
		{
			return false;
		}
		else
		{
			ll.curr=ll.head;
			for( ;ll.curr.next!=null;ll.curr=ll.curr.next)
			{
				if(ll.curr.element==o)
				{
					return true;
				}
			}
			if(ll.curr.element==o)
			{
				return true;
			}
			return false;
		}
		
	}
	public void Insert(MobilePhone o)// : Inserts o into the set.
	{
		if(IsEmpty())
		{
			Node dummy=new Node(o,null);
			ll.head=dummy;
			ll.tail=dummy;
			ll.N=1;
		}
		else
		{
			Node dummy=new Node(o,null);
			ll.tail.next=dummy;
			ll.tail=dummy;
			ll.N+=1;
		}
	}
	
	
	public void Delete(MobilePhone o)// : Deletes o from the set, throws exception if o is not in the set
	{
		if(IsMember(o))
		{
				if(ll.head.element.identity==o.identity)
				{
					if(ll.N==1)
					{
						ll.head=null;
						ll.tail=null;
						ll.N=0;
					}
					else
					{
						ll.head=ll.head.next;
						ll.N-=1;
					}
				}
						else
						{
							if(ll.head==null)
							{
								System.out.println("Ohh Shit...!!! The head is null");
								return;
							}
							ll.curr=ll.head;
							for( ;ll.curr.next!=null;ll.curr=ll.curr.next)
							{
								if(ll.curr.element.identity==o.identity)
								{
									ll.curr.element=ll.curr.next.element;
									ll.curr.next=ll.curr.next.next;
									if(ll.curr.next==null)
									{
										ll.tail=ll.curr;
									}
									ll.N-=1;
									return;
								}
							}
							if(ll.curr.element==o)
							{
								ll.curr=ll.head;
								for(int i=0;i<ll.N-2;i++)
								{
									ll.curr=ll.curr.next;
								}
								ll.curr.next=null;
								ll.tail=ll.curr;
								ll.N-=1;
								return;
							}
						}
			
			
		}
		
		else
		{
			//System.out.println("This Mobile is not present");
			return;
		}
		
	}
		
	
	public Myset Union (Myset a)// : Returns a set which is the union of the current set with the set a.
	{
		Myset Uni= new Myset();
		ll.curr=ll.head;
		for( ;ll.curr.next!=null;ll.curr=ll.curr.next)
		{
			Uni.Insert(ll.curr.element);
		}
		Uni.Insert(ll.curr.element);
		
		a.ll.curr=a.ll.head;
		for( ;a.ll.curr.next!=null;a.ll.curr=a.ll.curr.next)
		{
			if(!IsMember(a.ll.curr.element))
			{
				Uni.Insert(a.ll.curr.element);
			}
			
		}
		if(!IsMember(a.ll.curr.element))
		{
			Uni.Insert(a.ll.curr.element);
		}
		return Uni;
	}
	
	public Myset Intersection(Myset a)//: Returns a set which is the intersection of the current set with the set a.
	{
		Myset inter=new Myset();
		a.ll.curr=a.ll.head;
		for( ;a.ll.curr.next!=null;a.ll.curr=a.ll.curr.next)
		{
			if(IsMember(a.ll.curr.element))
			{
				inter.Insert(a.ll.curr.element);
			}
			
		}
		if(IsMember(a.ll.curr.element))
		{
			inter.Insert(a.ll.curr.element);
		}
		return inter;
	}
		
	
}
