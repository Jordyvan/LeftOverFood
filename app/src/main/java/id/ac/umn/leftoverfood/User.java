package id.ac.umn.leftoverfood;

import java.io.Serializable;

public class User implements Serializable {
    private static String username;
    private static String pass;
    private static int role;

    public User(String un, String ps, int rl){
        username = un;
        pass = ps;
        role = rl;
    }

    public void setUsername(String un){ username = un; }
    public void setPass(String ps){ pass = ps; }
    public void setRole(int rl){ role = rl; }

    public String getUsername(){ return username; }
    public String getPass(){ return pass; }
    public int getRole(){ return role; }


}
