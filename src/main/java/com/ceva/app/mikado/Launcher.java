/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado;

import com.ceva.app.mikado.db.FileDB;
import com.ceva.app.mikado.ui.App;
import com.ceva.app.mikado.ui.ApplicationException;

public class Launcher {

	public static void main(String[] argv) {
			try {
				App app = new App();
				app.launch(new FileDB(argv[0]));
			} catch (ApplicationException e) {
				System.err.println("Could not start application");
				e.printStackTrace();
			}
	}
 
}
