/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ceva.app.mikado.loanserver;

/**
 *
 * @author PC
 */
public interface LoanRepository {

    LoanApplication fetch(String ticketId);

    Ticket store(LoanApplication application);

    Ticket approve(String ticketId);
}
