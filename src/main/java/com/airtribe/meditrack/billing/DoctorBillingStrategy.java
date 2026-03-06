package com.airtribe.meditrack.billing;


import com.airtribe.meditrack.constants.Constants;

public class DoctorBillingStrategy implements BillingStrategy {

    private double taxRate;

    public DoctorBillingStrategy() {
        this.taxRate = Constants.TAX_RATE;
    }

    @Override
    public double calculateTotal(double baseAmount) {

        double tax = baseAmount * taxRate;

        return baseAmount + tax;
    }
}