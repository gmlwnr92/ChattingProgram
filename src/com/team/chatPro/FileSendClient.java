package com.team.chatPro;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class FileSendClient extends Frame implements ActionListener {
	FileDialog fd;
	Button b1, b2;
	TextField tf;
	String directory = "", file = "";

	/* 아시죠? 프레임을 구성할 컴포넌트들 생성함 */
	public FileSendClient() throws Exception {
		b1 = new Button("File Select");
		b1.addActionListener(this);
		tf = new TextField(25);
		b2 = new Button("File Send");
		b2.addActionListener(this);
		add(b1, "North");
		add(tf, "Center");
		add(b2, "South");
		setBounds(200, 200, 100, 100);
		setVisible(true);
	}

	/* 버튼에 액션 발생시 실행됨 */
	public void actionPerformed(ActionEvent ae) {

		try {

			/* 파일선택 다이얼 로그가 뜨고 */
			if (ae.getActionCommand().equals("File Select")) {
				fd = new FileDialog(this, "", FileDialog.LOAD);
				fd.setVisible(true);
				tf.setText("");

				/* 선택했을 경우 디렉토리와 파일명이 저장됨 */
				directory = fd.getDirectory();
				file = fd.getFile();
				tf.setText(directory + file);

				/* "else" 파일전송버튼 클릭시 실행됨 */
			} else {

				/* localhost 부분은 상대편 ip 주소를 입력하고, 3333 은 서버측 포트와 동일하게 세팅 */
				Socket s = new Socket("localhost", 3333);

				/* 소켓으로부터 OutputStream 얻어서 파일명을 먼저 보냄 */
				/* 서버측에서 파일 객체 생성시 이용할 것임 */
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						s.getOutputStream()));
				System.out.println("파일명 : " + file);
				bw.write(file + "\n");
				bw.flush();

				/* 선택한 파일로 부터 스트림을 읽어들여서 얻어놓은 OutputStream에 연결 */
				DataInputStream dis = new DataInputStream(new FileInputStream(
						new File(tf.getText())));
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());

				/* 바이트단위로 읽어서 스트림으로 쓰기 */
				int b = 0;
				while ((b = dis.read()) != -1) {
					dos.writeByte(b);
					dos.flush();
				}

				/* 자원정리 */
				dis.close();
				dos.close();
				s.close();
				dis = null;
				dos = null;
				s = null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}