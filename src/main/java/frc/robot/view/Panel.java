package frc.robot.view;

import javax.swing.JPanel;

import java.awt.Color;

public class Panel extends JPanel
{

	
	public Panel()
	{
		super();
		
		setupPanel();
		setupListeners();
		setupLayout();
		
	}
	
	private void setupPanel()
	{
		this.setBackground(new Color(200, 150, 50 ));
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
	
	}
}