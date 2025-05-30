package ptit.example.btlwebbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ptit.example.btlwebbook.dto.response.*;
import ptit.example.btlwebbook.service.AnalystService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalystController {

    private final AnalystService analystService;

    @GetMapping("/total-revenue")
    public ResponseData<Double> getTotalRevenue() {
        return new ResponseData<>(HttpStatus.OK.value(), "success", analystService.getTotalRevenue());

    }

    @GetMapping("/revenue/week")
    public ResponseData<List<RevenueResponse>> getTotalRevenueByWeek(
            @RequestParam("start") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate start,
            @RequestParam("end") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate end) {
        return new ResponseData<>(HttpStatus.OK.value(),"success", analystService.getTotalRevenueByWeek(start,end));
    }

    @GetMapping("/revenue/lifetime")
    public ResponseData<List<RevenueResponse>> getRevenueLifeTime() {
        return new ResponseData<>(HttpStatus.OK.value(),"success", analystService.getRevenueLifeTime());
    }

    @GetMapping("/order-count/lifetime")
    public ResponseData<List<OrderCountResponse>> getOrderCountLifeTime() {
        return new ResponseData<>(HttpStatus.OK.value(),"success", analystService.getOrderCountLifeTime());
    }

    @GetMapping("/best-sellers")
    public ResponseData<List<BestSellerResponse>> getBestSeller() {
        return new ResponseData<>(HttpStatus.OK.value(),"success", analystService.getBestSeller());
    }
}
