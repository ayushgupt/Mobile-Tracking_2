public class RoutingMapTree 
{
	public Exchange curr;
	Exchange root=new Exchange();
	MobilePhoneSet AllMob=new MobilePhoneSet();
	
	RoutingMapTree() //: constructor method. This should create a RoutingMapTree with one Exchange node, the root node which has an identifier of 0. Create other constructors that you deem necessary.
	{
		curr=root;
		root.identity=0;
	}
	
	public Boolean containsNode(int a)
	{
		//System.out.println(a+" "+root.identity);
		if(root.identity==a)
		{
			return true;
		}
		else
		{
			if(root.numChildren()==0)
			{
				return false;
			}
			else
			{
				
				for(int i=0;i<root.numChildren();i++)
				{	
					if(root.subtree(i).containsNode(a))
					{
						return true;
					}
				}
				return false;
			}
		}
	}
	
	public Exchange returnNode(int a)
	{
		if(root.identity==a)
		{
			return root;
		}
		else
		{
					for(int i=0;i<root.numChildren();i++)
					{	
						if(root.child(i).identity==a)
						{
							return root.child(i);
						}
					}
					for(int i=0;i<root.numChildren();i++)
					{
						if(root.subtree(i).containsNode(a))
						{
							return root.subtree(i).returnNode(a);
						}
					}
					return root;//This is bad
		}	
	}
	
	
	
	public void addExchange(int a,int b)
	{
		if(!containsNode(a))
		{
			System.out.println("This Parent Exchange is not present...!!!");
			return;
		}
		if(containsNode(b))
		{
			System.out.println("This Child Exchange is already present...!!!");
			return;
		}
		Exchange add=new Exchange(b);//created new Exchange
		add.par=returnNode(a);
		returnNode(a).Children.Insert(add);
	}
	public void switchOn(int a,int b)// : This method only works on mobile phones that are currently switched off. It switches the phone a on and registers it with base station b. The entire routing map tree will be updated accordingly.
	{
		if(!containsNode(b))
		{
			System.out.println("This Parent Exchange is not present...!!!");
			return;
		}
		if(containsPhone(a))
		{
			System.out.println("This Mobile is already On in another Exchange...!!!");
			return;
		}
		MobilePhone add=new MobilePhone(a);
		add.base=returnNode(b);
		returnNode(b).Resident.Set.Insert(add);
		AllMob.Set.Insert(add);
	}
	
	
	public Boolean containsPhone(int b)
	{
		if(AllMob.Set.ll.N==0)
		{
			return false;
		}
		AllMob.Set.ll.curr=AllMob.Set.ll.head;
		for(;AllMob.Set.ll.curr.next!=null;AllMob.Set.ll.curr=AllMob.Set.ll.curr.next)
		{
			if(AllMob.Set.ll.curr.element.identity==b)
			{
				return true;
			}
		}
		if(AllMob.Set.ll.curr.element.identity==b)
		{
			return true;
		}
		return false;
		
	}
	
	
	public void switchOff(int a)// : This method only works on mobile phones that are currently switched on. It switches the phone a off. The entire routing map tree has to be updated accordingly.
	{
		
		if(!containsPhone(a))
		{
			System.out.println("Mobile"+ a +"is Not Present...!!!");
			return;
		}
		
				/*find mobilephone with identifier a and deregister it*/
			Exchange dummy=new Exchange(20000);
			AllMob.Set.ll.curr=AllMob.Set.ll.head;
			for(;AllMob.Set.ll.curr.next!=null;AllMob.Set.ll.curr=AllMob.Set.ll.curr.next)
			{
				if(AllMob.Set.ll.curr.element.identity==a)
				{
					dummy=AllMob.Set.ll.curr.element.base;
					AllMob.Set.Delete(AllMob.Set.ll.curr.element);
					break;
				}
			}
			if(AllMob.Set.ll.curr.element.identity==a)
			{
				dummy=AllMob.Set.ll.curr.element.base;
				
				AllMob.Set.Delete(AllMob.Set.ll.curr.element);
				
			}
			
				//traverse through the tree and find whose resident set has a and then
				returnNode(dummy.identity).Resident.Set.ll.curr=returnNode(dummy.identity).Resident.Set.ll.head;
				for(;returnNode(dummy.identity).Resident.Set.ll.curr.next!=null;returnNode(dummy.identity).Resident.Set.ll.curr=returnNode(dummy.identity).Resident.Set.ll.curr.next)
				{
					if(returnNode(dummy.identity).Resident.Set.ll.curr.element.identity==a)
					{
						(returnNode(dummy.identity)).Resident.Set.Delete(returnNode(dummy.identity).Resident.Set.ll.curr.element);
						break;
					}
				}
				if(returnNode(dummy.identity).Resident.Set.ll.curr.element.identity==a)
				{
					(returnNode(dummy.identity)).Resident.Set.Delete(returnNode(dummy.identity).Resident.Set.ll.curr.element);
				}
				//(returnNode(dummy.identity)).Resident.Set.Delete(/*mobilephone object with identifier a*/)
	}
	
	public void queryNthchild(int a, int b)
	{
		if(!containsNode(a))
			{
				System.out.println("This Parent Exchange is not present...!!!");
				return;
			}
		if((returnNode(a).numChildren()-1)<b)
		{
			System.out.println("This Parent Exchange does not have so many children...!!!");
			return;
		}
		System.out.println("queryNthChild "+a+" "+b+":"+" "+returnNode(a).child(b).identity);
	}
	public void queryMobSet(Exchange imp)
	{
		if(imp.Resident.Set.ll.N!=0)
		{
			imp.Resident.Set.ll.curr=imp.Resident.Set.ll.head;
			for(;imp.Resident.Set.ll.curr.next!=null;imp.Resident.Set.ll.curr=imp.Resident.Set.ll.curr.next)
			{
				System.out.print(imp.Resident.Set.ll.curr.element.identity+",");
			}
			System.out.print(imp.Resident.Set.ll.curr.element.identity+",");
		}
		if(imp.numChildren()!=0)
		{
			for(int i=0;i<imp.numChildren();i++)
			{
				queryMobSet(imp.child(i));
			}
		}
	}
	public void queryMobileSet(int a)
	{
		if(!containsNode(a))
		{
			System.out.println("This Exchange is not present...!!!");
			return;
		}
		Exchange imp=returnNode(a);//returns this node
//		imp.Resident.Set.ll.curr=imp.Resident.Set.ll.head;
//		for(;imp.Resident.Set.ll.curr.next!=null;imp.Resident.Set.ll.curr=imp.Resident.Set.ll.curr.next)
//		{
//			System.out.print(imp.Resident.Set.ll.curr.element.identity+",");
//		}
//		System.out.print(imp.Resident.Set.ll.curr.element.identity);
//		for(int i=0;i<imp.numChildren();i++)
//		{
//			
//		}
		queryMobSet(imp);
		System.out.println(" ");
		
//		if(imp.Resident.Set.ll.N==0)
//		{
//			throw new RuntimeException("This Exchange is Empty...!!!");
//		}
	}
	
	
	public Exchange findPhone(int m)
	{
		
		AllMob.Set.ll.curr=AllMob.Set.ll.head;
		for(;AllMob.Set.ll.curr.next!=null;AllMob.Set.ll.curr=AllMob.Set.ll.curr.next)
		{
			if(AllMob.Set.ll.curr.element.identity==m)
			{
				return returnNode(AllMob.Set.ll.curr.element.base.identity);
			}
		}
//		if(AllMob.Set.ll.curr.element.identity==m)
//		{
//			return AllMob.Set.ll.curr.element.base;
//		}
		return returnNode(AllMob.Set.ll.curr.element.base.identity);
		
	}
	
	public void movePhone(int a,int b)//a-mobilephone,b-exchange 
	{
		if(!containsPhone(a))
		{
			System.out.println("Such a Phone is not present...!!!");
			return;
		}
		if(!containsNode(b))
		{
			System.out.println("Such an Exchange is not present...!!!");
			return;
		}
		
		switchOff(a);
		
		switchOn(a,b);
	}
	public Exchange lowestRouter(int a, int b)//a and b are Exchanges
	{
		
		if(!containsNode(a))
		{
			System.out.println("Such an Exchange is not present...!!!");
			return null;
		}
		if(!containsNode(b))
		{
			System.out.println("Such an Exchange is not present...!!!");
			return null;
		}
		if(returnNode(a).numChildren()!=0)
		{
			System.out.println("The first Exchange is not a base Exchange...!!!");
			return null;
		}
		if(returnNode(b).numChildren()!=0)
		{
			System.out.println("The Second Exchange is not a base Exchange...!!!");
			return null;
		}
		
		if(a==b)
		{
			return returnNode(a);
		}
		Exchange dummy;
		for( dummy=returnNode(a);dummy.identity!=0;dummy=dummy.par)
		{
			for(int  i=0;i<dummy.numChildren();i++)
			{
				if(dummy.subtree(i).containsNode(b))
				{
					return dummy;
				}
			}
		}
		return dummy;
	}
	
	public ExchangeList routeCall(int a, int b)// a and b are 2 mobile phone
	{
		if(!containsPhone(a))
		{
			System.out.print("The first Phone is not present");
			return null;
		}
		if(!containsPhone(b))
		{
			System.out.print("The Second Phone is not present");
			return null;
		}
		ExchangeList ans=new ExchangeList();
		Exchange dummy=lowestRouter(findPhone(a).identity,findPhone(b).identity);
		if(a==b)
		{
			System.out.print("Call is being done to the same Phone, Its base Directory is :"+ findPhone(a).identity);
			return null;
		}
		if(dummy==null)
		{
			return null;
		}
		
		Exchange dummyb=findPhone(b);
		if(dummy.identity==findPhone(a).identity)
		{
			Exchange add=new Exchange();
			add.identity=dummy.identity;
			
			ans.Insert(add);
			return ans;
		}
		for(Exchange dabba=findPhone(a);dabba.identity!=dummy.identity;dabba=dabba.par)
		{
			Exchange add=new Exchange();
			add.identity=dabba.identity;
			
			ans.Insert(add);
		}
		for(Exchange dabba=dummy;dabba.identity!=dummyb.identity;)
		{
			Exchange add=new Exchange();
			add.identity=dabba.identity;
			
			ans.Insert(add);
			for(int i=0;i<dabba.numChildren();i++)
			{
				
				if(dabba.subtree(i).containsNode(dummyb.identity))
				{
					
					dabba=dabba.child(i);
					break;
				}
			}
		}
		Exchange add=new Exchange();
		add.identity=dummyb.identity;
		
		ans.Insert(add);
		return ans;
	}
	
	
	
	
	
	public void performAction(String actionMessage)// : This the main stub method that you have to implement. It takes an action as a string. The list of actions, and their format will be described next.
	{
		String[] tokens = actionMessage.split(" ");
		String a1;
		a1=tokens[0];
		if(a1.equals("addExchange"))
		{
			addExchange(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]) );
		}
		else if(a1.equals("queryNthChild"))
		{
			queryNthchild(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
		}
		else if(a1.equals("switchOnMobile"))
		{
			switchOn( Integer.parseInt (tokens[1]) ,Integer.parseInt(tokens[2]) );
		}
		else if(a1.equals("switchOffMobile"))
		{
			switchOff(Integer.parseInt (tokens[1]) );
		}
		else if(a1.equals("queryMobilePhoneSet"))
		{
			System.out.print("queryMobilePhoneSet "+tokens[1]+":");
			queryMobileSet(Integer.parseInt(tokens[1]));
		}
		else if(a1.equals("queryFindPhone"))
		{
			System.out.print("queryfindPhone "+tokens[1]+":");
			Integer fun=Integer.parseInt(tokens[1]);
			if(!containsPhone(fun))
			{
				System.out.println("The Phone is not found or switched off...!!!");
			}
			else
			{
				System.out.println(findPhone(Integer.parseInt(tokens[1])).identity);
			}
		}
		else if(a1.equals("queryLowestRouter"))
		{
			System.out.print("queryLowestRouter  "+tokens[1]+" "+tokens[2]+":");
			Exchange dum=lowestRouter(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
			if(dum!=null)
			{
				System.out.println(dum.identity);
			}
		}
		else if(a1.equals("movePhone"))
		{
			//System.out.print("movePhone "+tokens[1]+" "+tokens[2]+":");
			movePhone(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
		}
		else if(a1.equals("queryFindCallPath"))
		{
			System.out.print("queryfindCallPath"+tokens[1]+" "+tokens[2]+":");
			ExchangeList route=routeCall(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
			Exchange cur=new Exchange();
			if(route!=null)
			{
				for(cur=route.head;cur.next!=null;cur=cur.next)
				{
					System.out.print(cur.identity+",");
				}
				System.out.print(cur.identity+",");
			}
			System.out.println(" ");
		}
		
	}
 
}
