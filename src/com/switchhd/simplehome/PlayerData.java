package com.switchhd.simplehome;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class PlayerData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2751869541102858774L;
	
	public boolean saveData(String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            GZIPOutputStream gzOut = new GZIPOutputStream(fileOut);
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(gzOut);
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
	
	public boolean loadData(String filePath) {
		try {
			
			FileInputStream fileIn = new FileInputStream(filePath);
			GZIPInputStream gzIn = new GZIPInputStream(fileIn);
			BukkitObjectInputStream in = new BukkitObjectInputStream(gzIn);
            PlayerData data = (PlayerData) in.readObject();
            in.close();
            return true;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
	}
	

}
