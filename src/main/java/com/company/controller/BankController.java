package com.company.controller;

import java.sql.Timestamp;
import java.util.Date;

public class BankController {
    public BankController() {
    }
    public void init() {
        Date date = new Date();
        date.getTime();
        Timestamp ts = new Timestamp(date.getTime());
    }
}
