package ptit.example.btlwebbook.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import ptit.example.btlwebbook.dto.request.RevenueQueryRequest;
import ptit.example.btlwebbook.dto.response.BestSellerResponse;
import ptit.example.btlwebbook.dto.response.OrderCountResponse;
import ptit.example.btlwebbook.dto.response.RevenueResponse;
import ptit.example.btlwebbook.repository.OrderRepository;
import ptit.example.btlwebbook.service.AnalystService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalystServiceImpl implements AnalystService {
    private final OrderRepository orderRepository;

    @Override
    public Double getTotalRevenue() {
        return orderRepository.getTotalRevenue();
    }

    @Override
    public List<RevenueResponse> getTotalRevenueByWeek(LocalDate start, LocalDate end) {
        List<RevenueResponse> rawData = orderRepository.getTotalRevenueByWeek(start, end);

        // Tạo map ngày -> revenue
        Map<String, Double> revenueMap = new HashMap<>();
        for (RevenueResponse r : rawData) {
            revenueMap.put(r.getDate(), r.getRevenue());
        }

        // Danh sách kết quả đầy đủ
        List<RevenueResponse> result = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate current = start;
        while (!current.isAfter(end)) {
            String formattedDate = current.format(formatter);
            Double revenue = revenueMap.getOrDefault(formattedDate, 0.0);
            result.add(new RevenueResponse() {
                @Override
                public String getDate() {
                    return formattedDate;
                }

                @Override
                public Double getRevenue() {
                    return revenue;
                }
            });
            current = current.plusDays(1);
        }

        return result;
    }

    @Override
    public List<RevenueResponse> getRevenueLifeTime() {
        return orderRepository.getRevenueLifeTime();
    }

    @Override
    public List<OrderCountResponse> getOrderCountLifeTime() {
        List<OrderCountResponse> result = orderRepository.getOrderCountLifeTime()
                .stream()
                .map(arr -> new OrderCountResponse((String) arr[0], (Long) arr[1]))
                .toList();

        return result;
    }

    @Override
    public List<BestSellerResponse> getBestSeller() {
        return orderRepository.getBestSeller()
                .stream()
                .map(arr -> new BestSellerResponse(
                        ((Number) arr[0]).longValue(),   // hoặc ép về BigInteger nếu cần
                        (String) arr[1],
                        ((BigDecimal) arr[2]).longValue()  // ép kiểu đúng trước khi gọi
                ))
                .toList();
    }
}
