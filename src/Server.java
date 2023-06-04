import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Server extends JFrame{
	ArrayList<String> words = new ArrayList<String>();
	JTextArea area = new JTextArea();
	
	public Server() {
		readWord();
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(area);
		area.setEnabled(false);
		area.setDisabledTextColor(Color.black);
		
		setTitle("영어 스펠 체크 서버");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		
		try {
			ServerSocket server = new ServerSocket(9999);
			
			while(true) {
				Socket socket = server.accept();
				area.append("클라이언트 접속됨!"+"\n");
				Multi multi = new Multi(socket);
				multi.start();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readWord() {
		String file = "c:\\Temp\\words.txt";
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        String line;
	        
	        while ((line = reader.readLine()) != null) {
	            words.add(line);
	        }
	        
	        reader.close();
	        area.append("words.text 읽기 완료\n");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	class Multi extends Thread{
		
		Socket socket;
		
		public Multi(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			try {
				BufferedReader Reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter Writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				while(true) {
					String word = Reader.readLine();
					if(word == null)
						break;
					
					if(words.indexOf(word) >= 0) {
						area.append(word+"=Yes"+"\n");
						Writer.write(word+"는 Yes" + "\n");
						Writer.flush();
					}
					else {
						area.append(word+"=No"+"\n");
						Writer.write(word + "는 No" + "\n");
						Writer.flush();
					}
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
