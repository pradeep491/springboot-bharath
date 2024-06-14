package com.test.redis.service;

import com.test.redis.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    public Invoice saveInvoice(Invoice inv);
    public Invoice updateInvoice(Invoice inv, Long invId);
    public void deleteInvoice(Long invId);
    public Invoice getOneInvoice(Long invId);
    public List<Invoice> getAllInvoices();
}
