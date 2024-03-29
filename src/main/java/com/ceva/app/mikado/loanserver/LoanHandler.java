/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.loanserver;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author PC
 */
public class LoanHandler extends AbstractHandler{
    public static final String APPLICATION = "apply";
    public static final String FETCH = "fetch";
    public static final String TICKET_ID = "ticketId";
    public static final String APPROVE = "approve";
    
    private final LoanRepository repo;
    
    public LoanHandler(LoanRepository repo){
        this.repo = repo;
    }
    
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        PrintWriter writer = response.getWriter();
        try {
            if (isApplication(request)) {
                LoanApplication application = new LoanApplication();
                application.setAmount(amountFrom(request));
                application.setContact(contactFrom(request));

                Ticket ticket = repo.store(application);
                writer.println(new Gson().toJson(ticket));
            } else if (isStatusRequest(request) && idSpecified(request)) {
                writer.println(fetchLoanInfo(request.getParameter(TICKET_ID)));
            } else if (isApproval(request) && idSpecified(request)) {
                writer.println(approveLoan(request.getParameter(TICKET_ID)));
            } else {
                writer.println("Incorrect parameters provided");
            }
        } catch (ApplicationException e) {
            writer.println("Uh oh! Problem occured: " + e.getMessage());
        }
    }

    private String contactFrom(HttpServletRequest request) {
        return request.getParameter("contact");
    }

    private long amountFrom(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("amount"));
    }

    private String approveLoan(String parameter) {
        return new Gson().toJson(repo.approve(parameter));
    }

    private boolean isApproval(HttpServletRequest request) {
        return APPROVE.equals(request.getParameter("action"));
    }

    private boolean idSpecified(HttpServletRequest request) {
        return request.getParameter(TICKET_ID) != null && validId(request) >= 0;
    }

    private long validId(HttpServletRequest request) {
        String ticketId = request.getParameter(TICKET_ID);
        try {
            return Long.parseLong(ticketId);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    private boolean isStatusRequest(HttpServletRequest request) {
        return FETCH.equals(request.getParameter("action"));
    }

    private boolean isApplication(HttpServletRequest request) {
        return APPLICATION.equals(request.getParameter("action"));
    }

    private String fetchLoanInfo(String ticketId) {
        LoanApplication formerApplication = repo.fetch(ticketId);
        return new Gson().toJson(formerApplication);
    }

    public static long getNextId() {
        File file = new File(FileBasedLoanRepository.REPOSITORY_ROOT);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(FileBasedLoanRepository.FILE_EXTENSION);
            }
        });

        return files == null ? 0 : files.length + 1;
    }
}
