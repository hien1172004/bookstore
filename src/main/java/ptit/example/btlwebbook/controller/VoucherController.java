package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.VoucherDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.ResponeError;
import ptit.example.btlwebbook.dto.response.ResponseData;
import ptit.example.btlwebbook.dto.response.VoucherResponse;
import ptit.example.btlwebbook.repository.VoucherRepository;
import ptit.example.btlwebbook.service.VoucherService;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/vouchers")
@Validated
public class VoucherController {
    private final VoucherService voucherService;
    private final VoucherRepository voucherRepository;
    String ERROR_MESSAGE ="errorMessage: {} ";
    @PostMapping("/")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseData<VoucherResponse> createVoucher(@Valid @RequestBody VoucherDTO voucherDTO){
            VoucherResponse voucherResponse = voucherService.create(voucherDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", voucherResponse);
    }
    @PutMapping("/{voucherId}")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseData<VoucherResponse> updateVoucher(@Valid @RequestBody VoucherDTO voucherDTO, @PathVariable(name = "voucherId") Long id){
            VoucherResponse voucherResponse = voucherService.updateVoucher(id, voucherDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", voucherResponse);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseData<Void> deleteVoucher(@PathVariable long id){
            voucherService.deleteVoucher(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "success");
    }
    @GetMapping("/{id}")
    public ResponseData<VoucherResponse> getVoucherById(@PathVariable Long id){
            VoucherResponse voucherResponse = voucherService.getById(id);
            return new ResponseData<>(HttpStatus.OK.value(), "success", voucherResponse);
    }
    @GetMapping("/code/{code}")
    public ResponseData<VoucherResponse> getVoucherByCode(@PathVariable String code){
            VoucherResponse voucherResponse = voucherService.getByCode(code);
            return new ResponseData<>(HttpStatus.OK.value(), "success", voucherResponse);
    }

    @GetMapping("/")
    public ResponseData<?> getAllVoucher(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                         @RequestParam(defaultValue = "8", required = false) int pageSize,
                                         @RequestParam(defaultValue = "createdAt:desc", required = false) String sort,
                                         @RequestParam(defaultValue = "true", required = false) boolean valid){

            PageResponse<?> pageResponse = voucherService.getAllVoucher(pageNo, pageSize, valid, sort);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", pageResponse);
    }
}
