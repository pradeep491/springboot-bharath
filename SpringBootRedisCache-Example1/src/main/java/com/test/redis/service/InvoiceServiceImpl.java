package com.test.redis.service;

import com.test.redis.entities.Invoice;
import com.test.redis.exception.InvoiceNotFoundException;
import com.test.redis.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepo;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @Override
    public Invoice saveInvoice(Invoice inv) {

        return invoiceRepo.save(inv);
    }

    @Override
    @CachePut(value = "Invoice", key = "#invId")
    public Invoice updateInvoice(Invoice inv, Long invId) {
        Invoice invoice = invoiceRepo.findById(invId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
        invoice.setInvAmount(inv.getInvAmount());
        invoice.setInvName(inv.getInvName());
        return invoiceRepo.save(invoice);
    }

    @Override
    @CacheEvict(value = "Invoice", key = "#invId")
    // @CacheEvict(value="Invoice", allEntries=true) //in case there are multiple records to delete
    public void deleteInvoice(Long invId) {
        Invoice invoice = invoiceRepo.findById(invId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
        invoiceRepo.delete(invoice);
    }

    @Override
    @Cacheable(value = "Invoice", key = "#invId")
    public Invoice getOneInvoice(Long invId) {
        Invoice invoice = invoiceRepo.findById(invId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
        return invoice;
    }

    @Override
    @Cacheable(value = "Invoice")
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }
}
