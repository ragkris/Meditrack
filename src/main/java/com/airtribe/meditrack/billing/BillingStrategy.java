package com.airtribe.meditrack.billing;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public interface BillingStrategy {

    double calculateTotal(double baseAmount);
}