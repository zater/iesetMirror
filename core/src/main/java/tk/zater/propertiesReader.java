package tk.zater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class propertiesReader {

    public void propertiesreader() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File("config.properties")));
        } catch (FileNotFoundException e) {

            System.out.println("config file not exist!配置文件不存在");
        } catch (IOException e) {
            System.out.println("error:1001 IO Error");
        }
        String domain = prop.getProperty("domain");
        String updateverurl = prop.getProperty("updateverurl");
        constant.updateinterval = Integer.parseInt(prop.getProperty("updateinterval"));
        constant.domain = "".equals(domain) ? constant.domain : domain;
        constant.updateverurl = "".equals(updateverurl) ? constant.updateverurl : updateverurl;
        constant.downloadpath = prop.getProperty("downloadpath");
        constant.ContainerPath = prop.getProperty("ContainerPath");
        constant.emulator = prop.getProperty("emulator");
        constant.updateusernameexe= prop.getProperty("updateusernameexe");
        constant.openproxy = "0".equals(prop.getProperty("openproxy"));
        if (constant.openproxy) {
            constant.ip = prop.getProperty("ip");
            constant.port = Integer.parseInt(prop.getProperty("port"));
            constant.proxyusername = prop.getProperty("proxyusername");
            constant.proxypassword=prop.getProperty("proxypassword");
        }
        try {
            Class cr = Class.forName("tk.zater.constant");
            Field[] fd = cr.getFields();
            for (int i = 0; i < fd.length; i++) {
                Object value = fd[i].get(new constant());
                System.out.println(fd[i].getName() + "=" + value);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error:1002 class not found");
        }
    }
}
