package com.iflytek.watch;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Watch extends JFrame {
	private JLabel date = null;
	private JLabel time = null;
	private JButton setDateBtn = null;
	private JButton setTimeBtn = null;
	private TextField setDateTf = null;
	private TextField setTimeTf = null;
	
	public Watch(){
		this.setTitle("显示并设置系统时间");
		this.setSize(800, 100);
		this.setLocation(500, 500);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
	}
	private void init(){
		Font myFont = new Font("MyFont",Font.BOLD,20);
		setDateTf = new TextField();
		setDateTf.setColumns(10);
		setDateTf.setFont(myFont);
		setTimeTf = new TextField();
		setTimeTf.setColumns(10);
		setTimeTf.setFont(myFont);
		date = new JLabel("日期" + getStringDateTime("yyyy-MM-dd"));
		date.setFont(myFont);
		time = new JLabel("时间" + getStringDateTime("HH:mm:ss"));
		time.setFont(myFont);
		setDateBtn = new JButton("设置日期");
		
		setTimeBtn = new JButton("设置时间");
		
		//单机设置日期
		setDateBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
//				System.out.println("设置日期");
				String date = setDateTf.getText();
				String regexp = "^(?:([0-9]{4}-(?:(?:0?[1,3-9]|1[0-2])-(?:29|30)|"
						+ "((?:0?[13578]|1[02])-31)))|"
						+ "([0-9]{4}-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|1\\d|2[0-8]))|"
						+ "(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|"
						+ "(?:0[48]00|[2468][048]00|[13579][26]00))-0?2-29)))$";
				Pattern pattern = Pattern.compile(regexp);
				Matcher matcher = pattern.matcher(date);
				boolean match = matcher.matches();
				if(date.equals("") || !match) {
					System.out.println("日期输入为空或格式不匹配");
					date = getStringDateTime("yyyy-MM-dd");
				}
				try {
					   Process p = Runtime.getRuntime().exec("cmd /c date " + date);
					//   p.waitFor();
					   BufferedReader br = new BufferedReader(new InputStreamReader(p
					     .getInputStream()));

					   if (!br.ready()) {
					    Thread.sleep(100);
					   }
					   while (true) {
					    String s = br.readLine();
					    if (s == null)
					     break;
					    System.out.println(s);
					   }
					   br.close();
					  } catch (IOException e1) {
					   e1.printStackTrace();
					  } catch (InterruptedException e1) {
					   e1.printStackTrace();
					  }
				update("yyyy-MM-dd");
			}
		});
		//单机设置时间
		setTimeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				String time = setTimeTf.getText();
				String regexp = "(20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
//				^((0[0-9]|1[0-9]|2[0-3])\:([0-5][0-9])\:([0-5][0-9]))$
//				(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$
				Pattern pattern = Pattern.compile(regexp);
				Matcher matcher = pattern.matcher(time);
				boolean match = matcher.matches();
				if(time.equals("") || !match) {
					System.out.println("时间输入为空或格式不匹配");
					time = getStringDateTime("HH:mm:ss");
				}
				
				try {
					   Process p = Runtime.getRuntime().exec("cmd /c time " + time);
					//   p.waitFor();
					   BufferedReader br = new BufferedReader(new InputStreamReader(p
					     .getInputStream()));

					   if (!br.ready()) {
					    Thread.sleep(100);
					   }
					   while (true) {
					    String s = br.readLine();
					    if (s == null)
					     break;
					    System.out.println(s);
					   }
					   br.close();
					  } catch (IOException e1) {
					   e1.printStackTrace();
					  } catch (InterruptedException e1) {
					   e1.printStackTrace();
					  }
				//
			}
		});
		this.add(date);
		this.add(time);
		this.add(setDateTf);
		this.add(setTimeTf);
		this.add(setDateBtn);
		this.add(setTimeBtn);
		
	}
	
	private String getStringDateTime(String format) {
		Date myTime = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat(format);
		 String dateString = formatter.format(myTime);
		return dateString;
	}
	public void update(String format){
		if("HH:mm:ss" == format){
			time.setText("时间" + getStringDateTime(format));
		}else if("yyyy-MM-dd" == format){
			date.setText("日期" + getStringDateTime(format));
		}
	}
}
