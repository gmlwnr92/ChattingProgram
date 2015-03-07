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

public class MemberAddFrame extends JDialog {

	MemberManagerMain parent;
	MemberDao dao;

	JTextField txtId;
	JTextField txtPassword;
	JTextField txtName;
	JTextField txtGender;
	JTextField txtPhone;
	JTextField txtAddr;
	JTextField txtAge;
	JTextField txtJob;
	JTextField txtMail;

	JButton btnAdd;
	JButton btnCancel;

	public MemberAddFrame(MemberManagerMain parent, boolean modal) {

		super(parent, "회원 추가", modal);
		this.parent = parent;

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(300, 30));
		JLabel titleLabel = new JLabel("회원 추가");
		titleLabel.setFont(new Font("굴림체", Font.BOLD, 18));
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();

		JPanel IdPanel = new JPanel();
		IdPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblId = new JLabel("아   이   디 : ");
		lblId.setPreferredSize(new Dimension(80, 26));
		txtId = new JTextField(15);
		txtId.setPreferredSize(new Dimension(0, 26));
		IdPanel.add(lblId);
		IdPanel.add(txtId);
		centerPanel.add(IdPanel);

		JPanel PasswordPanel = new JPanel();
		PasswordPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblPassword = new JLabel("비밀번호 : ");
		lblPassword.setPreferredSize(new Dimension(80, 26));
		txtPassword = new JTextField(15);
		txtPassword.setPreferredSize(new Dimension(0, 26));
		PasswordPanel.add(lblPassword);
		PasswordPanel.add(txtPassword);
		centerPanel.add(PasswordPanel);

		JPanel NamePanel = new JPanel();
		NamePanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblName = new JLabel("이        름 : ");
		lblName.setPreferredSize(new Dimension(80, 26));
		txtName = new JTextField(15);
		txtName.setPreferredSize(new Dimension(0, 26));
		NamePanel.add(lblName);
		NamePanel.add(txtName);
		centerPanel.add(NamePanel);
		add(centerPanel);
		
		JPanel GenderPanel = new JPanel();
		GenderPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblGender = new JLabel("성       별 : ");
		lblGender.setPreferredSize(new Dimension(80, 26));
		txtGender = new JTextField(15);
		txtGender.setPreferredSize(new Dimension(0, 26));
		GenderPanel.add(lblGender);
		GenderPanel.add(txtGender);
		centerPanel.add(GenderPanel);
		add(centerPanel);
		
		JPanel PhonePanel = new JPanel();
		PhonePanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblPhone = new JLabel("전화번호 : ");
		lblPhone.setPreferredSize(new Dimension(80, 26));
		txtPhone = new JTextField(15);
		txtPhone.setPreferredSize(new Dimension(0, 26));
		PhonePanel.add(lblPhone);
		PhonePanel.add(txtPhone);
		centerPanel.add(PhonePanel);
		add(centerPanel);
		
		JPanel JobPanel = new JPanel();
		JobPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblJob = new JLabel("직       업 : ");
		lblJob.setPreferredSize(new Dimension(80, 26));
		txtJob = new JTextField(15);
		txtJob.setPreferredSize(new Dimension(0, 26));
		JobPanel.add(lblJob);
		JobPanel.add(txtJob);
		centerPanel.add(JobPanel);
		add(centerPanel);
		
		JPanel AgePanel = new JPanel();
		AgePanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblAge = new JLabel("나       이 : ");
		lblAge.setPreferredSize(new Dimension(80, 26));
		txtAge = new JTextField(15);
		txtAge.setPreferredSize(new Dimension(0, 26));
		AgePanel.add(lblAge);
		AgePanel.add(txtAge);
		centerPanel.add(AgePanel);
		add(centerPanel);
		
