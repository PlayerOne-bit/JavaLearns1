package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.Toolkit;

import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JPanel;


public class design {

	private JFrame frame;
	private JTextField txtInput;
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
					design window = new design();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public design() {
		initialize();
	}

	
	public double value1=0;
	public String operator="";
	public ArrayList<Double> num = new ArrayList<>();
	public boolean try1;
	
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setFocusCycleRoot(true);
		frame.getContentPane().setFocusTraversalPolicyProvider(true);
		frame.setBackground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(153, 0, 0));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Joven\\Pictures\\Icons\\Hu tao Wallpaper.ico"));
		
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtInput = new JTextField();
		txtInput.setBounds(56, 89, 354, 46);
		txtInput.setHorizontalAlignment(SwingConstants.CENTER);
		txtInput.setBackground(new Color(153, 51, 51));
		txtInput.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(txtInput);
		txtInput.setColumns(10);
		
		JLabel equals = new JLabel("");
		equals.setBounds(310, 28, 164, 35);
		equals.setHorizontalAlignment(SwingConstants.LEFT);
		equals.setForeground(Color.WHITE);
		equals.setBackground(Color.BLACK);
		frame.getContentPane().add(equals);
		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(56, 28, 244, 35);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(44, 146, 206, 180);
		panel.setLayout(new GridLayout(4, 3, 0, 0));
		
		JButton num1 = new JButton("1");
		num1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
	
		num1.setForeground(new Color(204, 51, 51));
		
		num1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"1");
				lblNewLabel.setText(lblNewLabel.getText()+"1");
			}
		});
		
		JButton num2 = new JButton("2");
		num2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num2.setForeground(new Color(204, 51, 51));
		num2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"2");
				lblNewLabel.setText(lblNewLabel.getText()+"2");
			}
		});
		
		JButton num3 = new JButton("3");
		num3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num3.setForeground(new Color(204, 51, 51));
		
		JButton num4 = new JButton("4");
		num4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num4.setForeground(new Color(204, 51, 51));
		
		JButton num5 = new JButton("5");
		num5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num5.setForeground(new Color(204, 51, 51));
		
		JButton num6 = new JButton("6");
		num6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num6.setForeground(new Color(204, 51, 51));
		
		JButton num7 = new JButton("7");
		num7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num7.setForeground(new Color(204, 51, 51));
		
		JButton num8 = new JButton("8");
		num8.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num8.setForeground(new Color(204, 51, 51));
		
		JButton num9 = new JButton("9");
		num9.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num9.setForeground(new Color(204, 51, 51));
		
		JButton num0 = new JButton("0");
		num0.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		num0.setForeground(new Color(204, 51, 51));
		
		JButton decimal = new JButton(".");
		decimal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<txtInput.getText().length();i++) {
					if(txtInput.getText().charAt(i)=='.') {
						return;
					}
					
				}
				try1=true;
				txtInput.setText(txtInput.getText()+".");
				lblNewLabel.setText(lblNewLabel.getText()+".");
			}
		});
		decimal.setForeground(new Color(153, 51, 51));
		
		JButton erase = new JButton("<-");
		erase.setFont(new Font("Tahoma", Font.PLAIN, 30));
		erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numa="";
				String numb="";
				String op="";
				if(!try1) {
					for(int i=0; i<operator.length()-1;i++) {
						op+=operator.charAt(i);
					}
					try1=true;
					operator=op;
				}
				
				for(int i=0; i<lblNewLabel.getText().length()-1;i++) {
					numa+=lblNewLabel.getText().charAt(i);
				}
				
				for(int i=0; i<txtInput.getText().length()-1;i++) {
					numb+=txtInput.getText().charAt(i);
				}
				
				
				lblNewLabel.setText(numa);
				txtInput.setText(numb);
				
			}
		});
		erase.setForeground(new Color(153, 51, 51));
		frame.getContentPane().add(panel);
		panel.add(num1);
		panel.add(num2);
		panel.add(num3);
		panel.add(num4);
		panel.add(num5);
		panel.add(num6);
		panel.add(num7);
		panel.add(num8);
		panel.add(num9);
		panel.add(num0);
		panel.add(decimal);
		panel.add(erase);
		num0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"0");
				lblNewLabel.setText(lblNewLabel.getText()+"0");
			}
		});
		num9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"9");
				lblNewLabel.setText(lblNewLabel.getText()+"9");
			}
		});
		num8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"8");
				lblNewLabel.setText(lblNewLabel.getText()+"8");
			}
		});
		num7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"7");
				lblNewLabel.setText(lblNewLabel.getText()+"7");
			}
		});
		num6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"6");
				lblNewLabel.setText(lblNewLabel.getText()+"6");
			}
		});
		num5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"5");
				lblNewLabel.setText(lblNewLabel.getText()+"5");
			}
		});
		num4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"4");
				lblNewLabel.setText(lblNewLabel.getText()+"4");
			}
		});
		num3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try1=true;
				txtInput.setText(txtInput.getText()+"3");
				lblNewLabel.setText(lblNewLabel.getText()+"3");
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(272, 146, 68, 180);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton add = new JButton("+");
		panel_1.add(add);
		add.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add.setForeground(new Color(153, 51, 51));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(try1) {
					operator += '+';
					lblNewLabel.setText(lblNewLabel.getText()+"+");
					num.add(Double.parseDouble(txtInput.getText()));
					txtInput.setText("");
					try1=false;
				}
			}
		});
		
		frame.getContentPane().add(panel_1);
		
		JButton subtract = new JButton("-");
		panel_1.add(subtract);
		subtract.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		subtract.setForeground(new Color(153, 51, 51));
		
		JButton multiply = new JButton("*");
		panel_1.add(multiply);
		multiply.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		multiply.setForeground(new Color(204, 51, 51));
		
		JButton divide = new JButton("/");
		panel_1.add(divide);
		divide.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		divide.setForeground(new Color(153, 51, 51));
		divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(try1) {
					lblNewLabel.setText(lblNewLabel.getText()+"/");		
					operator += '/';
					num.add(Double.parseDouble(txtInput.getText()));
					txtInput.setText("");
					try1=false;
				}
			}
		});
		multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(try1) {
					lblNewLabel.setText(lblNewLabel.getText()+"*");
					operator += '*';
					num.add(Double.parseDouble(txtInput.getText()));
					txtInput.setText("");
					try1=false;
				}
			}
		});
		subtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(try1) {
					lblNewLabel.setText(lblNewLabel.getText()+"-");
					operator += '-';
					num.add(Double.parseDouble(txtInput.getText()));
					txtInput.setText("");
					try1=false;
				}
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(350, 146, 60, 150);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton result = new JButton("=");
		
		
		panel_2.add(result);
		result.setFont(new Font("Tahoma", Font.PLAIN, 30));
		result.setForeground(new Color(153, 51, 51));
		
		JButton clearAll = new JButton("C");
		panel_2.add(clearAll);
		clearAll.setFont(new Font("Tahoma", Font.PLAIN, 30));
		clearAll.setForeground(new Color(153, 51, 51));
		clearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInput.setText("");
				value1=0; 
				operator="";
				num.clear();
				lblNewLabel.setText("");
				equals.setText("");

			}
		});
		result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				num.add(Double.parseDouble(txtInput.getText()));
				boolean error=false;
				
				value1=num.get(0);
				
				
				for(int i=0; i<operator.length();i++) {
					if(operator.charAt(i)=='+') {
						value1+=num.get(i+1);
					} else if(operator.charAt(i)=='-') {
						value1-=num.get(i+1);
					}  else if(operator.charAt(i)=='*') {
						value1*=num.get(i+1);
					}  else if(operator.charAt(i)=='/') {
						if(num.get(i+1)!=0) {
							value1/=num.get(i+1);
						}else {
							error=true;
						}
					}  
				}
				
				if(!error) {
					txtInput.setText(Double.toString(value1));
					equals.setText("= "+Double.toString(value1));
					lblNewLabel.setText(Double.toString(value1));
				}else {
					txtInput.setText("");
					equals.setText("= Error");
					lblNewLabel.setText("");
				}
				
				value1=0; 
				operator="";
				num.clear();
				
				try1=true;
				}catch(Exception x) {
					txtInput.setText("");
					equals.setText("Error");
					lblNewLabel.setText("");
				}
			}
		});
		
		txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            	String numb="";
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    result.doClick();
                }
                if (e.getKeyCode() == KeyEvent.VK_1) {
                    num1.doClick();
                    for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                    txtInput.setText(numb);
                }
                if (e.getKeyChar() == KeyEvent.VK_2) {
                	num2.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_3) {
                	num3.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_4) {
                	num4.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_5) {
                	num5.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_6) {
                	num6.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_7) {
                	num7.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_8) {
                	num8.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_9) {
                	num9.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_0) {
                	num0.doClick();
                	for(int i=0; i<txtInput.getText().length()-1;i++) {
    					numb+=txtInput.getText().charAt(i);
    				}
                	txtInput.setText(numb);
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                	erase.doClick();
                }
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                	clearAll.doClick();
                }
                if (e.getKeyCode() == KeyEvent.VK_PLUS) {
                	add.doClick();	
                	txtInput.setText("");
                }
                if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                	subtract.doClick();
                	txtInput.setText("");
                }
                if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
                	multiply.doClick();
                	txtInput.setText("");
                }
                if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
                	divide.doClick();
                	txtInput.setText("");
                }
            }
        });		
	}
}
