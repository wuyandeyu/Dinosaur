package cn.gao.service;

import cn.gao.ui.GamePanel;
import cn.gao.ui.MainFrame;
import cn.gao.ui.ScoreDialog;

import java.awt.*;


public class FreshThread extends Thread{
    public static final int FREASH = 20;//ˢ��ʱ��
    GamePanel p ;//��Ϸ���
    public FreshThread(GamePanel p){
        this.p=p;
    }
    @Override
    public void run(){
        while(!p.isFinish()) {//��Ϸ�Ƿ������û�н�����һֱ�ػ�
            p.repaint();
            try{
               Thread.sleep(FREASH);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Container parent = p.getParent();//��ȡ������
        while (!(parent instanceof MainFrame)){
            parent= parent.getParent();//һֱ����ȡ��������
        }
        MainFrame frame = (MainFrame) parent;
        new ScoreDialog(frame);
        frame.restart();
    }
}
