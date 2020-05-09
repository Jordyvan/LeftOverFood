package id.ac.umn.leftoverfood;

import java.io.Serializable;

public class User implements Serializable {
    private String Username;
    private String Pass;
    private int role;

    public User(){

    }

    public String getUsername(){
        return Username;
    }

    public String getPass(){
        return Pass;
    }

    public int getRole(){
        return role;
    }

    public User(String un, String ps, int rl){
        this.Username = un;
        this.Pass = ps;
        this.role = rl;
    }
}
