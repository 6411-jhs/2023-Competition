package frc.robot.view;

import javax.swing.JFrame;

public class Frame extends JFrame
{

	
	private Panel panel;
	
	public Frame()
	{
		super();
		this.panel = new Panel();
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(panel);
		this.setTitle("Robot Frame");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setVisible(true);
	}
}
