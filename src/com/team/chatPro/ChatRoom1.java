package com.team.chatPro;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;




public class ChatRoom1 extends JFrame implements ItemListener, ActionListener, Runnable {
	//챗룸1 제이프레임 상속, 인터페이스 액션리스너, 런에이블
	JFrame colorFrame; //컬러프레임
	JPanel global = new JPanel(); //전체 판넬
	JPanel buttonPanel = new JPanel(); //버튼쪽판넬
	JButton close = new JButton(); //닫기버튼
	JButton txtSave = new JButton(); //대화저장버튼
	JButton rename = new JButton();
	JButton fileSend = new JButton();
	JButton btColor = new JButton(); //텍스트컬러 버튼
	JButton paper1 = new JButton();
	StyledDocument document = new DefaultStyledDocument();											//////////////////////////
	JTextPane area = new JTextPane(document); //대화화면												//////////////////////////
	StyleContext context = new StyleContext();														////////////////////////
	Style style = context.getStyle(StyleContext.DEFAULT_STYLE);										////////////////////////
	SimpleAttributeSet attributes =new SimpleAttributeSet();											/////////////////////////
	SimpleAttributeSet attributes2 =new SimpleAttributeSet();
	
	JTextField send = new JTextField(); //센트 텍필
	List list = new List(); //??
	JScrollPane jsP = new JScrollPane(); //스크롤판넬
	JScrollBar jsB; //스크롤 바
	Font f = new Font("맑은고딕체", 0, 12); //폰트
	BufferedReader in; //버퍼에 수신할 거
	BufferedWriter out; //버퍼에 송신할 거
	Socket s; //소켓 생성
	
	int idx = -1; //??
	String item; // 아이템 저장
	
	
	String id; //입장한 아디
	
	WaitRoom1 wait;
	File file;
	FileReader reader;
	FileWriter writer;
	
	ReName re = new ReName();
	Paper paper=new Paper();

	String color = "#000000";																			/////////////////////////
	String getColor;																				////////////////////////
	String getMsg;
	String currentDate = new SimpleDateFormat("yy년MM월dd일hh시mm분ss초").format(new java.util.Date());
	
	public ChatRoom1() {
		try{
		roomInit();
		}catch(Exception e){}
	}
	
