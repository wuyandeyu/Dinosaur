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
 *  ����ʵ����
 */
public class Dinosaur {
    //��ͼƬ
    public BufferedImage image;
    //�ܲ�ͼƬ
    private BufferedImage image1 ,image2,image3;
    //����
    public int x,y;
    //��Ծ״̬
    private boolean jumpState=false;
    //��Ծ������
    private int jumpVaule=0;
    //̤����ʱ��
    private int setTimer=0;
    //�������߶�
    private final int JUMP_HIGHT=100;
    //����������
    private final int LOWEST_y=120;
    //ˢ��ʱ��
    private final int FREASH= FreshThread.FREASH;

    /**
     * ������Ĺ��췽�������ڿ���ʵ����ʼ���������͸�ֵ��
     */
    public Dinosaur() {
        x=50;//������ʼ������λ��
        y=LOWEST_y;//������ʼ�������ʼ��λ��
        try {
            //��ʼ����������ͼƬ
            image1= ImageIO.read(new File("src/main/resources/image/����1.png"));
            image2= ImageIO.read(new File("src/main/resources/image/����2.png"));
            image3= ImageIO.read(new File("src/main/resources/image/����3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void step(){
        //��������λ�ó�ʼ����û�ж��ġ�Ϊ��Ч������һЩ��ÿ��250���룬����һ��ͼƬ����Ϊ����3��ͼƬ�����Գ���3ȡ�ࡣ����չʾ������ͼƬ
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
        //��ʱ������
        setTimer+=FREASH;
    }

    public void jump(){
        if(!jumpState){
            //�ж��Ƿ���Ծ����Ծ�Ͳ�����Ծ��Ч
            Sound.jump();
        }
        //������Ծ״̬
        jumpState=true;
    }

    public void move(){
        //����̤��
        step();
        //�ж��Ƿ���Ծ
        if(jumpState){
            //�ж���������ڵ�����͵㣨��ʼֵ��
            if(y>=LOWEST_y){
                //������Ϊ��ֵ
                jumpVaule=-4;
            }
            //���������ߵ�
            if(y<=LOWEST_y-JUMP_HIGHT){
                //������Ϊ��ֵ
                jumpVaule=4;
            }
            //�����귢���仯
            y+=jumpVaule;
            //�ж��Ƿ��ٴ����
            if(y>=LOWEST_y){
                //ֹͣ��Ծ
                jumpState=false;
            }
        }
    }
    /*
    * ����ͷ�����ֵĶ���
    * */
    public Rectangle getFootBounds(){
        return new Rectangle(x+30,y+59,29,18);
    }
    /*
    * �����Ų����ֵĶ���
    * */
    public Rectangle getHeadBounds(){
        return new Rectangle(x+66,y+25,32,22);
    }
}
