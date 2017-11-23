package prototype;

import java.awt.Desktop;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueues {
	private PriorityQueue<String> queue = new  PriorityQueue<String>();
	private int temperature;
	private int crimeRate;
	private String current;
	private JLabel city;
	PriorityQueues(int temperature, int crimeRate){
		this.temperature = temperature;
		this.crimeRate = crimeRate;
		store_pq();
		display();
		
	}
	
	protected void store_pq(){
	
		queue.add("Arizona");
		queue.add("California");
		queue.add("Massachusetts");
		queue.add("Kentucky");
		queue.add("Illinois");
		 
		 
	}
	/*
	protected void temperature()throws FileNotFoundException, IOException{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("data" + "\\dataset.xls"));
		HSSFSheet sheet = workbook.getSheet("mcdonalds");
		
		int mcdonald_row = sheet.getLastRowNum() + 1;
		int mcdonald_col = sheet.getRow(0).getLastCellNum();
					
		for  ( int i = 0 ; i < mcdonald_row  ; i++) {
			HSSFRow row = sheet.getRow(i) ;
			for ( int j = 0 ; j < mcdonald_col; j++) {
				HSSFCell cell = row.getCell(j) ;
			
			}
		}
	}
*/

	/*public static void main(String arg[]) throws Exception
	{
		
		
	}
	*/
	public void display(){
		JFrame frame = new JFrame("CityFit");
		frame.setVisible(true);
		frame.setSize(350,230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		JPanel panel_display = new JPanel();
		frame.add(panel_display);
		panel_display.setLayout(null);

		JLabel label_display = new JLabel("The current city is: ");
		label_display.setFont(new Font("Serif", Font.PLAIN, 18));
		label_display.setBounds(35,20,200,30);
		panel_display.add(label_display);
		
	
		current = queue.remove();
		city = new JLabel(current);
		city.setFont(new Font("Serif", Font.BOLD, 18));
		city.setBounds(180,20,200,30);
		panel_display.add(city);
			
		JButton learn_button = new JButton("Learn More");
		panel_display.add(learn_button);
		learn_button.setBounds(120,70,100,30);
		learn_button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == learn_button){
					Desktop link = Desktop.getDesktop();
					try {
						link.browse(new URI("http://ca.lmgtfy.com/?q="+ current +"+wikipedia"));
					} catch (IOException | URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			
		JButton next_button = new JButton("Next");
		panel_display.add(next_button);
		next_button.setBounds(120,130,100,30);
		next_button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == next_button){
					if (!queue.isEmpty()){
						city.setVisible(false);
						current = queue.remove();
						city = new JLabel(current);
						city.setFont(new Font("Serif", Font.BOLD, 18));
						city.setBounds(180,20,200,30);
						panel_display.add(city);
					}
				}
			}

		});
	}
}
