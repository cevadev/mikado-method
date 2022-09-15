/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.ui;

import com.ceva.app.mikado.db.Database;
import com.ceva.app.mikado.db.FileDB;

public class App {

	private UI ui;
	private static String storePath;

	public void launch(Database database) throws ApplicationException {
		ui = new UI(database);
		ui.showLogin();
	}
}
