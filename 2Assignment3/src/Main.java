import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class Main implements ActionListener, FocusListener
{
	private JFrame			frame;
	private JTextField		txtEnterName;
	private JTextField		txtEnterVolume;
	private JTextField		txtEnterPrice;
	private JLabel			statusLabel;
	private OrderBook		list	= new OrderBook();
	private JRadioButton	rdbtnBid;
	private JRadioButton	rdbtnOffer;
	private JButton			outputBtn;
							
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Main window = new Main();
					window.frame.setVisible(true);
					window.frame.requestFocusInWindow();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main()
	{
		initialize();
	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setTitle("Assignment 3 part 2: Stock GUI");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		panel.setLayout(new MigLayout("", "[grow][grow]", "[30:n][30:n][30:n][grow][50px:n][grow][20px:n]"));
		
		txtEnterPrice = new JTextField();
		txtEnterPrice.setText("Enter price");
		panel.add(txtEnterPrice, "cell 0 0,grow");
		txtEnterPrice.setColumns(10);
		
		rdbtnBid = new JRadioButton("Bid");
		panel.add(rdbtnBid, "cell 1 0");
		
		txtEnterVolume = new JTextField();
		txtEnterVolume.setText("Enter volume");
		panel.add(txtEnterVolume, "cell 0 1,grow");
		txtEnterVolume.setColumns(10);
		
		rdbtnOffer = new JRadioButton("Offer");
		panel.add(rdbtnOffer, "cell 1 1");
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBid);
		group.add(rdbtnOffer);
		
		txtEnterName = new JTextField();
		txtEnterName.setText("Enter name");
		panel.add(txtEnterName, "cell 0 2,grow");
		txtEnterName.setColumns(10);
		
		outputBtn = new JButton("Output book");
		outputBtn.addActionListener(this);
		outputBtn.setActionCommand("output");
		panel.add(outputBtn, "cell 1 2,growx");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_1, "cell 0 3 2 3,grow");
		panel_1.setLayout(new MigLayout("", "[50px:n][100:n,grow][30:n][100px:n,grow][50:n]", "[25:n][grow][25:n]"));
		
		JButton submitBtn = new JButton("Submit");
		panel_1.add(submitBtn, "cell 1 1,grow");
		
		submitBtn.addActionListener(this);
		submitBtn.setActionCommand("submit");
		
		JButton resetBtn = new JButton("Reset");
		panel_1.add(resetBtn, "cell 3 1,grow");
		
		resetBtn.addActionListener(this);
		resetBtn.setActionCommand("reset");
		
		frame.getRootPane().setDefaultButton(resetBtn);
		
		statusLabel = new JLabel("Last order: none");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setForeground(Color.BLACK);
		statusLabel.setBackground(UIManager.getColor("Button.shadow"));
		statusLabel.setOpaque(true);
		panel.add(statusLabel, "cell 0 6 2 1,grow");
		
		txtEnterPrice.addFocusListener(this);
		txtEnterVolume.addFocusListener(this);
		txtEnterName.addFocusListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "submit":
				double price = 0;
				int volume = 0;
				String ID;
				
				if (isDouble(txtEnterPrice.getText()))
					price = Double.parseDouble(txtEnterPrice.getText());
				else
				{
					statusLabel.setText("<html><font color='red'>Error: Enter price as number!</font></html>");
					break;
				}
				
				if (isInt(txtEnterVolume.getText()))
					volume = Integer.parseInt(txtEnterVolume.getText());
				else
				{
					statusLabel.setText("<html><font color='red'>Error: Enter volume as integer!</font></html>");
					break;
				}
				
				ID = txtEnterName.getText();
				
				if (!rdbtnBid.isSelected() && !rdbtnOffer.isSelected())
				{
					statusLabel.setText("<html><font color='red'>Error: Select the type of order!</font></html>");
					break;
				}
				
				if (price != 0 && volume != 0 && !ID.equals("Enter name"))
				{
					if (rdbtnBid.isSelected())
						list.addBOrder(ID, price, volume);
					else
						list.addOOrder(ID, price, volume);
					statusLabel.setText("Last order: " + ((rdbtnBid.isSelected()) ? "\tBid - " : "\tOffer - ") + volume
							+ " shares at $" + price);
				} else
					statusLabel.setText("<html><font color='red'>Error: Please complete all the info!</font></html>");
				break;
			case "reset":
				txtEnterPrice.setText("Enter price");
				txtEnterVolume.setText("Enter volume");
				txtEnterName.setText("Enter name");
				break;
			case "output":
				list.outputBook();
				break;
		}
	}
	
	public void focusGained(FocusEvent arg0)
	{
		JTextField source = (JTextField) arg0.getComponent();
		source.setText("");
	}
	
	public void focusLost(FocusEvent arg0)
	{
	}
	
	public static boolean isDouble(String s)
	{
		try
		{
			Double.parseDouble(s);
		} catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}
	
	public static boolean isInt(String s)
	{
		try
		{
			Integer.parseInt(s);
		} catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}
	
}
