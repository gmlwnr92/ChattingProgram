package com.team.chatPro;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

public class WaitRoom1 extends JFrame implements ActionListener{
	JTable memberTable; //테이블
	JTable myTable;
	JTable logonTable;
	DefaultTableModel memberModel; //테이블모델
	DefaultTableModel myModel; //테이블모델
	DefaultTableModel logonModel; //테이블모델
	MemberDao1 dao; //멤버다오 
	JButton accept; //버튼
	JButton update; 
	JButton next;
	JButton logout;
	int idx = -1; //커서개념. 테이블의 순서
	Login1 login1; //로그인
	ChatRoom1 chat1; //채팅창
	Next1 next1; //다른사람
	String id;
	
	public WaitRoom1(){ //기본생성자
		try {			
			roomInit(); //대기방 호출
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 로그인에서 아디받아오기
	public WaitRoom1(Login1 login1, String id) { //로그인이랑 연결
		this.login1 = login1;
		this.id = id;
		try {
			roomInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WaitRoom1(Next1 next1, String id){ //다른사람이랑 연결
		this.next1 = next1;
		this.id = id;
		try {			
			roomInit(); //대기방 호출			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WaitRoom1(ChatRoom1 chat1, String id) { //채팅방이랑 연결
		this.chat1 = chat1;
		this.id = id;
		try {			
			roomInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void roomInit() throws Exception { //대기상 화면
		this.setTitle("대기실");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getScreenSize(580, 500);
		setVisible(true);
		setResizable(false);	//생략함
		
		JPanel northPanel = new JPanel();
		JLabel titleLabel = new JLabel("추천회원 목록");
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH); //북쪽에 회원목록판넬생성
				
		Vector<String> columnNames = new Vector<String>(); //백터형으로 칼럼네임지정		
		columnNames.add("이름");
		columnNames.add("성별");
		columnNames.add("주소");
		columnNames.add("나이");
		columnNames.add("직업");
		
			dao = new MemberDao1(); //다오 연결
			Vector<Vector<Object>> memberList1 = dao.getMemberList1(id); 
			//벡터<벡터<오브젝트 멤버리스트1 = 다오의 겟멤버리스트를 넣는다
			memberModel = new DefaultTableModel(memberList1, columnNames){
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			//테이블모델에 벡터<벡터<오브젝트 멤버리스트를 넣고 , 칼럼 제목도 넣는다
			memberTable = new JTable(memberModel); //디폴드 테이블모델이 적용된 테이블생성		
			JScrollPane scrollPane = new JScrollPane( //테이블에 스크롤 생성 가로, 세로
					memberTable,
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
			JPanel centerPanel = new JPanel(new GridLayout(5,3)); //센터에 쓸 판넬에 생성
			centerPanel.add(scrollPane); //스크롤을 센터판넬에 넣음
			add(centerPanel, BorderLayout.CENTER);//센터판넬을 보더 센터에 넣음
			//접속중인 테이블
				Vector<Vector<Object>> logonList1 = dao.getLogMemberList1(id);
				JLabel logonLabel = new JLabel("접속중인사람",  SwingConstants.CENTER);
				centerPanel.add(logonLabel);
				logonModel = new DefaultTableModel(logonList1, columnNames){
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				logonTable = new JTable(logonModel);
				JScrollPane logonScrollPane = new JScrollPane( //테이블에 스크롤 생성 가로, 세로
						logonTable,
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				centerPanel.add(logonScrollPane); 
				
			//내 정보 테이블
					Vector<Vector<Object>> myList1 = dao.getMyList1(id); 
					JLabel myLabel = new JLabel("내정보",  SwingConstants.CENTER);
					centerPanel.add(myLabel); 
					myModel = new DefaultTableModel(myList1, columnNames){
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					myTable = new JTable(myModel);
					JScrollPane myScrollPane = new JScrollPane( //테이블에 스크롤 생성 가로, 세로
							myTable,
							JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
							JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					centerPanel.add(myScrollPane); 
				
		//하단에 버튼 2개 있는 판넬 만듬
		JPanel southPanel = new JPanel();
		accept = new JButton("수락하기");
		update = new JButton("내정보수정");
		next = new JButton("다른 사람찾기");
		logout = new JButton("로그아웃");
		southPanel.add(accept);
		southPanel.add(update);
		southPanel.add(next);
		southPanel.add(logout);
		add(southPanel, BorderLayout.SOUTH);		
		//버튼 액션리스너
		accept.addActionListener(this);
		update.addActionListener(this);
		next.addActionListener(this);
		logout.addActionListener(this);
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				dao.disconnectMember(id);
			}			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {//버튼 퍼폼			
		idx = logonTable.getSelectedRow(); //테이블에 셀렉트된 걸 idx에 넣음
		if (e.getSource() == accept) { //확인 눌렀을시
			/*if(idx == -1){ //셀렉트했다면 1이지만 아니면 -1이니 메시지뿌림
				JOptionPane.showMessageDialog(
						WaitRoom1.this, 
						"같이 대화할 사람이 선택되지 않았습니다.",
						"회원 선택이 않됨",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}*/
			new ChatRoom1(this,id); //그게 아니면 채팅방 입장
			dao.chatMember(id);
			this.dispose(); //이 화면만 중지
		} else if (e.getSource() == next) { //넥스트버튼 누루면						
			new Next1(this,id); //넥스트 창 활성화
			
		}else if (e.getSource() == update) { //업데이트버튼 누루면
			Updates upd = new Updates(this);			
		}
		else if (e.getSource() == logout) { //로그아웃버튼 누루면
			dao.disconnectMember(id);
			this.dispose();
			new Login1(); //대기방입장 서버연결해제해야함
		}
	}
	// 화면 중앙에 위치하게 하는 메소드
		public void getScreenSize(int x, int y) {
			this.setSize(x, y);
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension d = tk.getScreenSize();
			Dimension d2 = this.getSize();
	 
			this.setLocation((int) (d.width - d2.getWidth()) / 2,
					(int) (d.height - d2.getHeight()) / 2);
		}
}

class Updates extends Dialog implements ActionListener{
	JButton ok, cancel;

	public Updates(WaitRoom1 wait) {
		super(wait);
		ok = new JButton("확인");
		cancel = new JButton("취소");		
		getScreenSize(300,200);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		this.setLayout(new GridLayout(2,2));				
		this.add(ok);
		this.add(cancel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("확인")){			
			this.setVisible(false);
		}else{
			this.setVisible(false);
		}
		
	}
	// 화면 중앙에 위치하게 하는 메소드
		public void getScreenSize(int x, int y) {
			this.setSize(x, y);
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension d = tk.getScreenSize();
			Dimension d2 = this.getSize();
	 
			this.setLocation((int) (d.width - d2.getWidth()) / 2,
					(int) (d.height - d2.getHeight()) / 2);
		}
	
}