		JPanel MailPanel = new JPanel();
		MailPanel.setPreferredSize(new Dimension(300, 30));
		JLabel lblMail = new JLabel("이  메  일 : ");
		lblMail.setPreferredSize(new Dimension(80, 26));
		txtMail = new JTextField(15);
		txtMail.setPreferredSize(new Dimension(0, 26));
		MailPanel.add(lblMail);
		MailPanel.add(txtMail);
		centerPanel.add(MailPanel);
		add(centerPanel);
		
		JPanel AddrPanel = new JPanel();
		AddrPanel.setPreferredSize(new Dimension(600, 30));
		JLabel lblAddr = new JLabel("주       소 : ");
		lblAddr.setPreferredSize(new Dimension(80, 26));
		txtAddr = new JTextField(43);
		txtAddr.setPreferredSize(new Dimension(0, 26));
		AddrPanel.add(lblAddr);
		AddrPanel.add(txtAddr);
		centerPanel.add(AddrPanel);
		add(centerPanel);
		
		

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(300, 40));
		btnAdd = new JButton("추가하기");
		btnCancel = new JButton("취소하기");

		southPanel.add(btnAdd);
		southPanel.add(btnCancel);
		add(southPanel, BorderLayout.SOUTH);

		ButtonActionListener listener = new ButtonActionListener();
		btnAdd.addActionListener(listener);
		btnCancel.addActionListener(listener);

		setSize(700, 300);
		setDialogLocation();
		setVisible(true);
		setResizable(false);

	}

	public void setDialogLocation() {

		int x = parent.getX() + parent.getWidth() / 2 - this.getWidth() / 2;
		int y = parent.getY() + parent.getHeight() / 2 - this.getHeight() / 2;

		setLocation(x, y);
	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();
			if (btn == btnAdd) {
				String id = txtId.getText().trim();
				String password = txtPassword.getText().trim();
				String name = txtName.getText().trim();
				String gender = txtGender.getText().trim();
				String phone = txtPhone.getText().trim();
				String addr = txtAddr.getText().trim();
				String age = txtAge.getText().trim();
				String job = txtJob.getText().trim();
				String mail = txtMail.getText().trim();
				if (id.equals("")) {
					JOptionPane.showMessageDialog(MemberAddFrame.this,
							"ID가 입력되지 않았습니다.\n" + "ID을 입력해주세요", "ID가 입력되지 않음",
							JOptionPane.INFORMATION_MESSAGE);
					txtName.requestFocus();
					return;

				} else if (password.equals("")) {
					JOptionPane.showMessageDialog(MemberAddFrame.this,
							"비밀번호가 입력되지 않았습니다.\n" + "비밀번호를 입력해주세요",
							"비밀번호가 입력되지 않음", JOptionPane.INFORMATION_MESSAGE);
					txtPassword.requestFocus();
					return;

				} else if (name.equals("")) {
					JOptionPane.showMessageDialog(MemberAddFrame.this,
							"이름이 입력되지 않았습니다.\n" + "이름을 입력해주세요",
							"이름이 입력되지 않음", JOptionPane.INFORMATION_MESSAGE);
					txtName.requestFocus();
					return;
				}
				Vector<Object> member = new Vector<Object>();
				member.add(0, id);
				member.add(1, password);
				member.add(2, name);
				member.add(3, gender);
				member.add(4, phone);
				member.add(5, addr);
				member.add(6, age);
				member.add(7, job);
				member.add(8, mail);

				dao = new MemberDao();
				dao.addMember(member);

				member.add(0, dao.getCurrentId());
				parent.memberModel.addRow(member);

				JOptionPane.showMessageDialog(MemberAddFrame.this,
						"회원 등록이 완료 되었습니다.", "회원 등록완료",
						JOptionPane.INFORMATION_MESSAGE);

				txtId.setText("");
				txtPassword.setText("");
				txtName.setText("");
				txtGender.setText("");
				txtAddr.setText("");
				txtAge.setText("");
				txtJob.setText("");
				txtMail.setText("");
				txtPhone.setText("");
				txtId.requestFocus();

			} else if (btn == btnCancel) {
				MemberAddFrame.this.dispose();
			}

		}
	}

}
