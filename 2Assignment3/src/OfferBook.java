
public class OfferBook
{
	private Node head, bestBid, bestOffer;
	
	public OfferBook()
	{
		head = null;
	}
	
	private class Node
	{
		private Order	order;
		private Node	previous, next;
						
		public Node()
		{
			order = null;
			previous = next = null;
		}
		
		public Node(Order order, Node previous, Node next)
		{
			this.order = order;
			this.previous = previous;
			this.next = next;
			if (previous != null)
				previous.next = this;
			if (next != null)
				next.previous = this;
		}
	}
	
	public void addBOrder(String ID, double price, int volume)
	{
		BidOrder bidOrder = new BidOrder(ID, price, volume);
		
		if (head == null)
		{
			head = new Node(bidOrder, null, null);
		} else
		{
			Node position = sort(price);
			if (position == head)
				head = new Node(bidOrder, null, null);
			else if (position == null)
			{
				head.previous = new Node(bidOrder, position, head);
				head = head.previous;
			} else
				position.next = new Node(bidOrder, position, position.next);
		}
	}
	
	public void addOOrder(String ID, double price, int volume)
	{
		OfferOrder offerOrder = new OfferOrder(ID, price, volume);
		
		if (head == null)
		{
			head = new Node(offerOrder, null, null);
		} else
		{
			Node position = sort(price);
			if (position == head)
				head = new Node(offerOrder, null, null);
			else if (position == null)
			{
				head.previous = new Node(offerOrder, position, head);
				head = head.previous;
			} else
				position.next = new Node(offerOrder, position, position.next);
		}
	}
	
	public Node sort(double price)
	{
		Node current;
		current = head;
		if (current == null)
			return head;
		while (current.order.getPrice() <= price)
		{
			if (current.next == null)
				return current;
			current = current.next;
		}
		return current.previous;
	}
	
	public void outputBook()
	{
	
	}
	
	public void outputBBO()
	{
	
	}
}
