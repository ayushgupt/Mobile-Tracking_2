public class ExchangeList //implements a linked list of exchanges.
{
	public Exchange head;
	public Exchange tail;
	public Exchange curr;
	public int N;
	public ExchangeList()
	{
		head=null;
		tail=null;
		curr=null;
		N=0;
	}
	
	public Boolean IsEmpty() //returns true if the set is empty
	{
		if(N==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public Boolean IsMember(Exchange o) //: Returns true if o is in the set, false otherwise.
	{
		if(IsEmpty())
		{
			return false;
		}
		else
		{
			curr=head;
			for( ;curr.next!=null;curr=curr.next)
			{
				if(curr.identity==o.identity)
				{
					return true;
				}
			}
			if(curr.identity==o.identity)
			{
				return true;
			}
			return false;
		}	
	}
	
	public void Insert(Exchange o)// : Inserts o into the set.
	{
		if(IsEmpty())
		{
			head=o;
			tail=o;
			N=1;
		}
		else
		{
			tail.next=o;
			tail=o;
			N+=1;
		}
	}
	
	public void Delete(Exchange o)// : Deletes o from the set, throws exception if o is not in the set
	{
		if(IsMember(o))
		{
			if(head==o)
			{
				if(N==1)
				{
					head=null;
					tail=null;
					N=0;
				}
				else
				{
					head=head.next;
					N-=1;
				}
			}
			else
			{
				curr=head;
				for( ;curr.next!=null;curr=curr.next)
				{
					if(curr==o)
					{
						curr=curr.next;
						curr.next=curr.next.next;
						if(curr.next==null)
						{
							tail=curr;
						}
						N-=1;
					}
				}
				if(curr==o)
				{
					curr=head;
					for(int i=0;i<N-2;i++)
					{
						curr=curr.next;
					}
					curr.next=null;
					tail=curr;
					N-=1;
				}
			}
			
			
		}
		else
		{
			throw new RuntimeException("This Exchange is not Present");
		}
	}
	
	public Exchange querychild(int k)
	{
		curr=head;
		for(int i=0;i<k-1;k++)
		{
			curr=curr.next;
		}
		return curr;
	}
	
	
}
