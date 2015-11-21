import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class Window implements ActionListener
{
	private Window		handler;
	private JFrame		frmProcrannilatorPrototype;
	private JPanel		contentPane;
	private JLabel		lblTimer;
	private JButton		btnStart;
	private BigDecimal	second;
	private int			minute, hour;
	private MyTimer		task;
						
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Window window = new Window();
					window.frmProcrannilatorPrototype.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
		});
	}
	
	/**
	 * Create the application.
	 */
	public Window()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		handler = this;
		
		frmProcrannilatorPrototype = new JFrame();
		frmProcrannilatorPrototype.setTitle("Procrannilator - Prototype");
		frmProcrannilatorPrototype.setMinimumSize(new Dimension(500, 325));
		frmProcrannilatorPrototype.setBounds(100, 100, 500, 325);
		frmProcrannilatorPrototype.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel(null);
		frmProcrannilatorPrototype.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow]", "[grow][20px][grow]"));
		
		lblTimer = new JLabel("Timer");
		lblTimer.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTimer.setOpaque(true);
		lblTimer.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setForeground(new Color(255, 0, 0));
		lblTimer.setBackground(new Color(255, 255, 255));
		contentPane.add(lblTimer, "cell 0 0 2 1,grow");
		
		btnStart = new JButton("Start");
		btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnStart.setBorder(null);
		btnStart.setBackground(new Color(0, 255, 0));
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnStart, "cell 0 2 2 1,grow");
		btnStart.addActionListener(this);
		btnStart.setActionCommand("start");
		
		second = new BigDecimal(0);
		minute = 0;
		hour = 0;
		
		task = new MyTimer(second, minute, hour, handler);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "start":
				task.start(1, 1);
				btnStart.setBackground(Color.RED);
				btnStart.setText("stop");
				
				lblTimer.setForeground(Color.BLACK);
				btnStart.setActionCommand("stop");
				break;
			case "stop":
				task.stop();
				btnStart.setBackground(new Color(0, 255, 0));
				btnStart.setText("start");
				
				lblTimer.setForeground(Color.RED);
				btnStart.setActionCommand("start");
				break;
		}
		
	}
	
	void updateTimerText(String text)
	{
		lblTimer.setText(text);
	}
}
