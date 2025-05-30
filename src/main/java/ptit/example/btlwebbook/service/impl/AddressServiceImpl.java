package ptit.example.btlwebbook.service.impl;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.example.btlwebbook.dto.request.AddressDTO;
import ptit.example.btlwebbook.dto.response.AddressResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.AddressMapper;
import ptit.example.btlwebbook.model.Address;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.repository.AddressRepository;
import ptit.example.btlwebbook.repository.UserRepository;
import ptit.example.btlwebbook.service.AddressService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;
    @Override
    @Transactional
    public AddressResponse saveAddress(long userid, AddressDTO addressDTO) {
        User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Address address = addressMapper.toAddress(addressDTO);
        address.setUser(user);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }

    @Override
    @Transactional
    public List<AddressResponse> getAddress(long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        List<Address> addresses = addressRepository.findByUser_Id(userID);
        return addresses.stream().map(addressMapper::toAddressResponse).toList();
    }

    @Override
    @Transactional
    public void deleteAddress(long userId, long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address addressToDelete = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        if(!addressToDelete.getUser().getId().equals(userId)){
            throw new RuntimeException("Address dose not belong to user");
        }
        addressRepository.delete(addressToDelete);
        log.info("save address success");
    }

    @Override
    @Transactional
    public void updateDefaultAddressId(long userId, long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address addressToUpdate = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        if(!addressToUpdate.getUser().getId().equals(userId)){
            throw new RuntimeException("Address dose not belong to user");
        }
        Address address = addressRepository.findByUser_IdAndIsDefaultIsTrue(userId).orElse(null);
        if (address != null) {
            // Kiểm tra nếu địa chỉ cần cập nhật đã là mặc định
            if (address.getId() == addressId) {
                throw new DuplicateRequestException("This address is already the default");
            }
            // Nếu không phải là địa chỉ mặc định, cập nhật lại địa chỉ mặc định cũ
            address.setDefault(false);
            addressRepository.save(address);  // Lưu địa chỉ cũ (đặt là không mặc định)

            // Đặt địa chỉ cần cập nhật thành mặc định
            addressToUpdate.setDefault(true);
        } else {
            // Nếu không có địa chỉ mặc định, chỉ cần đặt địa chỉ cần cập nhật thành mặc định
            addressToUpdate.setDefault(true);
        }

        // Lưu địa chỉ cần cập nhật (được đánh dấu là mặc định)
        addressRepository.save(addressToUpdate);

        log.info("Successfully updated the default address");
    }
}
