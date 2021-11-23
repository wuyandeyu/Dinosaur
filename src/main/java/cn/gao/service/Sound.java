package cn.gao.service;

public class Sound {

    static final String DIR= "src/main/resources/music/";//�����ļ���
    static final String BACKGROUND="background.wav";//��������
    static final String JUMP="jump.wav";//��Ծ����
    static final String  HIT="hit.wav";//ײ������
    /*
    * ��������
    * */
    private static void play(String file,boolean circulate){
        try {
            MusicPlayer player = new MusicPlayer(file,circulate);//����������
            player.play();//��������ʼ����
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void jump() {
        play(DIR+JUMP,false);//��Ծʱ����һ��
    }
    static public void hit(){
        play(DIR+HIT,false);//ײ��ʱ����һ��
    }
    static public void background(){
        play(DIR+BACKGROUND,true);//ѭ�����ű�������
    }
}
