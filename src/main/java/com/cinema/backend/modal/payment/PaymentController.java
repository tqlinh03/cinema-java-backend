package com.cinema.backend.modal.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PatchMapping("/{id}")
    public ResponseEntity<Integer> updatePayment(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(paymentService.update(id));
    }
}
