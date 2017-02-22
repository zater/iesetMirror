/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.zater.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import tk.zater.CS.GetUserKey;
import tk.zater.CS.Userinfo;
import tk.zater.constant;

/**
 *
 * @author Administrator
 */
public class UserinfoReader {

    public static void readUserinfo()  {
        Properties prop = new Properties();
        if (!"".equals(constant.updateusernameexe)) {
            if (constant.updateusernameexe.toLowerCase().endsWith("(exe|bat)")) {
                try {

                    Process ps = Runtime.getRuntime().exec(constant.updateusernameexe);
                    Scanner scn = new Scanner(ps.getInputStream());
                    while (scn.hasNext()) {
                        System.out.println(scn.nextLine());
                    }
                } catch (IOException ex) {
                    System.out.println("error:1101.updateusernameexe file do not found.更新id软件未找到");
                }
                System.out.println("run id generator.运行id获取工具");
            } else {
                try {
                Object reflectfile = Class.forName(constant.updateusernameexe).newInstance();
                if(reflectfile instanceof GetUserKey){
                   GetUserKey trueObject=(GetUserKey)reflectfile;
                   trueObject.run("id.properties");
                }else{
                System.out.println("this is not a true class implements GetUserKey");
                }
                } catch (ClassNotFoundException ex) {
                  System.out.println("error1201.Unknown reflect class.找不到对应的class文件");
                } catch (InstantiationException ex) {
                 System.out.println("error1202.fail eto reflect无法进行生成操作");
                } catch (IllegalAccessException ex) {
                         System.out.println("error1203.no access.读取权限不足。");
                }

            }
        }
        try {
            prop.load(new FileInputStream(new File("id.properties")));
        } catch (IOException ex) {
            System.out.println("Error:1007. id.properties not exist!id.properties不存在");
        }
        Set<String> Userinfo = prop.stringPropertyNames();
        Iterator<String> it = Userinfo.iterator();
        while (it.hasNext()) {
            String username = it.next();
            constant.alluserinfolist.add(new Userinfo(username, (String) prop.get(username)));
        }

        System.out.println(new Date().toString() + "read userinfo success.读取用户资料成功");
    }
}
