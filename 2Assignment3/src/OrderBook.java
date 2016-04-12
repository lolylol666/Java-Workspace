
public class OrderBook
{
	private Node head, bestBid = new Node(new BidOrder("", 0, 0), null, null),
			bestOffer = new Node(new OfferOrder("", 0, 0), null, null);
			
	public OrderBook()
	{
		head = null;
	}
	
	private class Node
	{
		private Order	order;
		private Node	previous, next;
						
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
		match();
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
		match();
	}
	
	private Node sort(double price)
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
	
	private void deleteOrder(Node node)
	{
		if (node.previous != null)
			node.previous.next = node.next;
		if (node.next != null)
			node.next.previous = node.previous;
		node = null;
	}
	
	private void match()
	{
		Node cursor = head;
		
		while (cursor.next != null)
		{
			Node pointer = cursor.next;
			while (cursor.order.getClass() != pointer.order.getClass() && cursor.order instanceof OfferOrder)
			{
				if (cursor.order.getPrice() < bestOffer.order.getPrice())
				{
					System.out.println("Match found:\nOff: " + cursor.order.getPrice() + "\t" + cursor.order.getVolume()
							+ "\t" + cursor.order.getID() + "\nBid: " + pointer.order.getPrice() + "\t"
							+ pointer.order.getVolume() + "\t" + pointer.order.getID());
					if (cursor.order.getVolume() < pointer.order.getVolume())
					{
						pointer.order.setVolume(pointer.order.getVolume() - cursor.order.getVolume());
						deleteOrder(cursor);
					} else if (cursor.order.getVolume() > pointer.order.getVolume())
					{
						cursor.order.setVolume(cursor.order.getVolume() - pointer.order.getVolume());
						pointer = pointer.next;
						deleteOrder(pointer.previous);
					} else
					{
						deleteOrder(cursor);
						deleteOrder(pointer);
					}
				} else if (pointer.order.getPrice() > bestBid.order.getPrice())
				{
					System.out.println("Match found:\nOff: " + cursor.order.getPrice() + "\t" + cursor.order.getVolume()
							+ "\t" + cursor.order.getID() + "\nBid: " + pointer.order.getPrice() + "\t"
							+ pointer.order.getVolume() + "\t" + pointer.order.getID());
					if (cursor.order.getVolume() < pointer.order.getVolume())
					{
						pointer.order.setVolume(pointer.order.getVolume() - cursor.order.getVolume());
						cursor = cursor.previous;
						deleteOrder(cursor.next);
					} else if (cursor.order.getVolume() > pointer.order.getVolume())
					{
						cursor.order.setVolume(cursor.order.getVolume() - pointer.order.getVolume());
						deleteOrder(pointer);
					} else
					{
						deleteOrder(cursor);
						deleteOrder(pointer);
					}
				}
			}
			cursor = cursor.next;
		}
		while (cursor != null)
		{
			if (cursor.order.getClass() != cursor.next.order.getClass())
			{
				if (cursor.order instanceof BidOrder)
				{
					bestBid = cursor;
					bestOffer = cursor.next;
				} else
				{
					bestOffer = cursor;
					bestBid = cursor.next;
				}
				break;
			}
			cursor = cursor.next;
		}
	}
	
	public void outputBook()
	{
		System.out.println("Order book: ");
		
		for (Node cursor = head; cursor != null; cursor = cursor.next)
		{
			System.out.println(cursor.order.toString());
		}
	}
	
	public void outputBBO()
	{
		System.out.println("Best Bid & Offer");
		
		System.out.println(bestOffer.order.toString());
		System.out.println(bestBid.order.toString());
	}
}
