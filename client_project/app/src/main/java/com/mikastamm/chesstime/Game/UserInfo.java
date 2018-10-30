package com.mikastamm.chesstime.Game;

public class UserInfo {
    public String name;
    public String elo;

    @Override
    public boolean equals(Object obj) {
        if(UserInfo.class.isInstance(obj))
            return ((UserInfo)obj).name.equals(name);
        else
            return super.equals(obj);
    }
}
