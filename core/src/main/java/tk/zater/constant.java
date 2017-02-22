package tk.zater;

import java.util.ArrayList;
import java.util.HashMap;
import tk.zater.CS.FileName;
import tk.zater.CS.Userinfo;

public class constant {

    public static String domain = "localhost";
    public static String updateverurl = "http://localhost/update.ver";
    public static ArrayList<Userinfo> alluserinfolist = new ArrayList();
    public static String downloadpath = "d:/nod32";
    public static String OS = System.getProperty("os.name");
    public static String currentpath = System.getProperty("user.dir");
    public static int updateinterval = 28800000;
    public static String emulator = "ESS Update (Windows; U; 32bit; PVT F; BPC 8.0.319.1; OS: 6.1.7601 SP 1.0 NT; TDB 24801; CL 1.1.1; x64c; APP essbe; BEO 1; ASP 0.0; FW 0.0; PX 0; PUA 1; CD 1; RA 0; HWF: 0000000A-0000-0002-0011-AAAAAAAAAA; PLOC zh_cn; PCODE 110.40.0; PAR 0; ATH 2; DC 0)";
    public static String ContainerPath="/";
    public static boolean openproxy=false;
    public static String ip="127.0.0.1";
    public static String updateusernameexe="";
    public static int port=8087;
            public static String proxyusername="";
            public static String proxypassword="";
}
