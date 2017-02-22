/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.zater;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class controlThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            Thread thread2 = new Thread(new download());
            thread2.start();
            System.out.println("休息开始");
            try {
                Thread.sleep(constant.updateinterval);
            } catch (InterruptedException ex) {
                Logger.getLogger(controlThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(
                    "休息结束");
        }
    }
   
}
