package cn.gao.ui;

import cn.gao.service.ScoredRecorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreDialog extends JDialog {
    public ScoreDialog(JFrame frame){
        super(frame,true);
        int[] scores = ScoredRecorder.getScores();
        JPanel scoreP=new JPanel(new GridLayout(4,1));
        scoreP.setBackground(Color.WHITE);
        JLabel title= new JLabel("�÷����а�",JLabel.CENTER);
        title.setFont(new Font("����",Font.BOLD,20));
        title.setForeground(Color.red);
        JLabel first = new JLabel("��һ����"+scores[2],JLabel.CENTER);
        JLabel second = new JLabel("�ڶ�����"+scores[1],JLabel.CENTER);
        JLabel third = new JLabel("��������"+scores[0],JLabel.CENTER);
        JButton button = new JButton("���¿�ʼ");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        scoreP.add(title);
        scoreP.add(first);
        scoreP.add(second);
        scoreP.add(third);

        Container c=getContentPane();//��ȡ������
        second.setLayout(new BorderLayout());//ʹ�ñ߽粼��
        c.add(scoreP,BorderLayout.CENTER);//�ɼ������м�
        c.add(button,BorderLayout.SOUTH);//��ť�ŵײ�

        setTitle("������Ϸ");
        int width,height;
        width=height=200;
        int x =frame.getX()+(frame.getWidth()-width)/2;
        int y =frame.getY()+(frame.getHeight()-height)/2;
        setBounds(x,y,width,height);
        setVisible(true);
    }
}
