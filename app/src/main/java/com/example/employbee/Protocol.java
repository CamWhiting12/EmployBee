package com.example.employbee;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"prot_name", "prot_text"}, unique = true)})

public class Protocol {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "prot_name")
    public String prot;

    @ColumnInfo(name = "prot_text")
    public String protInfo;

    public Protocol(){}
    public static int count = 0;

    public Protocol(String protName, String protText) {
        uid = count++;
        prot = protName;
        protInfo = protText;
    }

    public boolean equals(Protocol p) {
        if (prot.equals(p.prot)) {
            return true;
        }

        return false;
    }
    public String toString(){
        return prot;
    }
}