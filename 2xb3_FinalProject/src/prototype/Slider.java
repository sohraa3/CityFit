package prototype;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.*;

import edu.princeton.cs.algs4.MaxPQ;

public class Slider extends JFrame implements ChangeListener{

	private JSlider slider_temperature, slider_crimeRate, slider_Criteria3;
	private JLabel label_temperature, label_crimeRate, label_description, label_Criteria3;
	private JButton finish_button;
	private int value_Criteria1, value_Criteria2, value_Criteria3; 

	public int getValue_Criteria3() {
		return value_Criteria3;
	}
	public void setValue_Criteria3(int value_Criteria3) {
		this.value_Criteria3 = value_Criteria3;
	}


	
	
	public Slider(){	
		setLayout(new FlowLayout());
		label_description = new JLabel("Please rank the following criteria on a scale from 1 to 10");
		add(label_description);
		//---------- Temperature ----------//
	
		slider_temperature = new JSlider(JSlider.HORIZONTAL,0,10,5);
		//slider_temperature.setBounds(20,30,40,200);
		slider_temperature.setMajorTickSpacing(2);
		slider_temperature.setPaintTicks(true);
		add(slider_temperature);
		 
		label_temperature = new JLabel("              Young Age: 5            ");
		add(label_temperature);
		
		//---------- Crime Rate ----------//
		slider_crimeRate = new JSlider(JSlider.HORIZONTAL,0,10,5);
		slider_crimeRate.setMajorTickSpacing(2);
		slider_crimeRate.setPaintTicks(true);
		add(slider_crimeRate);
		
		label_crimeRate = new JLabel("Equal gender population: 5");
		add(label_crimeRate);
		
		slider_temperature.addChangeListener(this);
		slider_crimeRate.addChangeListener(this);
		
		//---------- Crime Rate ----------//
				slider_Criteria3 = new JSlider(JSlider.HORIZONTAL,0,10,5);
				slider_Criteria3.setMajorTickSpacing(2);
				slider_Criteria3.setPaintTicks(true);
				add(slider_Criteria3);
				
				label_Criteria3 = new JLabel("         High Population: 5       ");
				add(label_Criteria3);
				
				slider_temperature.addChangeListener(this);
				slider_crimeRate.addChangeListener(this);
				slider_Criteria3.addChangeListener(this);

	
		JButton finish_button = new JButton("Finish");
		finish_button.setBounds(100,100,50,50);
		add(finish_button);
		finish_button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == finish_button){
					City[] cityArray=setRates.setPoints(value_Criteria1, value_Criteria2, value_Criteria3);
					MaxPQ pq = new MaxPQ(cityArray.length,City.comparePoints);
					for(City c:cityArray){
						pq.insert(c);
					}
					pq.display();
				}
			}
		});
	}


		public void stateChanged(ChangeEvent e){
			value_Criteria1 = slider_temperature.getValue();
			value_Criteria2 = slider_crimeRate.getValue();
			value_Criteria3 = slider_Criteria3.getValue();
			
			label_temperature.setText("              Young Age: " + value_Criteria1 + "            ");
			label_crimeRate.setText("Equal gender population: " + value_Criteria2);
			label_Criteria3.setText("         High Population: " + value_Criteria3 + "       ");
	
			
		}
	

	public static void main(String args[]){
		Slider gui = new Slider();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(420,200);
		gui.setLocation(dim.width/2-gui.getSize().width/2, dim.height/2-gui.getSize().height/2);
		gui.setVisible(true);
		gui.setTitle("CityFit");
	}






	
	}
