package com.airtribe.meditrack.entity.bill;

import com.airtribe.meditrack.billing.BillingStrategy;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.MedicalEntity;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.interfaces.Payable;

public class LabBill extends MedicalEntity implements Payable {

    private double testFee;
    private BillingStrategy billingStrategy;

    public LabBill(EntityID billId,
                   double testFee,
                   BillingStrategy billingStrategy) {

        super(billId);
        this.testFee = testFee;
        this.billingStrategy = billingStrategy;
    }

    @Override
    public BillSummary generateBill() {

        double total = billingStrategy.calculateTotal(testFee);

        double tax = total - testFee;

        return new BillSummary(
                getId(), "Laboratory Charges",
                testFee,
                tax,
                total
        );
    }
}