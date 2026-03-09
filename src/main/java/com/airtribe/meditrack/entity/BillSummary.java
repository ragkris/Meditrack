package com.airtribe.meditrack.entity;


import com.airtribe.meditrack.entity.id.EntityID;

public record BillSummary(EntityID billId, String billType, double baseAmount, double tax, double totalAmount) {

    @Override
    public String toString() {
        return "-------------------BillSummary-----------------------\n" +
                "billId='" + billId.value() + '\'' +
                ", billType='" + billType + '\'' +
                ", baseAmount=" + baseAmount +
                ", tax=" + tax +
                ", totalAmount=" + totalAmount +
                "\n---------------------------------------------------------";
    }
}