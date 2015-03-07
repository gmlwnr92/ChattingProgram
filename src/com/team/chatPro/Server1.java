package com.team.chatPro;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class Server1 extends JFrame implements Runnable { //서버클래스 상속인터 
	JTextArea ta = new JTextArea(); //텍스트아레나 그냥 전체화면
	JScrollPane jsp; //화면 늘어나면 스크롤
	Container cp; // 컨테이너 
	Vector vc = new Vector(); //백터 <<
	ServerSocket server; //서버 소켓 서버. 서버용 소켓?
	String getColor;																///////////////////////
	String getMsg;
	
	public Server1() { //서버 생성자
		cp = getContentPane(); //컨테이너 
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				for(int i=0;i<vc.size();i++)
				{
					Service sv=(Service)vc.elementAt(i);
					try {
						sv.out.write("800\n");
						sv.out.flush();
						try {
							sv.join();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						sv.s.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}		
				System.exit(0);
			}
	});

		jsp = new JScrollPane(ta); //텍아레나에 스크롤 넣음
		cp.add(jsp);	// 컨테이너에 스크롤 넣음
		setSize(500, 600); // 화면 크기 설정
		setVisible(true); // 화면 출력
	}

	public void run() {//서버 런에이블
		try {
			server = new ServerSocket(5000); //서버소켓을 생성
			ta.append("서버소켓 생성\n");
			
		} catch (IOException e) {
			ta.append("서버소켓 실패\n");
			e.printStackTrace();
		}
		while (true) {
			try {
				Socket s = server.accept(); //클라요청에 의한 소켓생성
				ta.append("클라이언트 접속 성공~\n");
				Service sv = new Service(s); //클래스 서비스 sv에 소켓 s 넣음
				sv.start();	//서비스 스타트
			} catch (IOException e) {
				System.out.println("클라이언트 접속 실패~");
				e.printStackTrace();
			}
		}
	}

	
	
	public class Service extends Thread {//서비스클래스 쓰레드 보고 읽게하기위해
		
		String name;
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket s;

		public Service(Socket s) {
			this.s = s;
			try {//주고받기 준비
				in = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(
						s.getOutputStream()));
				// System.out.println("서버IO 생성");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void send(String msg) {
			int s = vc.size();
			// ta.append("벡터 갯수 : "+s+"\n");

			for (int i = 0; i < s; i++) {
				Service sv = (Service) vc.elementAt(i);
				try {
					sv.out.write(msg + "\n");
					sv.out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		public void autoScroll() {
			int len = ta.getDocument().getLength();
			ta.setCaretPosition(len);
		}

		public void run() {
			while (true) {
				String msg = null;
				try {
					msg = in.readLine();
				} catch (Exception e) {
					System.out.println("read error");
					return;
				}
				ta.append("Server Receive : " + msg + "\n");
				autoScroll();
				StringTokenizer st = new StringTokenizer(msg, "|");
				int num = Integer.parseInt(st.nextToken());
				switch (num) {
				case 100:// 로그인
				{	
					
					name = st.nextToken();
					vc.addElement(this);
					String nickall = "";
					for (int i = 0; i < vc.size(); i++) {
						Service sv = (Service) vc.elementAt(i);
						nickall += sv.name + "%";
					}
					send("100|" + name + "|" + nickall);
				}
					break;
						
				case 200:// 메세지
					getColor = st.nextToken();											/////////////////////
					getMsg = st.nextToken();											////////////////////
					send("200|" + getColor + "|" + name + ">>" + getMsg);
					break;
					
				case 400:
				{
						String nick=st.nextToken();
						String message=st.nextToken();
						//sendWhom(400,nick,message);				
				}
				break;

					
				case 500://닉네임 변경
				{
					String newName=st.nextToken();
					send("500|"+name+"|"+newName);
					name=newName;
				}
				break;

					
				case 900://나가기
				{
							for(int i=0;i<vc.size();i++)
							{
								Service sv=(Service)vc.elementAt(i);
								if(name.equals(sv.name))
								{
									vc.removeElementAt(i);
									break;
								}
									
							}
							send("900|"+name+"\n");
							try {
								this.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
					}
				}// switch
			}// while
		}// run
	}// inner class


	public static void main(String[] args) {
		Server1 ss = new Server1(); //서버객체생성
		new Thread(ss).start(); //서버스타트
	}
}
