package com.airtribe.meditrack.interfaces;


import com.airtribe.meditrack.entity.BillSummary;

public interface Payable {

    BillSummary generateBill();
}