package ptit.example.btlwebbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ptit.example.btlwebbook.dto.request.AddressDTO;
import ptit.example.btlwebbook.dto.response.AddressResponse;
import ptit.example.btlwebbook.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressDTO addressDTO);
    @Mapping(target = "userId", source = "user.id")
    AddressResponse toAddressResponse(Address address);
}
