package ptit.example.btlwebbook.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.AddressDTO;
import ptit.example.btlwebbook.dto.response.AddressResponse;
import ptit.example.btlwebbook.model.Address;
import ptit.example.btlwebbook.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:33+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address.AddressBuilder<?, ?> address = Address.builder();

        address.address( addressDTO.getAddress() );
        address.districtId( addressDTO.getDistrictId() );
        address.provinceId( addressDTO.getProvinceId() );
        address.wardId( addressDTO.getWardId() );

        return address.build();
    }

    @Override
    public AddressResponse toAddressResponse(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponse.AddressResponseBuilder addressResponse = AddressResponse.builder();

        addressResponse.userId( addressUserId( address ) );
        addressResponse.address( address.getAddress() );
        addressResponse.districtId( address.getDistrictId() );
        addressResponse.id( address.getId() );
        addressResponse.provinceId( address.getProvinceId() );
        addressResponse.wardId( address.getWardId() );

        return addressResponse.build();
    }

    private Long addressUserId(Address address) {
        if ( address == null ) {
            return null;
        }
        User user = address.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
