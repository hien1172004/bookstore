//package ptit.example.btlwebbook.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import ptit.example.btlwebbook.config.GhnProperties;
//import ptit.example.btlwebbook.dto.request.DistrictDTO;
//import ptit.example.btlwebbook.dto.request.ProvinceDTO;
//import ptit.example.btlwebbook.dto.request.WardDTO;
//import ptit.example.btlwebbook.dto.response.ResponseData;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//
//@RestController
//@RequestMapping("/api/ghn")
//@RequiredArgsConstructor
//public class GhnController {
//
//    @Value("${ghn.token}")
//    private String ghnToken;
//
//    private final RestTemplate restTemplate;
////    private final GhnProperties ghnProperties;
//
//    private final String GHN_API_BASE = "https://online-gateway.ghn.vn/shiip/public-api/master-data";
//
//    // Lấy danh sách tỉnh
//    @GetMapping("/province")
//    public ResponseEntity<List<ProvinceDTO>> getProvinces() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Token", ghnToken);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        String url = GHN_API_BASE + "/province";
//
//        ResponseEntity<ResponseData<List<ProvinceDTO>>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                entity,
//                new ParameterizedTypeReference<ResponseData<List<ProvinceDTO>>>() {}
//        );
//
//        return ResponseEntity.ok(Objects.requireNonNull(response.getBody()).getData());
//    }
//
//    // Lấy danh sách huyện theo province_id
//    @GetMapping("/district")
//    public ResponseEntity<List<DistrictDTO>> getDistricts(@RequestParam("province_id") int provinceId) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Token", ghnToken);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        String url = GHN_API_BASE + "/district?province_id=" + provinceId;
//
//        ResponseEntity<ResponseData<List<DistrictDTO>>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                entity,
//                new ParameterizedTypeReference<ResponseData<List<DistrictDTO>>>() {}
//        );
//
//        return ResponseEntity.ok(Objects.requireNonNull(response.getBody()).getData());
//    }
//
//    // Lấy danh sách xã/phường theo district_id
//    @GetMapping("/ward")
//    public ResponseEntity<List<WardDTO>> getWards(@RequestParam("district_id") int districtId) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Token", ghnToken);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        String url = GHN_API_BASE + "/ward?district_id=" + districtId;
//
//        ResponseEntity<ResponseData<List<WardDTO>>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                entity,
//                new ParameterizedTypeReference<ResponseData<List<WardDTO>>>() {}
//        );
//
//        return ResponseEntity.ok(Objects.requireNonNull(response.getBody()).getData());
//    }
//}
//
