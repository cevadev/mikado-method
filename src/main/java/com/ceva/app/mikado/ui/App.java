/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.ui;

public class App {

	private UI ui;
	private static String storePath;

	public void launch() throws ApplicationException {
		ui = new UI();
		ui.showLogin();
	}

	public static String getStorageFile() {
		return storePath;
	}

	public static void setStorageFile(String storePath) {
		App.storePath = storePath;
	}

}
