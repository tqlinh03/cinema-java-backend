package com.cinema.backend.modal.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public final PaymentMapper paymentMapper;

    public Payment createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentMapper.toPayment(paymentRequest);
        return paymentRepository.save(payment);
    }

    public Integer update(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        payment.setStatus(true);
        return paymentRepository.save(payment).getId();
    }
}
