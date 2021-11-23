package cn.gao.entity;



import cn.gao.ui.BackgroundImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Obstacle {
    public int x,y;//��������
    public BufferedImage image;
    private BufferedImage stone;//ʯͷͼƬ
    private BufferedImage cacti;//������ͼƬ
    private int speed;//�ƶ��ٶ�


    /*
    * �������ʯͷ���������ƹ��췽��
    * */
    public Obstacle() {
        try {
            stone= ImageIO.read(new File("src/main/resources/image/ʯͷ.png"));
            cacti= ImageIO.read(new File("src/main/resources/image/������.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r =new Random();//�������
        if(r.nextInt(2)==0){
            image=cacti;//����������ͼƬ����
        }else {
            image=stone;//����ʯͷͼƬ����
        }
        x=800;//��ʼ������
        y=200-image.getHeight();//������
        speed= BackgroundImage.SPEED;//�ƶ��ٶȺͱ����ƶ��ٶ�ͬ��
    }
    /*
    * �ƶ�
    * */
    public void move(){
        x-=speed;//������ݼ�
    }
    /*
    * ʯͷ�����������Ƴ��˱������Ϊ����
    * */
    public boolean isLive(){
        if(x<=-image.getWidth()){
            return false;//��־����
        }
        return true;//��־���
    }
    /*
    * ��ȡ�����ƺ�ʯͷ�ı߽����
    * */
    public Rectangle getBounds(){
        //���ݳ��ֵ�ͼƬ�������ƻ���ͼƬ�Ӷ�������ȡ���Ǹ��ı߽����
        if(image==cacti){
            return new Rectangle(x+7,y,15,image.getHeight());//�����Ʊ߽�ֵ����
        }
        return new Rectangle(x+5,y+4,23,21);//����ʯͷ�߽�ֵ����

    }
}
