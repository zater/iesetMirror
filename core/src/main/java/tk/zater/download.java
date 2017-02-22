package tk.zater;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tk.zater.CS.FileName;

import tk.zater.util.FileDownloader;
import tk.zater.util.UserinfoReader;
import tk.zater.util.VerGenerator;
import tk.zater.util.fileinfoutil;

public class download implements Runnable {

    @Override
    public void run() {
        Scanner scn = null;
        //System.out.println(new Date().toString() + "start update");
        FileDownloader fw = new FileDownloader();
        File verzater = new File("ver.zater");
        verzater.delete();
        verzater = new File("update.ver");
        verzater.delete();
        fw.download(constant.updateverurl, "ver.zater");
        express();
        try {
            scn = new Scanner(new File("update.ver"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(download.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(new Date().toString() + "unpack update.ver success");
        VerGenerator.openfile();
        while (true) {
            String p = scn.nextLine();
            if (p.startsWith("[")) {
                if ("[HOSTS]".equals(p) || ("[Expire]").equals(p) || ("[SETUP]").equals(p) || ("[PCUVER]".equals(p))) {
                } else {
                    VerGenerator.write(p);
                    break;
                }
            }
        }

        FileName tempery = new FileName();
        Field[] allFields = null;
        try {
            allFields = Class.forName("tk.zater.CS.FileName").getFields();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(download.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scn.hasNext()) {
            String nextLine = scn.nextLine();
            if (nextLine.startsWith("[")) {
                VerGenerator.write(nextLine);
                update(tempery);
                tempery = new FileName();
                continue;
            }
            if (nextLine.startsWith("version") || nextLine.startsWith("versionid") || nextLine.startsWith("build") || nextLine.startsWith("file") || nextLine.startsWith("size")) {
                String splitinfo[] = nextLine.split("=");
                if (nextLine.startsWith("file")) {
                    tempery.Url = splitinfo[1];
                    String fileinfo[] = tempery.Url.split("/");
                    tempery.filename = fileinfo[fileinfo.length - 1];
                    VerGenerator.write("file="+constant.ContainerPath + tempery.filename);

                } else {
                    VerGenerator.write(nextLine);
                    if(splitinfo[0].equals("size")){
                       tempery.size=Integer.parseInt(splitinfo[1]);
                       continue;
                    }
                    for (int i = 0; i < allFields.length; i++) {
                        if (allFields[i].getName().equals(splitinfo[0])) {
                            try {
                                    allFields[i].set(tempery, splitinfo[1]);
                            } catch (IllegalArgumentException ex) {
                                System.out.println(allFields[i].getGenericType());
                                System.out.println("error 1011:reflect fail.");
                            } catch (IllegalAccessException ex) {
                                System.out.println("error 1012:reflect fail.");
                            }
                        }
                    }
                    

                }
            } else {
                VerGenerator.write(nextLine);
            }
        }
        update(tempery);
        System.out.println("update success");
        VerGenerator.closefile();
        new fileinfoutil().writefile();
    }

    private boolean checkthefile(FileName tempery) {
        File downloadfile = new File(FileDownloader.path(tempery.Url));
        if (tempery.size == downloadfile.length()) {
            return true;
        } else {
            System.out.println(constant.alluserinfolist.remove(0) + "error.失效");
            System.out.printf("剩余%d个未测试的帐号",constant.alluserinfolist.size());
            if(constant.alluserinfolist.size()==0){
                System.out.println("尝试使用帐号更新软件更新序列号");
                UserinfoReader.readUserinfo();
            }
            return false;
        }
    }

    public void express() {
        try {
            Process process = Runtime.getRuntime().exec("rar E ver.zater");
        } catch (IOException e) {
            System.out.println("error 1006.install rar.安装rar。");
            System.out.println("for ubuntu:sudo apt-get install rar");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(download.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(FileName tempery) {
        if(tempery.filename.matches("(eav|ess)_nt(32|64)_[^c]{1}[^h]{1}[^st]{1}.nup"))
        return;
        
        boolean flag = false;
        if (nowlist.nowlist.containsKey(tempery.filename)) {
            flag = true;
            FileName tp = nowlist.nowlist.get(tempery.filename);
            if (tp.checkthesame(tempery) == true) {
                tempery = new FileName();
                return;
            }
        }
        while (true) {
            System.out.println("start download " + tempery);
            FileDownloader.download(tempery.Url);
            if (checkthefile(tempery) == true) {
                break;
            }

        }
        if (flag == true) {
            nowlist.nowlist.remove(tempery.filename);
        } 
            nowlist.nowlist.put(tempery.filename, tempery);
        

    }
}
