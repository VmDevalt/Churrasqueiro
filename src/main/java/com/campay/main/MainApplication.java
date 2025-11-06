package com.campay.main;


import com.campay.util.ConnectionFactory;
import com.campay.util.DatabaseInitializer;
import java.awt.EventQueue;

public class MainApplication {

    public static void main(String[] args) {
        
        DatabaseInitializer.initialize();
        ConnectionFactory.startH2Console();
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}