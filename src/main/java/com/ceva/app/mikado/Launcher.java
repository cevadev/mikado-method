/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado;

import com.ceva.app.mikado.ui.App;
import com.ceva.app.mikado.ui.ApplicationException;

public class Launcher {

	public static void main(String[] argv) {
			try {
				App.setStorageFile(System.getProperty("user.dir") + "/db.txt");
				App app = new App();
				app.launch();
			} catch (ApplicationException e) {
				System.err.println("Could not start application");
				e.printStackTrace();
			}
	}
 
}
