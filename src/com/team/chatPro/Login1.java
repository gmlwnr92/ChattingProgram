package com.team.chatPro;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class Login1 extends JFrame implements ActionListener{
	//로그인1퍼블릭클래스 제이프레임상속과 인터페이스액션리스너를 받는다
	JFrame frame = new JFrame(); //제이프레임 틀 객체 생성
	JPanel global = new JPanel(); //제이프레임안에 글로벌 판넬 객체 생성
	GridLayout gridLayout1 = new GridLayout(); //그리그레이아웃 객체 생성
	JPanel idPanel = new JPanel(); //아이디입력란 판넬
	JPanel passPanel = new JPanel(); //패스워드입력 판넬
	JPanel buttonPanel = new JPanel(); //버튼놓을 판넬
	JLabel id = new JLabel(); //아디 제목넣는 거
	JLabel pass = new JLabel(); //비번 제목넣는 거
	JTextField idTF = new JTextField(); //아디 입력란
	JPasswordField passPF = new JPasswordField(); //비번입력란
	JButton join = new JButton(); //조인버튼
	JButton ok = new JButton(); //확인버튼	
	JButton cancel = new JButton(); //캔슬버튼
	Font f = new Font("맑은고딕체", 0, 12); //폰트
	
	BufferedReader in; //버퍼드리더 수신
	BufferedWriter out; //버퍼드라이터 송신
	Socket s; //소켓. 두 기기대화하기 위한 요소
	
	
	public Login1() { //로긴1 생성자. 메인에서 호출할때 여길들어온다
		try { //에러처리를 위해 쓰는 시도			
			loginIN(); //로그인화면호출
		} catch (Exception e) { //에러발생시 처리할 내용
			e.printStackTrace(); //에러구문 출력해준다.
		}
	}	

	public void loginIN() { //로그인실제화면메소드
		setTitle("로그인 화면"); //프레임제목
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료시 프레임종료
		
		getScreenSize(280, 150);
		setVisible(true); //화면보이게
		
		id.setText("   I          D   : ");	//아디라밸
		id.setFont(f);
			idPanel.setLayout(null); //텍필작아짐
			id.setBounds(new Rectangle(6, 3, 666, 27)); //라밸안보임
			idTF.setBounds(new Rectangle(78, 3, 163, 27)); //텍필안보임
		pass.setText("  비밀번호  : "); //비번라밸
		pass.setFont(f);
			passPanel.setLayout(null); //텍필작아짐
			pass.setBounds(new Rectangle(6, 0, 666, 27)); //라밸안보임
			passPF.setBounds(new Rectangle(78, 0, 163, 27)); //텍필안보임	
		join.setText("회원가입"); //조인버튼라밸
		join.setFont(f);
		ok.setText("로그인"); //확인버튼라밸
		ok.setFont(f);
		cancel.setText("나가기"); // 캔슬버튼라밸
		cancel.setFont(f);
		
		getContentPane().add(global); //컨테이너에 글로발추가
		global.setLayout(gridLayout1); //글로발패널을 그리드로함
		gridLayout1.setRows(3); //그리드레이아웃에 세로 3개생성
		global.add(idPanel); //글로발에 아디패널추가
			idPanel.add(id); //아디패널에 아디라밸추가
			idPanel.add(idTF); //아디패널에 아디텍필추가
		global.add(passPanel); //글로발에 패스판넬추가
			passPanel.add(pass); //패스판넬에 패스라밸추가
			passPanel.add(passPF); //패스판넬에 패스텍펠추가
		global.add(buttonPanel); //글로벌에 버튼판넬추가
			buttonPanel.add(join); //회원가입버튼추가.여기서버튼순서정해짐
			buttonPanel.add(ok); //확인버튼추가
			buttonPanel.add(cancel); //나가기버튼추가
		
		join.addActionListener(this); //회원가입 창 생성
		ok.addActionListener(this); //대기실 이동
		cancel.addActionListener(this);	//종료	
		
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { //버튼액션시
		if(e.getSource() == join){ //e의 소스가 조인이면
			new Join1(); //조인화면띄움호출
		}else if(e.getSource() == ok){ //e의 소스가 오케이이면
			loginProcess(); //로긴테스트			
		}else if(e.getSource() == cancel){ //e의 소스가 캔슬이면
			System.exit(0); //종료
		}		
	}
	
	public void loginProcess() { //로긴프로세스메소드
		
		MemberDao1 dao = new MemberDao1(); //다오 객체 생성
		String id = idTF.getText().trim(); //아디텍필에 값가져오기
		String pass = passPF.getText().trim(); //패스텍필에 값가져오기
		if (id.length()<1 || pass.length() <1) { //길이가 1보다 작으면 입력안한거
			JOptionPane.showMessageDialog(null, "입력하지않음", "메시지", JOptionPane.INFORMATION_MESSAGE);
			//확인창메시지뿌림
		}else{//각 텍필에 값이 있을시
			boolean loginCheck = dao.loginTest(id, pass); //다오로긴테스트클래스에 아디,패스값넣고 트루펄스리턴
			if(loginCheck){
				JOptionPane.showMessageDialog(null, "로그인성공", "메시지", JOptionPane.INFORMATION_MESSAGE);
				//serverConnectProcess();
				new WaitRoom1(this,id);
				dao.connectMember(id);
				dispose();				
			}else if(id.equals("admin")&&pass.equals("admin")){
				// 기백형꺼 관리자모드 불러옴
				new MemberManagerMain();
			}
			else{
				JOptionPane.showMessageDialog(null, "잘못된입력", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
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
