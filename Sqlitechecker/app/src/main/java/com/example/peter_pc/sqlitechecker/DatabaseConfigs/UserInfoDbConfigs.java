package com.example.peter_pc.sqlitechecker.DatabaseConfigs;

import android.provider.BaseColumns;

/**
 * Created by Peter-PC on 3/10/2017.
 */

public final class UserInfoDbConfigs {

    // To prevent someone from accidentally instantiating the class,
    // make the constructor private.
    private UserInfoDbConfigs() {
    }

    public static class UserInfoEntry implements BaseColumns {
        //Declare and Intialize constants that shall be used as Table Name and column headers
        public static final String TABLE_NAME="myusers";
        public static final String COLUMNNAME_ID="users_id";
        public static final String COLUMNNAME_FNAME="fisrt_name";
        public static final String COLUMNNAME_LNAME="last_name";

    }

}