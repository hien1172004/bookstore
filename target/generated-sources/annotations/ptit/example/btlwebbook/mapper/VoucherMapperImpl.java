package ptit.example.btlwebbook.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.VoucherDTO;
import ptit.example.btlwebbook.dto.response.VoucherResponse;
import ptit.example.btlwebbook.model.Voucher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:33+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class VoucherMapperImpl implements VoucherMapper {

    @Override
    public Voucher toVoucher(VoucherDTO voucherDTO) {
        if ( voucherDTO == null ) {
            return null;
        }

        Voucher.VoucherBuilder<?, ?> voucher = Voucher.builder();

        voucher.code( voucherDTO.getCode() );
        voucher.createdBy( voucherDTO.getCreatedBy() );
        voucher.end( voucherDTO.getEnd() );
        voucher.minimum( voucherDTO.getMinimum() );
        voucher.name( voucherDTO.getName() );
        voucher.start( voucherDTO.getStart() );
        voucher.value( voucherDTO.getValue() );

        return voucher.build();
    }

    @Override
    public void updateVoucher(Voucher voucher, VoucherDTO voucherDTO) {
        if ( voucherDTO == null ) {
            return;
        }

        voucher.setCode( voucherDTO.getCode() );
        voucher.setCreatedBy( voucherDTO.getCreatedBy() );
        voucher.setEnd( voucherDTO.getEnd() );
        voucher.setMinimum( voucherDTO.getMinimum() );
        voucher.setName( voucherDTO.getName() );
        voucher.setStart( voucherDTO.getStart() );
        voucher.setValue( voucherDTO.getValue() );
    }

    @Override
    public VoucherResponse toVoucherResponse(Voucher voucher) {
        if ( voucher == null ) {
            return null;
        }

        VoucherResponse.VoucherResponseBuilder voucherResponse = VoucherResponse.builder();

        voucherResponse.code( voucher.getCode() );
        voucherResponse.createdBy( voucher.getCreatedBy() );
        voucherResponse.end( voucher.getEnd() );
        voucherResponse.id( voucher.getId() );
        voucherResponse.minimum( voucher.getMinimum() );
        voucherResponse.name( voucher.getName() );
        voucherResponse.start( voucher.getStart() );
        if ( voucher.getValue() != null ) {
            voucherResponse.value( voucher.getValue() );
        }

        return voucherResponse.build();
    }
}
