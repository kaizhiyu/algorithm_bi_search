package com.algorithm.$19_todo.list.ytd;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class ssss {

    public static void main(String[] args)  {
        String filename = "/Users/van/Music/网易云音乐/陈绮贞 - 鱼.mp3";
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));

            Player player = new Player(buffer);
            player.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}