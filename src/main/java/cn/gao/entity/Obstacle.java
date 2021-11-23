package cn.gao.entity;



import cn.gao.ui.BackgroundImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Obstacle {
    public int x,y;//横纵坐标
    public BufferedImage image;
    private BufferedImage stone;//石头图片
    private BufferedImage cacti;//仙人掌图片
    private int speed;//移动速度


    /*
    * 随机出现石头或者仙人掌构造方法
    * */
    public Obstacle() {
        try {
            stone= ImageIO.read(new File("src/main/resources/image/石头.png"));
            cacti= ImageIO.read(new File("src/main/resources/image/仙人掌.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r =new Random();//随机对象
        if(r.nextInt(2)==0){
            image=cacti;//创建仙人掌图片对象
        }else {
            image=stone;//创建石头图片对象
        }
        x=800;//初始横坐标
        y=200-image.getHeight();//纵坐标
        speed= BackgroundImage.SPEED;//移动速度和背景移动速度同步
    }
    /*
    * 移动
    * */
    public void move(){
        x-=speed;//横坐标递减
    }
    /*
    * 石头或者仙人掌移出了背景框后即为死亡
    * */
    public boolean isLive(){
        if(x<=-image.getWidth()){
            return false;//标志死亡
        }
        return true;//标志存活
    }
    /*
    * 获取仙人掌和石头的边界对象
    * */
    public Rectangle getBounds(){
        //根据出现的图片是仙人掌还是图片从而决定获取是那个的边界对象
        if(image==cacti){
            return new Rectangle(x+7,y,15,image.getHeight());//仙人掌边界值对象
        }
        return new Rectangle(x+5,y+4,23,21);//返回石头边界值对象

    }
}
