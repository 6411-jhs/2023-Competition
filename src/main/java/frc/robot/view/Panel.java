package frc.robot.view;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

import frc.robot.RobotContainer;

import java.awt.Color;

public class Panel extends JPanel
{

	
	private SpringLayout panelLayout;
	private ButtonGroup typeGroup;
	private JRadioButton taxiAuto;
	private JRadioButton placetaxiAuto;

	public Panel()
	{
		super();
		
		this.setBackground(new Color(200, 150, 50 ));
		this.panelLayout = new SpringLayout();
		this.typeGroup = new ButtonGroup();
		this.taxiAuto = new JRadioButton("taxi auto");
		this.placetaxiAuto = new JRadioButton("place & taxi Auto");
		setupPanel();
		setupListeners();
		setupLayout();
		
	}
	
	private void setupPanel()
	{
		this.setLayout(panelLayout);
		this.add(taxiAuto);
		this.add(placetaxiAuto);
	
	}
	
	private void setupListeners()
	{
		taxiAuto.addActionListener(select -> {RobotContainer.setAutoCommand("taxi"); placetaxiAuto.setSelected(false); taxiAuto.setSelected(true);});
		placetaxiAuto.addActionListener(select -> {RobotContainer.setAutoCommand("place & taxi");taxiAuto.setSelected(false); placetaxiAuto.setSelected(true);});
	}
	
	private void setupLayout()
	{
	
		panelLayout.putConstraint(SpringLayout.NORTH, taxiAuto, 5, SpringLayout.NORTH, this);
		panelLayout.putConstraint(SpringLayout.NORTH, placetaxiAuto, 5, SpringLayout.SOUTH, taxiAuto);
	}
}