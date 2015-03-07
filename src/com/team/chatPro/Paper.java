package com.team.chatPro;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Paper extends JFrame //implements ActionListener
{
	JPanel global = new JPanel();
	JLabel to = new JLabel();
	JLabel l2 = new JLabel();
	JLabel from = new JLabel();
	JLabel l1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JPanel south1 = new JPanel();
	JButton cancel = new JButton();
	CardLayout card = new CardLayout();
	JButton ok = new JButton();//보내기
	JButton answer = new JButton();//답장하기
	JScrollPane jsP = new JScrollPane();
	JTextArea letter = new JTextArea();

	public Paper() {
		super("쪽지보내기~~~");
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.getContentPane().setBackground(new Color(255, 236, 0));
		this.getContentPane().setLayout(null);
		global.setBorder(BorderFactory.createEtchedBorder());
		global.setOpaque(false);
		global.setBounds(new Rectangle(3, 3, 282, 267));
		global.setLayout(null);
		to.setBackground(Color.orange);
		to.setFont(new java.awt.Font("SansSerif", 0, 12));
		to.setOpaque(true);
  		to.setHorizontalAlignment(SwingConstants.CENTER);
   		to.setHorizontalTextPosition(SwingConstants.CENTER);
		to.setBounds(new Rectangle(192, 9, 78, 30));
		l2.setBounds(new Rectangle(158, 9, 31, 30));
		l2.setFont(new java.awt.Font("SansSerif", 0, 12));
		l2.setText("T o : ");
		from.setBounds(new Rectangle(55, 9, 78, 30));
		from.setBackground(Color.orange);
		from.setFont(new java.awt.Font("SansSerif", 0, 12));
   		from.setForeground(SystemColor.desktop);
		from.setOpaque(true);
   		from.setHorizontalAlignment(SwingConstants.CENTER);
   		from.setHorizontalTextPosition(SwingConstants.CENTER);
		l1.setBounds(new Rectangle(12, 9, 40, 30));
		l1.setFont(new java.awt.Font("SansSerif", 0, 12));
		l1.setText("From : ");
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.setOpaque(false);
		jPanel1.setBounds(new Rectangle(71, 228, 141, 33));
		jPanel1.setLayout(gridLayout1);
		gridLayout1.setColumns(2);
		gridLayout1.setHgap(3);
		cancel.setFont(new java.awt.Font("SansSerif", 0, 12));
		cancel.setBorder(BorderFactory.createRaisedBevelBorder());
		cancel.setText("취 소");
		south1.setOpaque(false);
		south1.setLayout(card);
		ok.setFont(new java.awt.Font("SansSerif", 0, 12));
		ok.setBorder(BorderFactory.createRaisedBevelBorder());
		ok.setSelected(true);
		ok.setText("보내기");
		//ok.addActionListener(this);
		answer.setFont(new java.awt.Font("SansSerif", 0, 12));
   		answer.setBorder(BorderFactory.createRaisedBevelBorder());
		answer.setText("답장하기");
		//answer.addActionListener(this);
		jsP.setBounds(new Rectangle(3, 45, 276, 177));
		this.getContentPane().add(global, null);
		global.add(from, null);
		global.add(to, null);
		global.add(l2, null);
		global.add(l1, null);
		global.add(jPanel1, null);
		jPanel1.add(south1, null);
		jPanel1.add(cancel, null);
		global.add(jsP, null);
		jsP.getViewport().add(letter, null);
		south1.add(ok, "ok");
		south1.add(answer, "answer");
		card.show(south1, "ok");
		setBounds(150,150,295,310);
		//setVisible(true);
	}

	/*public void actionPerformed(ActionEvent e)
	{
		Object ob=e.getSource();
		if(ob==ok)
			card.show(south1, "answer");
		else
			card.show(south1, "ok");	
	}
	
	public static void main(String[] args) {
		Paper paper = new Paper();
	}
	*/
}
