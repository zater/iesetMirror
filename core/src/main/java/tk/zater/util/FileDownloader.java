package tk.zater.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import tk.zater.CS.Userinfo;

import tk.zater.constant;

public class FileDownloader {

    public static final int cache = 10 * 1024;

    public static void download(String url, String Filename) {
        try {
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            if (constant.alluserinfolist.size() == 0) {
                System.out.println("Error 1012:not enough.全部账户失效");
                return;
            }
            RequestConfig config = null;
            Userinfo firstuser = constant.alluserinfolist.get(0);
            credsProvider.setCredentials(new AuthScope(constant.domain, 80),
                    new UsernamePasswordCredentials(firstuser.username, firstuser.password));
            HttpHost proxy = null;
            if (constant.openproxy) {
                credsProvider.setCredentials(
                        new AuthScope(constant.ip, constant.port),
                        new UsernamePasswordCredentials(constant.proxyusername, constant.proxypassword));
                proxy = new HttpHost(constant.ip, constant.port);
                config = RequestConfig.custom()
                        .setProxy(proxy)
                        .build();
            }

            CloseableHttpClient httpclient = HttpClients.custom().setUserAgent(constant.emulator).setDefaultCredentialsProvider(credsProvider).build();
            HttpHost target = new HttpHost(constant.domain, 80, "http");
            HttpGet httpget = new HttpGet(url);
            if(constant.openproxy){
             httpget.setConfig(config);
            
            }
            CloseableHttpResponse response = httpclient.execute(target, httpget);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            File file = new File(Filename);
            FileOutputStream fileout = new FileOutputStream(file);

            byte[] buffer = new byte[cache];
            int ch = 0;
            while ((ch = is.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }
            is.close();
            fileout.flush();
            fileout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void download(String url) {
        download(url, path(url));
    }

    public static String path(String url) {
        String[] urlsp = url.split("/");
        StringBuffer buffer = new StringBuffer(constant.downloadpath);
        buffer.append("/");
        buffer.append(urlsp[urlsp.length - 1]);
        return buffer.toString();
    }

}
