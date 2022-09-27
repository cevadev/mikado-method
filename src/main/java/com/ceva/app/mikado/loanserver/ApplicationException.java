/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.app.mikado.loanserver;

/**
 *
 * @author PC
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException(String message, Exception e) {
        super(message, e);
    }

    private static final long serialVersionUID = 1L;
}
