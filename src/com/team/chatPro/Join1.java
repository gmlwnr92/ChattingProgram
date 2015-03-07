package com.team.chatPro;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Join1 extends JFrame implements ActionListener{
	//조인퍼블릭클래스 제이프레임상속, 인터페이스 액션리스너	
	GridLayout gridLayout1 = new GridLayout(); //그리드레이아웃객체생성
	//패널은 변수명과 같음
	JPanel global = new JPanel(); 
	JPanel idPanel = new JPanel(); 
	JPanel passwordPanel = new JPanel();
	JPanel namePanel = new JPanel();
	JPanel genderPanel = new JPanel();
	JPanel phonePanel = new JPanel();
	JPanel addrPanel = new JPanel();
	JPanel agePanel = new JPanel();
	JPanel jobPanel = new JPanel();
	JPanel mailPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	//라밸은 변수명과 같음 
	JLabel id = new JLabel();
	JLabel password = new JLabel();
	JLabel name = new JLabel();
	JLabel gender = new JLabel();
	JLabel phone = new JLabel();
	JLabel phoneLabel1 = new JLabel("-");
	JLabel phoneLabel2 = new JLabel("-");
	JLabel addr = new JLabel();
	JLabel age = new JLabel();
	JLabel job = new JLabel();
	JLabel mail = new JLabel();
	JLabel mailLabel = new JLabel("@");
	//텍필은 변수명과 같음
	JTextField idTF = new JTextField();
	JPasswordField passwordPF = new JPasswordField();
	JTextField nameTF = new JTextField();
	JTextField phoneTF1 = new JTextField();
	JTextField phoneTF2 = new JTextField();
	JTextField phoneTF3 = new JTextField();	
	JTextField ageTF = new JTextField();
	JTextField jobTF = new JTextField();
	JTextField mailTF1 = new JTextField();
	JTextField mailTF2 = new JTextField();
	
	Font f = new Font("맑은고딕체", 0, 12); //폰트
	
	ButtonGroup btnGroup = new ButtonGroup(); //버튼그룹생성
	JRadioButton manRB = new JRadioButton("남자"); //남자라디오버튼
	JRadioButton womanRB = new JRadioButton("여자"); //여자라디오버튼

	JButton ok = new JButton(); //가입버튼
	JButton cancel = new JButton(); //취소버튼
	JButton joongbok = new JButton(); //중복확인버튼
	//지역을 콤보박스를 쓰기 위한 스트링배열
	String[] arrayAddr = { "서울", "부산", "인천", "광주", "대전", "대구", "울산",
			"경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주" };
	JComboBox comboAddr = new JComboBox(arrayAddr); //콤보박스 객체생성
		
	MemberDao1 dao = new MemberDao1(); //MemberDAO의 클래스에 dao변수 설정

	public Join1() { //로그인1에서 들어올때				
		try { //트라이구문 예외처리, 이하생략
			joinIn(); //조인인 메소드호출
		} catch (Exception e) { 			
			e.printStackTrace(); 
		}
	}

	private void joinIn() { //조인인메소드
		this.setTitle("회원가입화면"); //프레임제목
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // dispose(); //현재창만 닫는다.	
		this.getContentPane().setBackground(new Color(249, 255, 255));
		this.getContentPane().setLayout(null);
		setSize(300, 400); //창크기
		setVisible(true);
		//글로벌 판넬 세팅및 그리드레이아웃지정
		global.setBorder(BorderFactory.createEtchedBorder());
		global.setOpaque(false);
		global.setBounds(new Rectangle(3, 3, 280, 350));
		global.setLayout(gridLayout1);
		gridLayout1.setRows(10);
		gridLayout1.setColumns(1);
		gridLayout1.setVgap(5);
		//판넬 세팅 아직 미정
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(null);
		mailPanel.setOpaque(false);
		mailPanel.setLayout(null);	
		jobPanel.setOpaque(false);
		jobPanel.setLayout(null);
		agePanel.setOpaque(false);
		agePanel.setLayout(null);
		addrPanel.setOpaque(false);
		addrPanel.setLayout(null);
		phonePanel.setOpaque(false);
		phonePanel.setLayout(null);
		genderPanel.setOpaque(false);
		genderPanel.setLayout(null);
		namePanel.setOpaque(false);
		namePanel.setLayout(null);
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(null);
		idPanel.setOpaque(false);
		idPanel.setLayout(null);

		// 레이블 위치지정
		id.setBounds(new Rectangle(6, 3, 66, 27));
		id.setText("아이디: ");
		id.setFont(f);
		password.setBounds(new Rectangle(6, 0, 66, 27));
		password.setText("비밀번호 : ");
		password.setFont(f);
		name.setBounds(new Rectangle(6, 0, 66, 27));
		name.setText("이름 : ");
		name.setFont(f);
		gender.setBounds(new Rectangle(6, 0, 66, 27));
		gender.setText("성별 : ");
		gender.setFont(f);
		phone.setBounds(new Rectangle(6, 0, 66, 27));
		phone.setText("전화번호 : ");
		phone.setFont(f);
		addr.setBounds(new Rectangle(6, 0, 66, 27));
		addr.setText("지역 : ");
		addr.setFont(f);
		age.setBounds(new Rectangle(6, 0, 66, 27));
		age.setText("나이 : ");
		age.setFont(f);
		job.setBounds(new Rectangle(6, 0, 66, 27));
		job.setText("직업 : ");
		job.setFont(f);
		mail.setBounds(new Rectangle(6, 0, 66, 27));
		mail.setText("이메일 : ");
		mail.setFont(f);
		
		// 텍스트필드 위치지정
		idTF.setBounds(new Rectangle(78, 3, 120, 27));
		passwordPF.setBounds(new Rectangle(78, 3, 120, 27));
		nameTF.setBounds(new Rectangle(78, 3, 120, 27));
		phoneTF1.setBounds(new Rectangle(78, 3, 43, 27));
		phoneTF2.setBounds(new Rectangle(138, 3, 43, 27));
		phoneTF3.setBounds(new Rectangle(198, 3, 43, 27));
		ageTF.setBounds(new Rectangle(78, 3, 163, 27));
		jobTF.setBounds(new Rectangle(78, 3, 163, 27));
		mailTF1.setBounds(new Rectangle(78, 3, 83, 27));
		mailTF2.setBounds(new Rectangle(178, 3, 83, 27));

		// 성별 라디오버튼
		manRB.setBounds(78, 3, 55, 27);
		manRB.setOpaque(false);
		womanRB.setBounds(133, 3, 55, 27);
		womanRB.setOpaque(false);

		// 지역 콤보박스
		comboAddr.setBounds(78, 3, 127, 27);
		comboAddr.setOpaque(false);
		//중복확인
		joongbok.setBorder(BorderFactory.createRaisedBevelBorder());
		joongbok.setText("중복확인");
		joongbok.setFont(f);
		joongbok.setBounds(new Rectangle(200, 2, 67, 26));
		//확인버튼
		ok.setBorder(BorderFactory.createRaisedBevelBorder());
		ok.setText("가입");
		ok.setFont(f);
		ok.setBounds(new Rectangle(60, 1, 67, 26));
		//취소버튼
		cancel.setBorder(BorderFactory.createRaisedBevelBorder());
		cancel.setText("취소");
		cancel.setFont(f);
		cancel.setBounds(new Rectangle(140, 1, 67, 26));

		// 패널 순서지정
		this.getContentPane().add(global, null);
		global.add(idPanel, null);
		idPanel.add(id, null);
		idPanel.add(idTF, null);
		idPanel.add(joongbok, null);
		global.add(passwordPanel, null);
		passwordPanel.add(password, null);
		passwordPanel.add(passwordPF, null);
		global.add(namePanel, null);
		namePanel.add(name, null);
		namePanel.add(nameTF, null);
		global.add(genderPanel, null);
		genderPanel.add(gender, null);
		btnGroup.add(manRB);
		btnGroup.add(womanRB);
		genderPanel.add(manRB);
		genderPanel.add(womanRB);
		global.add(phonePanel, null);
		phonePanel.add(phone, null);
		phonePanel.add(phoneTF1, null);
		phonePanel.add(phoneTF2, null);
		phonePanel.add(phoneTF3, null);
		phonePanel.add(phoneLabel1);
		phonePanel.add(phoneLabel2);
		phoneLabel1.setBounds(126, 2, 120, 27);
		phoneLabel2.setBounds(186, 2, 120, 27);
		global.add(addrPanel, null);
		addrPanel.add(addr, null);
		addrPanel.add(comboAddr);
		global.add(agePanel, null);
		agePanel.add(age, null);
		agePanel.add(ageTF, null);
		global.add(jobPanel, null);
		jobPanel.add(job, null);
		jobPanel.add(jobTF, null);
		global.add(mailPanel, null);
		mailPanel.add(mail, null);
		mailPanel.add(mailTF1, null);
		mailPanel.add(mailTF2, null);
		mailPanel.add(mailLabel);
		mailLabel.setBounds(163, 2, 120, 27);
		global.add(buttonPanel, null);
		buttonPanel.add(ok, null);
		buttonPanel.add(cancel, null);
		//액션리스너
		ok.addActionListener(this);
		cancel.addActionListener(this);
		joongbok.addActionListener(this);

	}
	
	@Override //재구성
	public void actionPerformed(ActionEvent ae) {//버튼리스너퍼폼
		if (ae.getSource() == ok) { //ae.겟소스가 OK이면 잘입력했는지 확인한다.
			if (idTF.getText().trim().equals("")) { //만약 아디필드가 입력이 없다면 이하생략
				JOptionPane.showMessageDialog(null, "아이디가 입력되지 않았습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE); //알람메시지 발생
				idTF.requestFocus(); //포커스는 커서. 커서를 아디로 맞춘다. 이하생략
				return; //다시 돌아감. 변화없음.

			} else if (passwordPF.getText().trim().equals("")) { 
				JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
				passwordPF.requestFocus();
				return;
			} else if (nameTF.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "이름이 입력되지 않았습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
				nameTF.requestFocus();
				return;
			} else if (!manRB.isSelected() && !womanRB.isSelected()) { //이건 체크버튼이라 isselect씀
				JOptionPane.showMessageDialog(null, "성별이 선택되지 않았습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
				manRB.requestFocus();
				return;
			} else if (phoneTF1.getText().trim().equals("") //텍필이 3개라서 3개다 확인함
					|| phoneTF2.getText().trim().equals("")
					|| phoneTF3.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "전화번호가 입력되지 않았습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
				phoneTF1.requestFocus();
				return;
			} else if (ageTF.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "나이이 입력되지 않았습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
				ageTF.requestFocus();
				return;
			} else if (jobTF.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "직업이 입력되지 않았습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
				jobTF.requestFocus();
				return;
			} else if (mailTF1.getText().trim().equals("") //텍필이 2개임 
					|| mailTF2.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "메일이 입력되지 않았습니다.", "메시지",
						JOptionPane.INFORMATION_MESSAGE);
				mailTF1.requestFocus();
				return;
			} else {
				insertMember(); //인서트멤버메소드 호출
				Join1.this.dispose(); //회원가입창만 닫음
				new Login1(); //다시 로그인화면으로
			}

		} else if (ae.getSource() == cancel) { //취소버튼 누루면
			this.dispose(); // 창닫기 (현재창만 닫힘)
			
		} else if (ae.getSource() == joongbok) {
			confirmProcess(); //확인받는 메소드 호출
		}
	}

	public void insertMember() {
		Member1 member = new Member1(); //멤버등록클래스 member 객체생성
		//텍필의 값들을 스트링에 저장함
		String id = idTF.getText();
		String password = passwordPF.getText();
		String name = nameTF.getText();
		String phone1 = phoneTF1.getText();
		String phone2 = phoneTF2.getText();
		String phone3 = phoneTF3.getText();
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		String addr = "";
		addr = (String) comboAddr.getSelectedItem(); //콤보박스의 셀렉트아이템을 스트링으로 캐스팅해서 저장
		String age = ageTF.getText();
		String job = jobTF.getText();		
		String gender = ""; 
		if (manRB.isSelected()) { //체크박스는 버튼셀렉트일때 해당 스트링에 값저장하는방식
			gender = "남자";
		} else if (womanRB.isSelected()) {
			gender = "여자";
		}
		String mail1 = mailTF1.getText();
		String mail2 = mailTF2.getText();
		String mail = mail1 + "@" + mail2; //메일의 @포함하기 위해 3개 스트링을 씀.
		//클래스 멤버1에 set을 한다. 저장되게
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setGender(gender);
		member.setAddr(addr);
		member.setPhone(phone);
		member.setAge(age);
		member.setJob(job);
		member.setMail(mail);
		dao.addMember(member); // DB의 member 테이블에 회원 정보를 추가한다.
	}
	
	public void confirmProcess(){ //아디중복확인메소드
		String id = idTF.getText(); //스트링에 텍필 아디값 저장
		if (dao.confirmId(id) == 1) { //다오의 컨펌 리턴값이 1일때
			JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.", "메시지",
					JOptionPane.INFORMATION_MESSAGE); //메시지 호출 이하생략
			idTF.setText(""); //텍필비우기
		} else if (dao.confirmId(id) == -1 && !(id.length() < 1)) { 
			//다오의 컨펌리턴값이 -1이거나 길이가 1보다 작지 않을 때
			JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.", "메시지",
					JOptionPane.INFORMATION_MESSAGE);
		} else if(id.length() < 1){ //길이가 1보다 작으면 무값이라 판단
			JOptionPane.showMessageDialog(null, "아이디가 입력되지 않았습니다.", "메시지",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/*
	public static void main(String[] args) { 
		new Join1(); 
	}
	*/
}
