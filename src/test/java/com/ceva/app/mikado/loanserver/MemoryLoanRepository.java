/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.loanserver;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class MemoryLoanRepository implements LoanRepository{
    private final Map<String, LoanApplication> applications = new HashMap<String, LoanApplication>();

    @Override
    public Ticket store(LoanApplication application) {
        int nextId = nextId();
        applications.put(nextId + "", application);
        return new Ticket(nextId);
    }

    private int nextId() {
        return currentId() + 1;
    }

    private int currentId() {
        return applications.size();
    }

    @Override
    public Ticket approve(String ticketId) {
        LoanApplication application = fetch(ticketId);
        application.approve();
        return new Ticket(application.getApplicationNo());
    }

    @Override
    public LoanApplication fetch(String ticketId) {
        return applications.get(ticketId);

    }
}
