package ptit.example.btlwebbook.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ptit.example.btlwebbook.dto.request.VoucherDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.VoucherResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.VoucherMapper;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.model.Voucher;
import ptit.example.btlwebbook.repository.UserRepository;
import ptit.example.btlwebbook.repository.VoucherRepository;
import ptit.example.btlwebbook.service.VoucherService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository voucherRepository;
    private final VoucherMapper voucherMapper;
    private final UserRepository userRepository;

    @Override
    public PageResponse<?> getAllVoucher(int pageNo, int pageSize, boolean valid, String sortBy) {
        int page = 0;
        if(pageNo > 0){
            page = pageNo - 1;
        }

        List<Sort.Order> sorts = new ArrayList<Sort.Order>();
        if(StringUtils.hasLength(sortBy)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                } else if (matcher.group(3).equalsIgnoreCase("desc")) {
                    sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sorts));
        Page<Voucher> vouchers;
        if(valid){
             vouchers = voucherRepository.findAllVoucherActive(pageable);
        }
        else{
            vouchers = voucherRepository.findAll(pageable);
        }
        List<VoucherResponse> voucherResponses = vouchers.stream()
                .map(voucherMapper::toVoucherResponse)
                .toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(vouchers.getTotalPages())
                .items(voucherResponses)
                .build();
    }

    @Override
    public VoucherResponse create(VoucherDTO voucherDTO) {
//        User user = userRepository.findByEmail(voucherDTO.getCreatedBy()).orElseThrow(() ->
//                new ResourceNotFoundException("user not found"));
        Voucher voucher = voucherMapper.toVoucher(voucherDTO);
        voucherRepository.save(voucher);
        return voucherMapper.toVoucherResponse(voucher);
    }

    @Override
    public VoucherResponse updateVoucher(long id, VoucherDTO voucherDTO) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("not found voucher"));
        voucherMapper.updateVoucher(voucher, voucherDTO);
        voucherRepository.save(voucher);
        log.info("voucher updated");
        return voucherMapper.toVoucherResponse(voucher);
    }

    @Override
    public void deleteVoucher(long id) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("not found voucher"));
        voucherRepository.delete(voucher);
    }

    @Override
    public VoucherResponse getById(long id) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("not found voucher"));
        return voucherMapper.toVoucherResponse(voucher);
    }

    @Override
    public VoucherResponse getByCode(String code) {
        Voucher voucher = voucherRepository.findByCode(code);
        return voucherMapper.toVoucherResponse(voucher);
    }
}
