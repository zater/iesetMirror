/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.zater.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import tk.zater.constant;

/**
 *
 * @author Administrator
 */
public class VerGenerator {
    static FileWriter fw=null;
    public static void openfile(){
        File verpath=new File(constant.downloadpath+"/update.ver");
        verpath.delete();
        try {
            fw=new FileWriter(verpath);
        } catch (FileNotFoundException ex) {
          System.out.println("error 1008:update.ver generator faile.update.ver生成失败");
        } catch (IOException ex) {
           System.out.println("error 1010:update  generator faile.ver生成失败");
        }
    }
    public static void write(String line){
        try {
            fw.write(line);
            fw.write("\r\n");
        } catch (IOException ex) {
              System.out.println("error 1011:update  generator faile.ver生成失败");
        }
    }
    public static void closefile(){
    
        try {
            fw.close();
        } catch (IOException ex) {
         System.out.println("error 1009:update.ver closed faile.update.ver关闭失败");
        }
    }
}
