package ptit.example.btlwebbook.service;

import ptit.example.btlwebbook.dto.request.AddressDTO;
import ptit.example.btlwebbook.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse saveAddress(long userid, AddressDTO addressDTO);

   List<AddressResponse> getAddress(long userID);

    void deleteAddress(long userId, long addressId);

    void updateDefaultAddressId(long userId, long addressId);
}
