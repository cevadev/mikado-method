/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FileDB implements Database {

	private final File file;
        
        public FileDB(String storageFile){
            this.file = new File(storageFile);
        }

        @Override
	public List<String> load(String key) {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
			Map<String, Serializable> data = (Map<String, Serializable>)stream.readObject();
			return (List<String>) data.get(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
        @Override
	public void store(Map<String, Serializable> data) {
		try {
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream stream = new ObjectOutputStream(out);
			stream.writeObject(data);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
