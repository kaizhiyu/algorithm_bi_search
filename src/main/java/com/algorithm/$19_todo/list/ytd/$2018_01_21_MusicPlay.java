package com.algorithm.$19_todo.list.ytd;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.audio.*;

public class $2018_01_21_MusicPlay {
//    private AudioStream as; // 单次播放声音用
//    ContinuousAudioDataStream cas;// 循环播放声音
//
//    // <a href="https://www.baidu.com/s?wd=%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1Y4nW6dnhNbmWIbn1bLnHbz0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EPjnkrHD1rH6YnW0kPjfLrHmY" target="_blank" class="baidu-highlight">构造函数</a>
//    public $2018_01_21_MusicPlay(URL url) {
//        try {
//            // 打开一个声音文件流作为输入
//            as = new AudioStream(url.openStream());
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    // 一次播放 开始
//    public void start() {
//        if (as == null) {
//            System.out.println("AudioStream object is not created!");
//            return;
//        } else {
//            AudioPlayer.player.start(as);
//        }
//    }
//
//    // 一次播放 停止
//    public void stop() {
//        if (as == null) {
//            System.out.println("AudioStream object is not created!");
//            return;
//        } else {
//            AudioPlayer.player.stop(as);
//        }
//    }
//
//    // 循环播放 开始
//    public void continuousStart() {
//        // Create AudioData source.
//        AudioData data = null;
//        try {
//            data = as.getData();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        // Create ContinuousAudioDataStream.
//        cas = new ContinuousAudioDataStream(data);
//
//        // Play audio.
//        AudioPlayer.player.start(cas);
//    }
//
//    // 循环播放 停止
//    public void continuousStop() {
//        if (cas != null) {
//            AudioPlayer.player.stop(cas);
//        }
//    }

    public static void main(String[] args) throws InterruptedException, JavaLayerException {
//        $2018_01_21_MusicPlay play = new $2018_01_21_MusicPlay(new URL("/Users/van/Music/网易云音乐/蔡健雅 - 红色高跟鞋.mp3"));
//        play.continuousStart();
//        Thread.sleep(10000L);
//        play.continuousStop();

        ///Users/van/Music
//        try {
////            FileInputStream fileau = new FileInputStream("/Users/van/Music/ssss.mp3");
////            System.out.println(fileau);
////            AudioStream as = new AudioStream(fileau);
////            AudioPlayer.player.start(as);
//            File file1 = new File("/Users/van/Music/ssss.mp3");
//            AudioClip sound1;
//            sound1 = Applet.newAudioClip(file1.toURL());
//            sound1.play();
//
//            Thread.sleep(Integer.MAX_VALUE);
//        } catch (IOException ie) {
//            ie.printStackTrace();
//        }


//        try {
//            FileInputStream fileau = new FileInputStream("/Users/van/Music/linjunjie.wav");
//            AudioStream as = new AudioStream(fileau);
//            AudioPlayer.player.start(as);
//
//            Thread.sleep(Integer.MAX_VALUE);
//        } catch (IOException ie) {
//            ie.printStackTrace();
//        }

//        Player player = new Player("/Users/van/Music/linjunjie.wav");

//        String filename="\\song\\Richard Clayderman-水边的阿狄丽娜.mp3";
        String filename = "/Users/van/Music/网易云音乐/陈绮贞 - 鱼.mp3";
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));

            Player player = new Player(buffer);
            player.play();

            player.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}