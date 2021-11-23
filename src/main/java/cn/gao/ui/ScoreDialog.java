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
        JLabel title= new JLabel("得分排行榜",JLabel.CENTER);
        title.setFont(new Font("黑体",Font.BOLD,20));
        title.setForeground(Color.red);
        JLabel first = new JLabel("第一名："+scores[2],JLabel.CENTER);
        JLabel second = new JLabel("第二名："+scores[1],JLabel.CENTER);
        JLabel third = new JLabel("第三名："+scores[0],JLabel.CENTER);
        JButton button = new JButton("从新开始");
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

        Container c=getContentPane();//获取主容器
        second.setLayout(new BorderLayout());//使用边界布局
        c.add(scoreP,BorderLayout.CENTER);//成绩面板放中间
        c.add(button,BorderLayout.SOUTH);//按钮放底部

        setTitle("结束游戏");
        int width,height;
        width=height=200;
        int x =frame.getX()+(frame.getWidth()-width)/2;
        int y =frame.getY()+(frame.getHeight()-height)/2;
        setBounds(x,y,width,height);
        setVisible(true);
    }
}
