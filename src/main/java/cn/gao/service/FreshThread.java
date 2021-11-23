package cn.gao.service;

import cn.gao.ui.GamePanel;
import cn.gao.ui.MainFrame;
import cn.gao.ui.ScoreDialog;

import java.awt.*;


public class FreshThread extends Thread{
    public static final int FREASH = 20;//刷新时间
    GamePanel p ;//游戏面板
    public FreshThread(GamePanel p){
        this.p=p;
    }
    @Override
    public void run(){
        while(!p.isFinish()) {//游戏是否结束，没有结束就一直重画
            p.repaint();
            try{
               Thread.sleep(FREASH);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Container parent = p.getParent();//获取父窗体
        while (!(parent instanceof MainFrame)){
            parent= parent.getParent();//一直到获取到父窗体
        }
        MainFrame frame = (MainFrame) parent;
        new ScoreDialog(frame);
        frame.restart();
    }
}
