package cn.gao.ui;

import cn.gao.service.ScoredRecorder;
import cn.gao.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public MainFrame(){
        restart();
        setBounds(340,150,806,260);
        setTitle("����������");
        Sound.background();
        ScoredRecorder.init();
        addListener();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ScoredRecorder.saveScope();
            }
        });
    }

    public void restart() {
        Container c = getContentPane();//��ȡ����������
        c.removeAll();//ɾ�������е��������
        GamePanel panel = new GamePanel();//�����µ���Ϸ���
        c.add(panel);
        addKeyListener(panel);//��Ӽ����¼�
        c.validate();//����������֤�������
    }

}
