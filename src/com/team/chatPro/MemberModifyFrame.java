package com.team.chatPro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class MemberModifyFrame extends JDialog {
	MemberManagerMain parent;
	MemberDao dao;
	Vector<Object> member;
	int selectedRow;
	
	JTextField txtId;
	JTextField txtPassword;
	JTextField txtName;
	JTextField txtGender;
	JTextField txtPhone;
	JTextField txtAddr;
	JTextField txtAge;
	JTextField txtJob;
	JTextField txtMail;

	JButton btnModify;
	JButton btnCancel;

	public MemberModifyFrame(MemberManagerMain parent, Vector<Object> member,
			int selectedRow, boolean modal) {
		super(parent, "회원 수정", modal);
		this.parent = parent;
		this.member = member;
		this.selectedRow = selectedRow;

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(300, 30));
		JLabel titleLabel = new JLabel("회원 수정");
		titleLabel.setFont(new Font("굴림체", Font.BOLD, 18));
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		
		
		JPanel IdPanel = new JPanel();
		IdPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblId = new JLabel("아   이   디 : ");
		lblId.setPreferredSize(new Dimension(80, 26));
		txtId = new JTextField(15);
		txtId.setText(member.get(1).toString());
		txtId.setPreferredSize(new Dimension(0, 26));
		IdPanel.add(lblId);
		IdPanel.add(txtId);
		centerPanel.add(IdPanel);

		JPanel PasswordPanel = new JPanel();
		PasswordPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblPassword = new JLabel("비밀번호 : ");
		lblPassword.setPreferredSize(new Dimension(80, 26));
		txtPassword = new JTextField(15);
		txtPassword.setText(member.get(2).toString());
		txtPassword.setPreferredSize(new Dimension(0, 26));
		PasswordPanel.add(lblPassword);
		PasswordPanel.add(txtPassword);
		centerPanel.add(PasswordPanel);

		JPanel NamePanel = new JPanel();
		NamePanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblName = new JLabel("이        름 : ");
		lblName.setPreferredSize(new Dimension(80, 26));
		txtName = new JTextField(15);
		txtName.setText(member.get(3).toString());
		txtName.setPreferredSize(new Dimension(0, 26));
		NamePanel.add(lblName);
		NamePanel.add(txtName);
		centerPanel.add(NamePanel);
		

		JPanel GenderPanel = new JPanel();
		GenderPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblGender = new JLabel("성       별 : ");
		lblGender.setPreferredSize(new Dimension(80, 26));
		txtGender = new JTextField(15);
		txtGender.setText(member.get(4).toString());
		txtGender.setPreferredSize(new Dimension(0, 26));
		GenderPanel.add(lblGender);
		GenderPanel.add(txtGender);
		centerPanel.add(GenderPanel);
		

		JPanel PhonePanel = new JPanel();
		PhonePanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblPhone = new JLabel("전화번호 : ");
		lblPhone.setPreferredSize(new Dimension(80, 26));
		txtPhone = new JTextField(15);
		txtPhone.setText(member.get(5).toString());
		txtPhone.setPreferredSize(new Dimension(0, 26));
		PhonePanel.add(lblPhone);
		PhonePanel.add(txtPhone);
		centerPanel.add(PhonePanel);
		

		JPanel JobPanel = new JPanel();
		JobPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblJob = new JLabel("직       업 : ");
		lblJob.setPreferredSize(new Dimension(80, 26));
		txtJob = new JTextField(15);
		txtJob.setText(member.get(8).toString());
		txtJob.setPreferredSize(new Dimension(0, 26));
		JobPanel.add(lblJob);
		JobPanel.add(txtJob);
		centerPanel.add(JobPanel);
		

		JPanel AgePanel = new JPanel();
		AgePanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblAge = new JLabel("나       이 : ");
		lblAge.setPreferredSize(new Dimension(80, 26));
		txtAge = new JTextField(15);
		txtAge.setText(member.get(7).toString());
		txtAge.setPreferredSize(new Dimension(0, 26));
		AgePanel.add(lblAge);
		AgePanel.add(txtAge);
		centerPanel.add(AgePanel);
		

		JPanel MailPanel = new JPanel();
		MailPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblMail = new JLabel("이  메  일 : ");
		lblMail.setPreferredSize(new Dimension(80, 26));
		txtMail = new JTextField(15);
		txtMail.setText(member.get(9).toString());
		txtMail.setPreferredSize(new Dimension(0, 26));
		MailPanel.add(lblMail);
		MailPanel.add(txtMail);
		centerPanel.add(MailPanel);
		

		JPanel AddrPanel = new JPanel();
		AddrPanel.setPreferredSize(new Dimension(600, 30));
		JLabel lblAddr = new JLabel("주       소 : ");
		lblAddr.setPreferredSize(new Dimension(80, 26));
		txtAddr = new JTextField(43);
		txtAddr.setText(member.get(6).toString());
		txtAddr.setPreferredSize(new Dimension(0, 26));
		AddrPanel.add(lblAddr);
		AddrPanel.add(txtAddr);
		centerPanel.add(AddrPanel);
		
		add(centerPanel);
		


		
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(300, 40));
		btnModify = new JButton("수정하기");
		btnCancel = new JButton("취소하기");
		southPanel.add(btnModify);
		southPanel.add(btnCancel);
		add(southPanel, BorderLayout.SOUTH);

		ButtonActionListener listener = new ButtonActionListener();
		btnModify.addActionListener(listener);
		btnCancel.addActionListener(listener);

		setSize(700, 300);
		setDialogLocation();
		setVisible(true);
		setResizable(false);

	}

	public void setDialogLocation() {

		// 메인 프레임의 위치를 이용해 프레임을 띄우 x, y 위치를 구한다.
		int x = parent.getX() + parent.getWidth() / 2 - this.getWidth() / 2;
		int y = parent.getY() + parent.getHeight() / 2 - this.getHeight() / 2;

		// 메인 프레임의 중앙에 친구 추가하기 프레임을 띄운다.
		setLocation(x, y);
	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn == btnModify) {
				
				String id = txtId.getText().trim();
				String password = txtPassword.getText().trim();
				String name = txtName.getText().trim();
				String gender = txtGender.getText().trim();
				String phone = txtPhone.getText().trim();
				String addr = txtAddr.getText().trim();
				String age = txtAge.getText().trim();
				String job = txtJob.getText().trim();
				String mail = txtMail.getText().trim();

				member.set(1, id);
				member.set(2, password);
				member.set(3, name);
				member.set(4, gender);
				member.set(5, phone);
				member.set(6, addr);
				member.set(7, age);
				member.set(8, job);
				member.set(9, mail);

				dao = new MemberDao();
				dao.modifyMember(member);

				for (int i = 0; i < parent.memberModel.getColumnCount(); i++) {

					switch (i) {
					case 1:
						parent.memberModel.setValueAt(id, selectedRow, i);
						break;
					case 2:
						parent.memberModel.setValueAt(password, selectedRow, i);
						break;
					case 3:
						parent.memberModel.setValueAt(name, selectedRow, i);
						break;
					case 4:
						parent.memberModel.setValueAt(gender, selectedRow, i);
						break;
					case 5:
						parent.memberModel.setValueAt(phone, selectedRow, i);
						break;
					case 6:
						parent.memberModel.setValueAt(addr, selectedRow, i);
						break;
					case 7:
						parent.memberModel.setValueAt(age, selectedRow, i);
						break;
					case 8:
						parent.memberModel.setValueAt(job, selectedRow, i);
						break;
					case 9:
						parent.memberModel.setValueAt(mail, selectedRow, i);
						break;
					}
				}

				/*
				 * Vector<Vector<Object>> memberList = dao.getMemberList();
				 * 
				 * Vector<Object> columnNames = new Vector<Object>();
				 * columnNames.add("ID"); columnNames.add("이 름");
				 * columnNames.add("전화번호"); columnNames.add("이메일");
				 * 
				 * parent.memberTable.setModel( new
				 * DefaultTableModel(memberList, columnNames));
				 * System.out.println(parent.memberModel.getValueAt(selectedRow,
				 * 1));
				 */

				JOptionPane.showMessageDialog(MemberModifyFrame.this,
						"회원 수정이 완료 되었습니다.", "회원 수정 완료",
						JOptionPane.INFORMATION_MESSAGE);

			} else if (btn == btnCancel) {
				MemberModifyFrame.this.dispose();
			}

		}

	}

}
