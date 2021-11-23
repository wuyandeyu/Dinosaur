package cn.gao.service;

public class Sound {

    static final String DIR= "src/main/resources/music/";//音乐文件加
    static final String BACKGROUND="background.wav";//背景音乐
    static final String JUMP="jump.wav";//跳跃音乐
    static final String  HIT="hit.wav";//撞击音乐
    /*
    * 播放声音
    * */
    private static void play(String file,boolean circulate){
        try {
            MusicPlayer player = new MusicPlayer(file,circulate);//创建播放器
            player.play();//播放器开始播放
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void jump() {
        play(DIR+JUMP,false);//跳跃时播放一次
    }
    static public void hit(){
        play(DIR+HIT,false);//撞击时播放一次
    }
    static public void background(){
        play(DIR+BACKGROUND,true);//循环播放背景音乐
    }
}
