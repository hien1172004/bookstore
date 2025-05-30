package ptit.example.btlwebbook.service;

import ptit.example.btlwebbook.dto.request.VoucherDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.VoucherResponse;

public interface VoucherService {
    PageResponse<?> getAllVoucher(int pageNo, int pageSize, boolean valid, String sort);

    VoucherResponse create(VoucherDTO voucherDTO);

    VoucherResponse updateVoucher(long id, VoucherDTO voucherDTO);

    void deleteVoucher(long id);

    VoucherResponse getById(long id);

    VoucherResponse getByCode(String code);
}
