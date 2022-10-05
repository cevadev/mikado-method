/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.loanserver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author PC
 */
public class LoanHandlerTest {
    LoanHandler loanHandler;
    RequestStub baseRequest;
    ResponseStub response;
    
    @Before
    public void setUp(){
        loanHandler = new LoanHandler(new MemoryLoanRepository());
        baseRequest = new RequestStub();
        response = new ResponseStub();
    }
    
    // verificamos que una llamada incompleta produzca un error
    @Test
    public void incompleteRequest() throws Exception {
        Map<String, String> params = Collections.<String, String> emptyMap();
        ServletRequestStub request = new ServletRequestStub(params);
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        String actual = response.responseAsText();
        Assert.assertEquals("Incorrect parameters provided\n", actual);
    }
    
    // verificamos que un id sea retornado cuando se realice una solicitud de aplicacion
    @Test
    public void completeApplication() throws Exception {
        ServletRequestStub request = new ServletRequestStub(applyParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        Assert.assertEquals("{\"id\":0}\n", response.responseAsText());
    }
    
    // verificamos que una aprovacion retorne un id, si no ocurre errores
    @Test
    public void loanApplicationsCanBeApproved() throws Exception {
        ServletRequestStub request = new ServletRequestStub(
                approveParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        Assert.assertEquals("{\"id\":0}\n", response.responseAsText());
    }
    
    // recuperamos toda la solicitud de prestamo y retornamos la informacion
    @Test
    public void givenAnIdTheStatusOfLoanIsReturned() throws Exception {
        ServletRequestStub request = new ServletRequestStub(fetchParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();

        Assert.assertEquals("{\"applicationNo\":0," + "\"amount\":100,"
                + "\"contact\":\"donald@ducks.burg\",\"approved\":false}\n",
                response.responseAsText());
    }
    
    private HashMap<String, String> approveParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.APPROVE);
        params.put("ticketId", "0");
        return params;
    }

    private HashMap<String, String> applyParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.APPLICATION);
        params.put("amount", "100");
        params.put("contact", "donald@ducks.burg");
        return params;
    }

    private HashMap<String, String> fetchParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.FETCH);
        params.put("ticketId", "0");
        return params;
    }
    
}
