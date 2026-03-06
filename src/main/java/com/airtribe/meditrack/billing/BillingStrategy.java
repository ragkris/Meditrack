package com.airtribe.meditrack.billing;

import com.airtribe.meditrack.entity.BillSummary;

public interface BillingStrategy {

    double calculateTotal(double baseAmount);}