import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Client extends JFrame{
	
	JTextField Field = new JTextField(10);
	JLabel Label = new JLabel();
	Socket socket;
	
	public Client() {
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(new JLabel("단어 입력 >>"));
		c.add(Field);
		c.add(Label);
		
		try {
		socket = new Socket("localhost",9999);
		}catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		Field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader Reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					BufferedWriter Writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					Writer.write(Field.getText()+"\n");
					Writer.flush();
					String result = Reader.readLine();
					Label.setText(result);
					
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		
		setTitle("스펠체크 클라이언트");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,100);
	}
	
	
	public static void main(String [] args) {
		new Client();
	}
}