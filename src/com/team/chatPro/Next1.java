package com.team.chatPro;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Next1 extends JFrame implements ActionListener{//넥스트1클래스 테이블 리플레쉬위한
	//버튼 2개
	JButton close; 
	JButton cancel;	
	WaitRoom1 wait; //대기방 
	String id;
	public Next1() {} //기본생성자 없어도 됨

	public Next1(WaitRoom1 wait, String id) { //생성자 대기방에서 버튼눌럿을시
		this.wait = wait;		//의미없음 그냥 같은거
		this.id = id;
		try {//화면 설정 생략함
			setTitle(id);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocation(500, 600);
			setSize(200, 100);
			setVisible(true);		
			JPanel centerPanel = new JPanel();
			close = new JButton("예");
			cancel = new JButton("아니오");
			centerPanel.add(close);
			centerPanel.add(cancel);
			add(centerPanel, BorderLayout.CENTER);
			//버튼 리스너			
			close.addActionListener(this);
			cancel.addActionListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 버튼을 클릭하면 대기방 다시 가거나 화면 닫음
		if (e.getSource() == close) {
			wait.dispose();
			new WaitRoom1(this,id);
			this.dispose();
		} else if (e.getSource() == cancel) {
			this.setVisible(false);			
		}
	}
}