	public ChatRoom1(WaitRoom1 wait, String id) { //기본생성자
		this.wait = wait;
		this.id = id;
		try {
			roomInit(); //대화방 호출
			connectProcess(); //연결프로세스 서버와 연결
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void roomInit() throws Exception { //대화방 메소드
		attributes.addAttribute(StyleConstants.CharacterConstants.Foreground, Color.decode(color));
		
		this.setTitle("채팅방"); //프레임 제목
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //일시중지 
		this.getContentPane().setBackground(new Color(249, 255, 255)); //화면옵션 이하생략
		this.setResizable(false); //생략
		this.getContentPane().setLayout(null); 
		global.setEnabled(false); 
		global.setBorder(BorderFactory.createEtchedBorder()); 
		global.setOpaque(false);
		global.setRequestFocusEnabled(false);
		global.setBounds(new Rectangle(4, 3, 441, 282));
		global.setLayout(null);
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(new Rectangle(316, 129, 120, 170));
		buttonPanel.setLayout(null);
		
		
		
		
		rename.setBounds(0, 1, 120, 25);
		rename.setFont(f);
		rename.setBorder(BorderFactory.createRaisedBevelBorder());
		rename.setText("대화명변경");
		
		
		btColor.setBounds(0, 31, 120, 25);												/////////////////////
		btColor.setFont(f);																/////////////////////
		btColor.setBorder(BorderFactory.createRaisedBevelBorder());									/////////////////////
		btColor.setText("텍스트 컬러 수정");
		
		paper1.setBounds(0, 61, 120, 25);
		paper1.setFont(f);
		paper1.setBorder(BorderFactory.createRaisedBevelBorder());
		paper1.setText("쪽지보내기");
		
		fileSend.setBounds(0, 91, 120, 25);
		fileSend.setFont(f);
		fileSend.setBorder(BorderFactory.createRaisedBevelBorder());
		fileSend.setText("파일보내기");
		

		txtSave.setBounds(0, 121, 120, 25);
		txtSave.setFont(f);
		txtSave.setBorder(BorderFactory.createRaisedBevelBorder());
		txtSave.setText("채팅내용저장");
		
		close.setBounds(0, 151, 120, 25);
		close.setFont(f);
		close.setBorder(BorderFactory.createRaisedBevelBorder());
		close.setText("나    가    기");
		
		send.setBounds(new Rectangle(7, 249, 306, 27));
		list.setBounds(new Rectangle(316, 5, 120, 107));
		jsP.setAutoscrolls(true);
		jsP.setBounds(new Rectangle(7, 5, 306, 238));

		this.getContentPane().add(global, null);

		buttonPanel.add(btColor);
		buttonPanel.add(paper1);
		buttonPanel.add(rename);
		buttonPanel.add(fileSend);
		buttonPanel.add(txtSave);
		buttonPanel.add(close);
		
		global.add(send, null);
		global.add(buttonPanel, null);
		global.add(list, null);
		global.add(jsP, null);
		jsP.setViewportView(area);

		getScreenSize(455, 317);

		setVisible(true);
		setResizable(false);

		btColor.addActionListener(this);
		txtSave.addActionListener(this);
		fileSend.addActionListener(this);
		close.addActionListener(this);
		send.addActionListener(this);
		
		rename.addActionListener(this);
		re.ok.addActionListener(this);
		re.newname.addActionListener(this);
		re.cancel.addActionListener(this);
		
		paper1.addActionListener(this);
		paper.ok.addActionListener(this);
		paper.cancel.addActionListener(this);
		paper.answer.addActionListener(this);
		
		
		list.addItemListener(this);


		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				closeProcess();
			}			
		});
		
	}
	

	// 화면 중앙에 위치하게 하는 메소드 생략
	public void getScreenSize(int x, int y) {
		this.setSize(x, y);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		Dimension d2 = this.getSize();

		this.setLocation((int) (d.width - d2.getWidth()) / 2,
				(int) (d.height - d2.getHeight()) / 2);
	}

	public void autoScroll() { //오토스크롤 
		int len = area.getDocument().getLength(); //에어리어 길이
		area.setCaretPosition(len); //포지션을 길이에 맞춤
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 버튼을 클릭하면
		if (e.getSource() == txtSave) {
			// 텍스트저장
			try {
				writer = new FileWriter(id + "의 채팅내용 " + currentDate + ".txt");
				out = new BufferedWriter(writer);
				String str = area.getText();
				out.write(str);
				out.newLine();
				JOptionPane.showMessageDialog(null, "채팅내용이 저장되었습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException ie) {
				ie.printStackTrace();
				// TODO: handle exception
			} finally {
				try {
					out.close();
					writer.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}else if (e.getSource() == close) {
			MemberDao1 dao = new MemberDao1(); //다오 객체 생성
			dao.connectMember(id);
			new WaitRoom1(this, id); //대기방입장
			closeProcess(); //연결해제
		} else if (e.getSource() == send){
			sendProcess(); //텍필에 있는 값 에어리에게 보내기
		} else if(e.getSource()==rename)
		{
			re.oldname.setText(id);
			re.setVisible(true);
		}
		else if(e.getSource()==re.ok||e.getSource()==re.newname)
		{
			renameProcess();
		}
		else if(e.getSource()==re.cancel)
		{
			re.setVisible(false);
		}//쪽지보내기
		else if(e.getSource()==paper1)
		{
			if(idx==-1)
			{
				JOptionPane.showMessageDialog(this,"쪽지보낼 상대방을 선택하세요~");
				return;
			}
			if(id.equals(item))
			{
				JOptionPane.showMessageDialog(this,"자기 자신한테는 쪽지보내면 안되요~");
				return;
			}
			paper.to.setText(item);
			paper.from.setText(id);
			paper.letter.requestFocus();
			paper.setVisible(true);
			paper.card.show(paper.south1,"ok");
		}
		else if(e.getSource()==paper.ok)
			paperProcess();
		else if(e.getSource()==paper.answer)
		{
			String to=paper.to.getText();
			String from=paper.from.getText();
			paper.to.setText(from);
			paper.from.setText(to);
			paper.letter.append("\n-------------------------\n");
			paperProcess();
			paper.setVisible(false);
			//paper.card.show(paper.south1,"ok");
		}
		else if(e.getSource()==paper.cancel)
		{
			paper.from.setText("");
			paper.to.setText("");
			paper.letter.setText("");
			paper.setVisible(false);
		}else if(e.getSource()==fileSend){
			if(idx==-1)
			{
				JOptionPane.showMessageDialog(this,"파일보낼 상대방을 선택하세요~");
				return;
			}
			if(id.equals(item))
			{
				JOptionPane.showMessageDialog(this,"자기 자신한테는 파일보내면 안되요~");
				return;
			}
			
			try {
				new sendFileThread().start();
				new FileSendClient();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==btColor){
			ColorChooser();
		}


	}
	
	public void renameProcess()
	{
		String newName=re.newname.getText().trim();
		try {
			out.write("500|"+newName+"\n");
			re.newname.setText("");
			out.flush();
			re.setVisible(false);			
		} catch (IOException e) {
			System.out.println("renameProcess Fail");
			e.printStackTrace();
		}
	}

	
	public void sendProcess() { // 메세지 보내기 메소드
		String msg = send.getText().trim(); //센드텍필에 값을 스트링으로 받음
		if (msg.length() < 1) //값이 없을때
			return;
		try {
			out.write("200|" + color + "|" + msg + "\n"); //버퍼라이터에 200| 메시지 보냄
			out.flush(); //버퍼비우기
		} catch (IOException e) {
			System.out.println("메세지 전송 실패 에러!!");
			e.printStackTrace();
		}
		send.setText(""); //센드텍필비우기
		send.requestFocus(); //포커스를 샌드로 맞춤
	}
	
	public void connectProcess(){ //커넥트프로세스 챗룸 오면 바로 적용
		try {
			s = new Socket("localhost", 5000); //소켓 아이피랑 포트
			document.insertString(document.getLength(), "접속 성공!!\n", attributes); //에어리어에 어펜트해줌		
			setVisible(true); //화면 트루
			send.requestFocus(); //샌드 포커스
			setTitle("[" + id + "님의 채팅창]"); //타이틀바꿈
			//버퍼 객체 생성 받아오고 내보내는 
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new BufferedWriter(
					new OutputStreamWriter(s.getOutputStream()));
			// chat.area.append("100|"+myid+"\n");
			out.write("100|" + id + "\r\n"); //초기 100과 아이디값 넣기
			out.flush();
			new Thread(this).start(); //스레드 스타트
		} catch (Exception e) {
			System.out.println("접속 실패 에러!!");
			e.printStackTrace();
		}
	}
	
	public void closeProcess() // 나가기 메소드
	{ // 전부다 초기 및 닫음. 
		area.setText("");
		send.setText("");
		setVisible(false);
		
		MemberDao1 dao = new MemberDao1();
		dao.disconnectMember(id);
		try {
			out.write("900|\n");
			out.flush();
		} catch (IOException e) {
			System.out.println("종료 실패 에러!!");
			e.printStackTrace();
		}
	}

	public void itemStateChanged(ItemEvent e) { //아이템스테이트채인지드
		idx = list.getSelectedIndex(); //아마 텍스트 저장관련내용일듯
		item = list.getSelectedItem();
	}
	
	public void paperProcess(){
		String to = paper.to.getText().trim();
		String temp = paper.letter.getText().trim();
		paper.setVisible(false);
		String message = temp.replace('\n','\\');
		try{
			out.write("400|" + to +"|" + message + "\n");	
			out.flush();
		}catch(Exception e){System.out.println("client paper : " + e);}
		paper.to.setText("");
		paper.from.setText("");
		paper.letter.setText("");

	}//paperProcess() end

	
	public void run() {//런에이블이라 런이 필요함
		while (true) {//계속 동작함
			try {			
				String msg = in.readLine(); //버퍼리드에 있는 내용을 스트링에 넣음
				System.out.println(msg); //메시지 뿌림
				StringTokenizer st = new StringTokenizer(msg, "|"); //문자열을 토큰처럼 짜름 "|"나올때까지
				int num = Integer.parseInt(st.nextToken()); //메시지의 값을 인트형으로 바꿈
				switch (num) {
				case 100: // 방입장. 처음에는 100인듯
				{
					String nick = st.nextToken(); //그 다음 값이 닉 
					//list.add(nick);
					String nickall = st.nextToken(); //그 다음 값이 닉올, 전체
					StringTokenizer token = new StringTokenizer(nickall, "%"); //토큰라이저 닉올을 %로 짜름
					list.removeAll(); //리스트 삭제
					while (token.hasMoreTokens()) { //토큰의 모어값이 있다면
						String temp = token.nextToken(); //그 값을 템프에 넣음
						list.add(temp); //리스트에 템프값 추가
					}
					document.insertString(document.getLength(), "▶▶ " + nick + " 님이 입장하였습니다\n", attributes); //에어리어에 어펜트해줌
					autoScroll(); //오토스크롤 적용
				}
					break;
					
				case 200: // 전체메세지
				{
					getColor = st.nextToken(); //st토큰을 스트링에 넣음
					getMsg = st.nextToken();
					attributes2.addAttribute(StyleConstants.CharacterConstants.Foreground, Color.decode(getColor));					///////////////////
					document.insertString(document.getLength(), getMsg + "\n", attributes2); //에어리어에 어펜트해줌
					autoScroll(); //오토스크롤 호출
				}
				break;
				
				case 400:
				{
					String from=st.nextToken();
					String message=st.nextToken().replace('\\', '\n');
					paper.from.setText(from);
					paper.to.setText(id);
					paper.letter.setText(message);
					paper.letter.requestFocus();
					paper.setVisible(true);
					paper.card.show(paper.south1,"answer");
				}
				break;

				
				case 500://닉네임변경
				{
						String oldName=st.nextToken();
						String newName=st.nextToken();
					
						int n=list.getItemCount();
						for(int i=0;i<n;i++)
						{
							String temp=list.getItem(i);
							if(oldName.equals(temp))
							{
								list.replaceItem(newName, i);
								break;
							}
						}
						if(oldName.equals(id))
						id=newName;
					  document.insertString(document.getLength(), "♡♡ "+oldName+" 님이 "+newName+" 로 닉네임을 변경하였습니다\n", attributes); //에어리어에 어펜트해줌
					}
					break;

				
				case 800:
					document.insertString(document.getLength(), "♧♣ 서버가 종료되었습니다.\n", attributes); //에어리어에 어펜트해줌
					in.close();
					out.close();
					s.close();
					return;

				case 900://나가기
					{
						String nick=st.nextToken();
						if(nick.equals(id))
						{
							in.close();
							out.close();
							s.close();
							return;
								
						}else
						{
							list.remove(nick);
							document.insertString(document.getLength(), "◁◁ "+nick+" 님이 퇴장하였습니다\n", attributes); //에어리어에 어펜트해줌
							autoScroll();
						}
					}
					break;

				
				}// switch
			} catch (Exception e) {
				System.out.println("client 에러");
				return;
			}

		}// while
	}// run
		
	/*
	public static void main(String[] args) {
		new ChatRoom1();
	}*/
	
	private void ColorChooser() {																					/////////////////////////////////////////////
		Color backgroundColor = JColorChooser.showDialog(colorFrame, "Choose background color", Color.white);		/////////////////////////////////////////////
		if (backgroundColor != null) {																				/////////////////////////////////////////////
			color = "#" + Integer.toHexString(backgroundColor.getRed())												/////////////////////////////////////////////
					+ Integer.toHexString(backgroundColor.getGreen())												/////////////////////////////////////////////
					+ Integer.toHexString(backgroundColor.getBlue());												/////////////////////////////////////////////
		}																											/////////////////////////////////////////////
		attributes.addAttribute(StyleConstants.CharacterConstants.Foreground, Color.decode(color));					/////////////////////////
		System.out.println("color : " + color);																		/////////////////////////////////////////////
	}
}

class sendFileThread extends Thread {
	public void run() {
		try {
			new FileSendServer();
		} catch (Exception e) {
		}
	}
}
