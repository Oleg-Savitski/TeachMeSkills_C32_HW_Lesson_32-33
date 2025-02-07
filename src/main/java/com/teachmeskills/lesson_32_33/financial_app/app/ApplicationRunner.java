package com.teachmeskills.lesson_32_33.financial_app.app;

import com.teachmeskills.lesson_32_33.financial_app.controller.impl.BankAccountController;

public class ApplicationRunner {
    public static void main(String[] args) {
        BankAccountController controller = new BankAccountController();
        controller.start();
    }
}