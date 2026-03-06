package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.billing.BillingStrategy;

public class Bill {

    private String billId;
    private double baseAmount;

    private BillingStrategy billingStrategy;

    public Bill(String billId,
                double baseAmount,
                BillingStrategy billingStrategy) {

        this.billId = billId;
        this.baseAmount = baseAmount;
        this.billingStrategy = billingStrategy;
    }

    public double generateTotal() {

        return billingStrategy.calculateTotal(baseAmount);
    }

    public String getBillId() {
        return billId;
    }

    public double getBaseAmount() {
        return baseAmount;
    }
}