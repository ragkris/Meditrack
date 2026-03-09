package com.airtribe.meditrack.billing;


import com.airtribe.meditrack.constants.Constants;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public class DoctorBillingStrategy implements BillingStrategy {

    private final double taxRate;

    public DoctorBillingStrategy() {
        this.taxRate = Constants.TAX_RATE;
    }

    @Override
    public double calculateTotal(double baseAmount) {

        double tax = baseAmount * taxRate;

        return baseAmount + tax;
    }
}