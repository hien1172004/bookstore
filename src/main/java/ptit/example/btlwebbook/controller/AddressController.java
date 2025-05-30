package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.AddressDTO;
import ptit.example.btlwebbook.dto.response.AddressResponse;
import ptit.example.btlwebbook.dto.response.ResponeError;
import ptit.example.btlwebbook.dto.response.ResponseData;
import ptit.example.btlwebbook.service.AddressService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class AddressController {
    private final AddressService addressService;
    String ERROR_MESSAGE ="errorMessage: {} ";
    @PostMapping("/{userId}/address")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseData<AddressResponse> createAddress(@PathVariable long userId, @RequestBody @Valid AddressDTO addressDTO){
        try {
            AddressResponse addressResponse = addressService.saveAddress(userId, addressDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", addressResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }


    @GetMapping("/{userId}/address")
    @PreAuthorize("#userId == authentication.principal.id or hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<List<AddressResponse>> getAddress(@PathVariable long userId){
        try {
            List<AddressResponse> addressResponse = addressService.getAddress(userId);
            return new ResponseData<>(HttpStatus.OK.value(), "success", addressResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PatchMapping("/{userId}/address/status/{addressId}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseData<Void> updateAddressDefault(@PathVariable long userId, @PathVariable long addressId){
        try {
            addressService.updateDefaultAddressId(userId, addressId);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success");
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }


    @DeleteMapping("/{userId}/address/{addressId}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseData<Void> deleteAddress(@PathVariable long userId, @PathVariable long addressId){
        try {
            addressService.deleteAddress(userId, addressId);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "success");
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
