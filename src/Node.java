public class Node 
{
	public MobilePhone element;
	public Node next;
	public Node()
	{
		element=null;
		next=null;
	}
	public Node(MobilePhone e, Node n)
	{
		element =e;
		next  = n;
	}
	public MobilePhone getElement()
	{
		return element;
	}
	public Node getNext()
	{
		return next;
	}
	public void  setElement(MobilePhone newElem)
	{
		element=newElem;
	}
	public void setNext(Node newNext)
	{
		next=newNext;
	}
}
