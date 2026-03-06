package com.airtribe.meditrack.billing;

public interface BillingStrategy {

    double calculateTotal(double baseAmount);
}