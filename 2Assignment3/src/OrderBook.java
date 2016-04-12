
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
			if (position == head.previous)
			{
				head.previous = new Node(bidOrder, null, head);
				head = head.previous;
			} else
				position = new Node(bidOrder, position, ((position == null) ? null : position.next));
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
			if (position == head.previous)
			{
				head.previous = new Node(offerOrder, null, head);
				head = head.previous;
			} else
				position = new Node(offerOrder, position, position.next);
		}
		match();
	}
	
	private Node sort(double price)
	{
		Node current;
		current = head;
		while (current.order.getPrice() <= price)
		{
			if (current.next == null)
				return current;
			current = current.next;
		}
		return current.previous;
	}
	
	private Node deleteOrder(Node node)
	{
		if (node.previous != null)
		{
			node.previous.next = node.next;
		}
		if (node.next != null)
			node.next.previous = node.previous;
		if (node == head)
		{
			head = node.next;
		}
		node = null;
		return node;
	}
	
	private void match()
	{
		getBBO();
		while (bestBid.order.getPrice() >= bestOffer.order.getPrice() && bestOffer.order.getPrice() > 0)
		{
			System.out.println("Match found:\nOff: " + bestOffer.order.getPrice() + "\t" + bestOffer.order.getVolume()
					+ "\t" + bestOffer.order.getID() + "\nBid: " + bestBid.order.getPrice() + "\t"
					+ bestBid.order.getVolume() + "\t" + bestBid.order.getID() + "\n");
					
			if (bestBid.order.getVolume() < bestOffer.order.getVolume())
			{
				bestOffer.order.setVolume(bestOffer.order.getVolume() - bestBid.order.getVolume());
				bestBid = deleteOrder(bestBid);
			} else if (bestOffer.order.getVolume() < bestBid.order.getVolume())
			{
				bestBid.order.setVolume(bestBid.order.getVolume() - bestOffer.order.getVolume());
				bestOffer = deleteOrder(bestOffer);
			} else
			{
				bestBid = deleteOrder(bestBid);
				bestOffer = deleteOrder(bestOffer);
			}
			getBBO();
		}
	}
	
	private void getBBO()
	{
		Node cursor = head;
		if (bestBid == null)
			bestBid = new Node(new BidOrder("", 0, 0), null, null);
		if (bestOffer == null)
			bestOffer = new Node(new OfferOrder("", 0, 0), null, null);
			
		while (cursor != null)
		{
			if (cursor.order instanceof BidOrder)
			{
				if (bestBid.order.getPrice() < cursor.order.getPrice())
					bestBid = cursor;
			} else if (bestOffer.order.getPrice() != 0)
			{
				if (cursor.order.getPrice() < bestOffer.order.getPrice())
					bestOffer = cursor;
			} else
				bestOffer = cursor;
			cursor = cursor.next;
		}
	}
	
	public void outputBook()
	{
		System.out.println("Order book: ");
		Node cursor = head;
		if (head == null)
			System.out.println("The book is empty");
		else
		{
			while (cursor.next != null)
			{
				cursor = cursor.next;
			}
			
			for (; cursor != null; cursor = cursor.previous)
			{
				System.out.println(cursor.order.toString());
			}
			System.out.println("\n");
		}
	}
	
	public void outputBBO()
	{
		System.out.println("Best Bid & Offer");
		
		System.out.println(bestOffer.order.toString());
		System.out.println(bestBid.order.toString());
		System.out.println("\n");
	}
}
