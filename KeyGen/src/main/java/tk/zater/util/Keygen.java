/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.zater.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import tk.zater.CS.GetUserKey;

/**
 *
 * @author Administrator
 */
public class Keygen implements GetUserKey {

    @Override
    public void run(String savepath) {
        try {
            HttpGet get = new HttpGet("http://www.zolsky.com/eset/nod32id.htm");

// (2) 使用HttpClient发送get请求，获得返回结果HttpResponse
            HttpClient http = new DefaultHttpClient();
            HttpResponse response = http.execute(get);

// (3) 读取返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                Scanner scn = new Scanner(entity.getContent(), "GBK");
                StringBuffer buf=new StringBuffer();
                while (scn.hasNextLine()) {
                   buf.append(scn.nextLine().trim());
                }
                Pattern pat=Pattern.compile("(TRIAL|EAV)-\\d{10}<br>密　码： \\w{10}");
               Matcher mat=pat.matcher(buf.toString());
               FileWriter fos=new FileWriter(new File(savepath));
               while(mat.find()){
               fos.write(mat.group().replace("<br>密　码： ","="));
               fos.write("\r\n");
               }
               fos.close();
            }
        } catch (IOException ex) {
            
        }
    }

}
