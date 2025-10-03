package com.ppp.billing.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;


public class RefrenceNumberGenerator {

	 private static final DateTimeFormatter YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");

	    private static final AtomicInteger PAT_SERIAL = new AtomicInteger(1);
	    private static final AtomicInteger INV_SERIAL = new AtomicInteger(1);
	    private static final AtomicInteger PAY_SERIAL = new AtomicInteger(1);


	    /* ---------- public helpers ---------- */
	    public static String nextPatientRef() {
	        return format("P", PAT_SERIAL);
	    }
	    public static String nextInvoiceRef() {
	        return format("F", INV_SERIAL);
	    }
	    public static String nextPaymentRef() {
	        return format("PAY",PAY_SERIAL); 
	    }

	    /* ---------- internal ---------- */
	    private static String format(String prefix, AtomicInteger serial) {
	        String date = LocalDate.now().format(YYMMDD);
	        int num   = serial.getAndUpdate(i -> i == 9999 ? 1 : i + 1);
	        return String.format("%s-%s-%04d", prefix, date, num);
	    }
	    
	    public static void resetAll() {
	        PAT_SERIAL.set(1);
	        INV_SERIAL.set(1);
	        PAY_SERIAL.set(1);
	    }
}
