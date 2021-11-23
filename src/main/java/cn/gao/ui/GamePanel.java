package cn.gao.ui;

import cn.gao.entity.Dinosaur;
import cn.gao.entity.Obstacle;
import cn.gao.service.FreshThread;
import cn.gao.service.ScoredRecorder;
import cn.gao.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {
    private BufferedImage image;//��ͼƬ
    private BackgroundImage backgroundImage;//����ͼƬ
    private Dinosaur golden;//����
    private Graphics2D graphics2D;//��ͼƬ��ͼ����
    private int addObstacleTimer=0;//����ϰ���ʱ��
    private boolean finish=false;//��Ϸ������־
    private List<Obstacle> list = new ArrayList();//�ϰ�����
    private final int FREASH= FreshThread.FREASH;//ˢ��ʱ��
    int score=0;//�÷�
    int scoreTimer=0;//������ʱ��
    public GamePanel(){
        image=new BufferedImage(800,300,BufferedImage.TYPE_INT_BGR);
        graphics2D=image.createGraphics();
        backgroundImage=new BackgroundImage();
        golden=new Dinosaur();
        list.add(new Obstacle());
        FreshThread thread= new FreshThread(this);
        thread.start();
    }

    private void paintImage(){
        backgroundImage.roll();
        golden.move();
        graphics2D.drawImage(backgroundImage.image,0,0,this);
        if(addObstacleTimer==1300){//ÿ��1300��������ϰ�
            if(Math.random()*100>40){//60%�ĸ��ʳ����ϰ���
                list.add(new Obstacle());
            }
            addObstacleTimer=0;//���¼�ʱ
        }
        for (int i = 0; i <list.size();i++) {
            Obstacle o = list.get(i);
            if(o.isLive()){//�ж��ϰ��Ƿ���Ч
                o.move();
                graphics2D.drawImage(o.image,o.x,o.y,this);//�����ϰ�
                //�����ײ
                if(o.getBounds().intersects(golden.getFootBounds())||o.getBounds().intersects(golden.getHeadBounds())){
                    Sound.hit();
                    gameOver();
                }
            }else {
                list.remove(i);
                i--;
            }
        }
        graphics2D.drawImage(golden.image,golden.x,golden.y,this);
        if(scoreTimer>=500){
            score+=10;
            scoreTimer=0;
        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font("����",Font.BOLD,24));
        graphics2D.drawString(String.format("%06d",score),700,30);
        addObstacleTimer+=FREASH;
        scoreTimer+=FREASH;
    }

    @Override
    public void paint(Graphics g) {
        paintImage();
        g.drawImage(image,0,0,this);
    }
    public void gameOver(){
        ScoredRecorder.addNewScore(score);
        finish=true;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_SPACE){
            golden.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public boolean isFinish() {
        return finish;
    }
}
