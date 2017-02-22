package tk.zater.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Set;

import tk.zater.constant;
import tk.zater.CS.FileName;
import tk.zater.nowlist;

public class fileinfoutil {

    public File saveFile = new File(constant.currentpath + "/file.zater");

    public void readfile() {
        ObjectInputStream in;
        try {

            in = new ObjectInputStream(new FileInputStream(saveFile));
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                FileName temp=(FileName) in.readObject();
                nowlist.nowlist.put(temp.filename,temp);
            }
            System.out.println(nowlist.nowlist);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("error1015:classException");
        }

    }

    public void writefile() {
        ObjectOutputStream out = null;
        int size = nowlist.nowlist.size();
        try {
            out = new ObjectOutputStream(new FileOutputStream(saveFile));
            out.writeInt(size);
            Set<String> list = nowlist.nowlist.keySet();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String nextname=it.next();
                FileName nextFileName=nowlist.nowlist.get(nextname);
               // System.out.println(nextFileName);
                out.writeObject(nextFileName);
            }
             out.close();
        } catch (IOException ex) {
            System.out.println("Error 1014:output error.输出错误");
        }
       

    }
}
