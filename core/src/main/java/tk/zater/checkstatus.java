package tk.zater;

import java.io.File;
import tk.zater.util.UserinfoReader;

import tk.zater.util.fileinfoutil;

public class checkstatus {

    public void checkstatus() {

               
        new propertiesReader().propertiesreader();
        new fileinfoutil().readfile();
        UserinfoReader.readUserinfo();
  
        File fw = new File(constant.downloadpath);
        if (fw.exists() == false) {
            fw.mkdirs();
        }
    }

    public void beforeexit() {
        new fileinfoutil().writefile();
        System.out.println("请再出输入EXIT以退出。press \"exit\"to exit!");
    }
}
