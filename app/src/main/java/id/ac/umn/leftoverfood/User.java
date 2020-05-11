package id.ac.umn.leftoverfood;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String pass;
    private int role;

    public static String currentUsername;

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
