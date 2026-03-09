package com.airtribe.meditrack.interfaces;


import com.airtribe.meditrack.entity.BillSummary;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public interface Payable {

    BillSummary generateBill();
}