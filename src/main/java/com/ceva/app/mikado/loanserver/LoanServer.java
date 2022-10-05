/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.loanserver;

import org.eclipse.jetty.server.Server;

/**
 *
 * @author PC
 */
public class LoanServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new LoanHandler(new FileBasedLoanRepository()));
        server.start();
        server.join();
    }
}
