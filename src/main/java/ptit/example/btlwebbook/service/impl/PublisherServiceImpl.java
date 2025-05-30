package ptit.example.btlwebbook.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import ptit.example.btlwebbook.dto.request.PublisherDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.PublisherResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.PublisherMapper;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Publisher;
import ptit.example.btlwebbook.repository.BookRepository;
import ptit.example.btlwebbook.repository.PublisherRespository;
import ptit.example.btlwebbook.service.PublisherService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/publisher")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PublisherServiceImpl implements PublisherService {
    PublisherRespository publisherRespository;

    BookRepository bookRepository;

    PublisherMapper publisherMapper;
    @Override
    public PublisherResponse savePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.toPublisher(publisherDTO);
        publisherRespository.save(publisher);
        log.info("save success");
        return publisherMapper.toPublisherResponse(publisher);
    }

    @Override
    public PublisherResponse updatePublisher(Long id, PublisherDTO publisherDTO) {
        Publisher publisher = publisherRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found publisher"));
        publisherMapper.updatePublisher(publisher,publisherDTO);
        return publisherMapper.toPublisherResponse(publisherRespository.save(publisher));
    }

    @Override
    @Transactional
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found publisher"));
        List<Book> books = bookRepository.findAllByPublisher_Id(id);
        books.forEach(book -> {
            book.setPublisher(null);
            bookRepository.save(book);
        });
        publisherRespository.deleteById(id);
        log.info("deleted success");
    }

    @Override
    public PublisherResponse getDetailResponse(Long id) {
        Publisher publisher = publisherRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found publisher"));
        return publisherMapper.toPublisherResponse(publisher);
    }

    @Override
    public PageResponse<?> getAllPublisher(int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0){
            page = pageNo-1;
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Publisher> publishersList = publisherRespository.findAll(pageable);
        List<PublisherResponse> list = publishersList.stream().map(publisherMapper::toPublisherResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(publishersList.getTotalPages())
                .items(list)
                .build();
    }
}
