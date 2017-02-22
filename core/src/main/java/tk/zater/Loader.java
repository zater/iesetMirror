package tk.zater;

import java.util.Scanner;
import tk.zater.util.UserinfoReader;
import tk.zater.util.fileinfoutil;
//

public class Loader {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        boolean showeura = true;
        if (args.length >= 1) {
            if (args[0].contains("y")) {
               showeura = false;
            }

        }
        if (showeura) {
            System.out.println("1.制作本软件纯属本人爱好，请勿将该安装程序用于商业目的。\n"
                    + "2.本人制作该软件仅用于交流目的，请在获取官方许可后安装本程序，否则请于24小时内删除该应用程序。\n"
                    + "3.本人确认，如果您安装、使用该程序，不会因为软件的原因导致该程序以及您操作系统的不稳定与损毁，与此相关问题请勿与本人联系。\n"
                    + "4.本人不承担由于安装、使用该程序对您计算机所产生的一切不良后果。\n"
                    + "5.本安装程序由本人提取并参照官方资源加工制作，源程序的版权归ESET公司所有，请勿就有关该程序的破解、注册信息与本人联系，就此问题本人不做任何回应。\n"
                    + "6.软件仅限在中国大陆地区测试，严禁在其他任何地区的任何网站、软件等复制、拷贝。\n"
                    + "7.任何未经作者同意对本程序进行的修改操作，包含但不限于破解、反编、二次开发等，需承担法律责任。\n"
            );
            System.out.println("According to the PRC's raw, my aimimg to design it is only for research.Please remove it after 24 hours.\nYou can only test this software in Mainland China.\n"
                    + "You cannot sold the software.In both the Chinese version shall prevail ");
            System.out.println("author:Zater(zater@vip.qq.com)");
            System.out.println("请输入Y确认.please press Y to confirm!");
            char p = scn.next().charAt(0);
            if (p != 'Y') {
                return;
            }
        }
        new checkstatus().checkstatus();
        System.out.println("初始化成功|System initialize success.");
        Thread thread = new Thread(new controlThread());
        thread.start();
        System.out.println("--------------------------------------");
        System.out.println("ESET SERVER                  provide by zater");
        System.out.println("--------------------------------------");
        System.out.println("1)更换ESET用户名密码Change ESET username and password");
        System.out.println("2)退出程序EXIT");
        System.out.println("3)重置file.zater reset file.zater");

        boolean flag = false;
        while (scn.hasNext()) {
            if (flag == true) {
                break;
            }
            int a = scn.nextInt();
            switch (a) {
                case 1:
                    UserinfoReader.readUserinfo();
                    break;

                case 2:
                    flag = true;
                    new checkstatus().beforeexit();
                    thread.stop();
                    break;
                case 3:
                    nowlist.nowlist.clear();
                    new fileinfoutil().writefile();
                    System.out.println("reset success.重置成功");
                    break;
                default:
                    System.out.println("input error.输入错误");
                    break;
            }
        }
    }
}
