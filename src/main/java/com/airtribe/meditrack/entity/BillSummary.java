package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.entity.id.EntityID;

public final class BillSummary {

    private final EntityID billId;
    private final String billType;
    private final double baseAmount;
    private final double tax;
    private final double totalAmount;

    public BillSummary(EntityID billId,
                       String billType,
                       double baseAmount,
                       double tax,
                       double totalAmount) {

        this.billId = billId;
        this.billType = billType;
        this.baseAmount = baseAmount;
        this.tax = tax;
        this.totalAmount = totalAmount;
    }

    public EntityID getBillId() {
        return billId;
    }

    public String getBillType() {
        return billType;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "BillSummary{" +
                "billId='" + billId.getValue() + '\'' +
                ", billType='" + billType + '\'' +
                ", baseAmount=" + baseAmount +
                ", tax=" + tax +
                ", totalAmount=" + totalAmount +
                '}';
    }
}