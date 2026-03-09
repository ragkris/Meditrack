package com.airtribe.meditrack.billing;

import com.airtribe.meditrack.constants.Constants;

public class LabBillingStrategy implements BillingStrategy {


    @Override
    public double calculateTotal(double baseAmount) {

        double tax = baseAmount *  Constants.TAX_RATE;

        return baseAmount + tax ;
    }
}