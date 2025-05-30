package ptit.example.btlwebbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ptit.example.btlwebbook.dto.request.VoucherDTO;
import ptit.example.btlwebbook.dto.response.VoucherResponse;
import ptit.example.btlwebbook.model.Voucher;

@Mapper(componentModel = "spring")
public interface VoucherMapper {
    Voucher toVoucher(VoucherDTO voucherDTO);

    void updateVoucher(@MappingTarget Voucher voucher, VoucherDTO voucherDTO);

    VoucherResponse toVoucherResponse(Voucher voucher);

}
