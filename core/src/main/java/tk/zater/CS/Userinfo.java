/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.zater.CS;

/**
 *
 * @author Administrator
 */
public class Userinfo {

    public Userinfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String username;
    public String password;

    public Userinfo() {
    }

    @Override
    public String toString() {
        return "Userinfo{" + "username=" + username + ", password=" + password + '}';
    }
    
}
