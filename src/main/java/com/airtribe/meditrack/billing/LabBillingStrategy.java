package com.airtribe.meditrack.billing;

import com.airtribe.meditrack.constants.Constants;

public class LabBillingStrategy implements BillingStrategy {

    private double taxRate;
    private double labServiceCharge;

    public LabBillingStrategy(double labServiceCharge) {
        this.taxRate = Constants.TAX_RATE;
        this.labServiceCharge = labServiceCharge;
    }

    @Override
    public double calculateTotal(double baseAmount) {

        double tax = baseAmount * taxRate;

        return baseAmount + tax + labServiceCharge;
    }
}