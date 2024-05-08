package com.test.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.core.dao.PaymentDAO;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO dao;

    public PaymentServiceImpl(PaymentDAO dao) {
        this.dao = dao;
    }

    public PaymentDAO getDao() {
        return dao;
    }

    public void setDao(PaymentDAO dao) {
        this.dao = dao;
    }

}
