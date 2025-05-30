package ptit.example.btlwebbook.service;

import ptit.example.btlwebbook.dto.response.BestSellerResponse;
import ptit.example.btlwebbook.dto.response.OrderCountResponse;
import ptit.example.btlwebbook.dto.response.RevenueResponse;

import java.time.LocalDate;
import java.util.List;

public interface AnalystService {

    Double getTotalRevenue();
    List<RevenueResponse> getTotalRevenueByWeek(LocalDate start, LocalDate end);
    List<RevenueResponse> getRevenueLifeTime();
    List<OrderCountResponse> getOrderCountLifeTime();
    List<BestSellerResponse> getBestSeller();

}
