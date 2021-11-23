package cn.gao.entity;

import cn.gao.service.FreshThread;
import cn.gao.service.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author yu
 *  恐龙实体类
 */
public class Dinosaur {
    //主图片
    public BufferedImage image;
    //跑步图片
    private BufferedImage image1 ,image2,image3;
    //坐标
    public int x,y;
    //跳跃状态
    private boolean jumpState=false;
    //跳跃增变量
    private int jumpVaule=0;
    //踏步计时器
    private int setTimer=0;
    //起跳最大高度
    private final int JUMP_HIGHT=100;
    //落地最低坐标
    private final int LOWEST_y=120;
    //刷新时间
    private final int FREASH= FreshThread.FREASH;

    /**
     * 恐龙类的构造方法，用于恐龙实例初始化（创建和赋值）
     */
    public Dinosaur() {
        x=50;//恐龙初始化横轴位置
        y=LOWEST_y;//恐龙初始化纵轴初始化位置
        try {
            //初始化恐龙背景图片
            image1= ImageIO.read(new File("src/main/resources/image/恐龙1.png"));
            image2= ImageIO.read(new File("src/main/resources/image/恐龙2.png"));
            image3= ImageIO.read(new File("src/main/resources/image/恐龙3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void step(){
        //恐龙横轴位置初始化是没有动的。为了效果逼真一些，每隔250毫秒，更换一张图片，因为共有3张图片，所以除以3取余。轮流展示着三张图片
        int tmp = setTimer/250%3;
        switch (tmp){
            case 1:
                image=image1;
                break;
            case 2:
                image=image2;
                break;
            default:
                image=image3;
        }
        //计时器递增
        setTimer+=FREASH;
    }

    public void jump(){
        if(!jumpState){
            //判断是否跳跃，跳跃就播放跳跃音效
            Sound.jump();
        }
        //处于跳跃状态
        jumpState=true;
    }

    public void move(){
        //不断踏步
        step();
        //判断是否跳跃
        if(jumpState){
            //判断纵坐标大于等于最低点（初始值）
            if(y>=LOWEST_y){
                //增变量为负值
                jumpVaule=-4;
            }
            //如果跳过最高点
            if(y<=LOWEST_y-JUMP_HIGHT){
                //增变量为正值
                jumpVaule=4;
            }
            //纵坐标发生变化
            y+=jumpVaule;
            //判断是否再次落地
            if(y>=LOWEST_y){
                //停止跳跃
                jumpState=false;
            }
        }
    }
    /*
    * 恐龙头部部分的对象
    * */
    public Rectangle getFootBounds(){
        return new Rectangle(x+30,y+59,29,18);
    }
    /*
    * 恐龙脚部部分的对象
    * */
    public Rectangle getHeadBounds(){
        return new Rectangle(x+66,y+25,32,22);
    }
}
