/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC
 */
public interface Database {
    public List<String> load(String key);
    public void store(Map<String, Serializable> data);
}
