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
        setTitle("恶龙咆哮吧");
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
        Container c = getContentPane();//获取主容器对象
        c.removeAll();//删除容器中得所有组件
        GamePanel panel = new GamePanel();//创建新的游戏面板
        c.add(panel);
        addKeyListener(panel);//添加监听事件
        c.validate();//容器重新验证所有组件
    }

}
