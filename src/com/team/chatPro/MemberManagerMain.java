package com.team.chatPro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MemberManagerMain extends JFrame {

	private static final long serialVersionUID = 1L;
	JTable memberTable;
	DefaultTableModel memberModel;
	MemberDao dao;

	JButton btnAdd;
	JButton btnModify;
	JButton btnDelete;

	public MemberManagerMain() {
		setTitle("회원 관리 프로그램");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// NORTH
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(1200, 30));
		JLabel titleLabel = new JLabel("회원관리");
		titleLabel.setFont(new Font("굴림체", Font.BOLD, 18));
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH);

		// CENTER
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("회원번호");
		columnNames.add("아이디");
		columnNames.add("비밀번호");
		columnNames.add("이름");
		columnNames.add("성별");
		columnNames.add("전화번호");
		columnNames.add("주소");
		columnNames.add("나이");
		columnNames.add("직업");
		columnNames.add("이메일");
		columnNames.add("접속여부");
		

		dao = new MemberDao();
		Vector<Vector<Object>> memberList = dao.getMemberList();

		memberModel = new DefaultTableModel(memberList, columnNames);
		memberTable = new JTable(memberModel);

		memberTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		memberTable.getTableHeader().setResizingAllowed(false);

		memberTable.getTableHeader().setReorderingAllowed(false);
		memberTable.getTableHeader().setPreferredSize(new Dimension(0, 26));
		memberTable.setSelectionBackground(new Color(255, 125, 125, 70));
		memberTable.setSelectionForeground(new Color(0, 0, 255));

		memberTable.getColumnModel().getColumn(0).setPreferredWidth(60); //회원번호
		memberTable.getColumnModel().getColumn(1).setPreferredWidth(90); //아이디
		memberTable.getColumnModel().getColumn(2).setPreferredWidth(90); //비밀번호
		memberTable.getColumnModel().getColumn(3).setPreferredWidth(100); //이름
		memberTable.getColumnModel().getColumn(4).setPreferredWidth(50); //성별
		memberTable.getColumnModel().getColumn(5).setPreferredWidth(130); //전화번호
		memberTable.getColumnModel().getColumn(6).setPreferredWidth(320); //주소
		memberTable.getColumnModel().getColumn(7).setPreferredWidth(50); //나이
		memberTable.getColumnModel().getColumn(8).setPreferredWidth(80); //직업
		memberTable.getColumnModel().getColumn(9).setPreferredWidth(150); //이메일
		memberTable.getColumnModel().getColumn(10).setPreferredWidth(80); //접속여부
		memberTable.setRowHeight(26);

		memberTable.setPreferredScrollableViewportSize(new Dimension(1200, 520));

		DefaultTableCellRenderer dCellRenderer = new DefaultTableCellRenderer();
		dCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcModel = memberTable.getColumnModel();
		tcModel.getColumn(0).setCellRenderer(dCellRenderer);
		tcModel.getColumn(1).setCellRenderer(dCellRenderer);
		tcModel.getColumn(2).setCellRenderer(dCellRenderer);
		tcModel.getColumn(3).setCellRenderer(dCellRenderer);
		tcModel.getColumn(4).setCellRenderer(dCellRenderer);
		tcModel.getColumn(5).setCellRenderer(dCellRenderer);
		tcModel.getColumn(6).setCellRenderer(dCellRenderer);
		tcModel.getColumn(7).setCellRenderer(dCellRenderer);
		tcModel.getColumn(8).setCellRenderer(dCellRenderer);
		tcModel.getColumn(9).setCellRenderer(dCellRenderer);
		tcModel.getColumn(10).setCellRenderer(dCellRenderer);

		JScrollPane scrollPane = new JScrollPane(memberTable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel centerPanel = new JPanel();
		centerPanel.add(scrollPane);
		add(centerPanel, BorderLayout.CENTER);

		// south

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(1200, 40));
		btnAdd = new JButton("회원 추가하기");
		btnModify = new JButton("회원 수정하기");
		btnDelete = new JButton("회원 삭제하기");

		southPanel.add(btnAdd);
		southPanel.add(btnModify);
		southPanel.add(btnDelete);

		add(southPanel, BorderLayout.SOUTH);

		ButtonActionListener listener = new ButtonActionListener();
		btnAdd.addActionListener(listener);
		btnModify.addActionListener(listener);
		btnDelete.addActionListener(listener);

		setSize(1200, 600);
		setVisible(true);
		setResizable(false);

	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn == btnAdd) {
				new MemberAddFrame(MemberManagerMain.this, true);
			} else {
				int row = memberTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(MemberManagerMain.this,
							"수정 또는 삭제 할 회원이 선택되지 않았습니다.", "회원 선택 안됨",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				}
			if (btn == btnModify) {
				Vector<Object> member = new Vector<Object>();
				member.add(memberTable.getValueAt(row, 0));
				member.add(memberTable.getValueAt(row, 1));
				member.add(memberTable.getValueAt(row, 2));
				member.add(memberTable.getValueAt(row, 3));
				member.add(memberTable.getValueAt(row, 4));
				member.add(memberTable.getValueAt(row, 5));
				member.add(memberTable.getValueAt(row, 6));
				member.add(memberTable.getValueAt(row, 7));
				member.add(memberTable.getValueAt(row, 8));
				member.add(memberTable.getValueAt(row, 9));
				System.out.println("vector");

				

				new MemberModifyFrame(MemberManagerMain.this, member, row,
							true);

			} 
			if (btn == btnDelete) {
				int result = JOptionPane.showConfirmDialog(
							MemberManagerMain.this, "선택한 회원을 삭제 하시겠습니까?",
							"회원 삭제 확인", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.CLOSED_OPTION
							|| result == JOptionPane.NO_OPTION) {
						return;
					}
					dao.deleteMember((int) memberTable.getValueAt(row, 0));
					memberModel.removeRow(row);

					JOptionPane.showMessageDialog(MemberManagerMain.this,
							"회원 삭제가 완료 되었습니다.", "회원 삭제 완료",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	public static void main(String[] args) {
		new MemberManagerMain();
	}

}
