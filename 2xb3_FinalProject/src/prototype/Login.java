package prototype;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;				
import java.io.*;				
import java.text.*;	
public class Login {

	private JLabel label_login;
	private JPanel panel_login, panel_preference;
	private JButton login_button, signUp_button;
	private Map<String, String> map = new HashMap();	
	
	protected void Users(){
			try {																		// open and read the txt file
				 FileReader fr = new FileReader("data" + "\\Users.txt");
		         BufferedReader br = new BufferedReader(fr);
		         String data;
		         while ((data = br.readLine()) != null){								// reads from the text file
		        	 String[] temp = data.split(",");					// split at ,
		        	 temp[1] = temp[1].trim();
		        	 map.put(temp[0], temp[1]);	        	 
		         }
		         br.close();
			} catch(IOException e) {										// catch exception
		        System.out.println("File not found !");
			}
		}
	
	protected void login(){

		JFrame frame = new JFrame("CityFit");
		frame.setVisible(true);
		frame.setSize(300,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		

		JPanel panel_login = new JPanel();
		frame.add(panel_login);
		panel_login.setLayout(null);

		JLabel label_login = new JLabel("Welcome to CityFit");
		label_login.setFont(new Font("Serif", Font.PLAIN, 22));
		label_login.setBounds(50,30,200,30);
		panel_login.add(label_login);

		JButton login_button = new JButton("Login");
		login_button.setBounds(90,90,100,30);
		panel_login.add(login_button);
		login_button.addActionListener(new ActionListener(){;
		
		
		// ---------- login ---------- //

				public void actionPerformed(ActionEvent e){
					if (e.getSource() == login_button){
						String username = JOptionPane.showInputDialog("Enter Username");
						String password = JOptionPane.showInputDialog("Enter Password");

		    	if (map.containsKey(username)){
		    		if (map.get(username).equals(password)){
		    			Slider slider = new Slider();
		    			slider.main(null);
		    		}else{
		    			JOptionPane.showMessageDialog(null, "Incorrect login");
		    		}
				
		    	}else{
		    		
		    		JOptionPane.showMessageDialog(null, "Incorrect login");
		    	}

						}
					}
				});

			JButton signUp_button = new JButton("Sign Up");
			signUp_button.setBounds(90,150,100,30);
			panel_login.add(signUp_button);
			signUp_button.addActionListener(new ActionListener(){;
				
// ---------- SignUp ---------- //
				public void actionPerformed(ActionEvent e){
					String username=null;
					String password=null;
					if (e.getSource() == signUp_button){
						username = JOptionPane.showInputDialog("Enter New Username");
						while (map.containsKey(username)){
							username = JOptionPane.showInputDialog("Username already taken, Enter New Username");
						}
						password = JOptionPane.showInputDialog("Enter Password");
						String password_verify = JOptionPane.showInputDialog("Verify Password");
						while (!password.equals(password_verify)){
							password = JOptionPane.showInputDialog("Password did not match, re-enter Password");
							password_verify = JOptionPane.showInputDialog("Verify Password");
						}
					}
					 map.put(username, password);
					try {															
						FileWriter fw = new FileWriter("data" + "\\Users.txt", true); 	// open and read the txt file
				        BufferedWriter bw = new BufferedWriter(fw);
				        bw.newLine();
				        bw.write(username);
				        bw.write(", ");
				        bw.write(password);
				    	bw.close();
				   
					} catch(IOException a) {										// catch exception
				        System.out.println("File not found !");
					}
				
				}
			});

		}
	
	public static void main(String arg[]){
		Login login = new Login();
		login.Users();
		login.login();
	
	}
}
