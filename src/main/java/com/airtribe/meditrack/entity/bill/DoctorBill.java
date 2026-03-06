package com.airtribe.meditrack.entity.bill;


import com.airtribe.meditrack.billing.BillingStrategy;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.MedicalEntity;
import com.airtribe.meditrack.entity.id.EntityID;
import com.airtribe.meditrack.interfaces.Payable;


public class DoctorBill extends MedicalEntity implements Payable {

    private double consultationFee;

    private BillingStrategy billingStrategy;

    public DoctorBill(EntityID billId,
                      double consultationFee,
                      BillingStrategy billingStrategy) {

        super(billId);
        this.consultationFee = consultationFee;
        this.billingStrategy = billingStrategy;
    }

    @Override
    public BillSummary generateBill() {

        double total = billingStrategy.calculateTotal(consultationFee);

        double tax = total - consultationFee;

        return new BillSummary(
                getId(), "Doctor Consultation",
                consultationFee,
                tax,
                total
        );
    }
}