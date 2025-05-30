package ptit.example.btlwebbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.MomoRequest;
import ptit.example.btlwebbook.service.MomoService;

@RequestMapping("/api/momo")
@RestController
@RequiredArgsConstructor
public class MomoController {
    private final MomoService momoService;

    @PostMapping
    public String testPayment(@RequestBody MomoRequest paymentRequest) {
        return momoService.createPaymentRequest(paymentRequest.getAmount(), paymentRequest.getPaymentId());
    }

    @GetMapping("/order-status")
    public String checkPaymentStatus(@RequestBody String paymentId) {
        return momoService.checkPaymentStatus(paymentId);
    }
}
