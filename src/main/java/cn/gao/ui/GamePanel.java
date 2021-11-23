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
    private BufferedImage image;//主图片
    private BackgroundImage backgroundImage;//背景图片
    private Dinosaur golden;//恐龙
    private Graphics2D graphics2D;//主图片绘图对象
    private int addObstacleTimer=0;//添加障碍计时器
    private boolean finish=false;//游戏结束标志
    private List<Obstacle> list = new ArrayList();//障碍集合
    private final int FREASH= FreshThread.FREASH;//刷新时间
    int score=0;//得分
    int scoreTimer=0;//分数计时器
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
        if(addObstacleTimer==1300){//每过1300毫秒出现障碍
            if(Math.random()*100>40){//60%的概率出现障碍物
                list.add(new Obstacle());
            }
            addObstacleTimer=0;//重新计时
        }
        for (int i = 0; i <list.size();i++) {
            Obstacle o = list.get(i);
            if(o.isLive()){//判断障碍是否有效
                o.move();
                graphics2D.drawImage(o.image,o.x,o.y,this);//绘制障碍
                //检测碰撞
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
        graphics2D.setFont(new Font("黑体",Font.BOLD,24));
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
