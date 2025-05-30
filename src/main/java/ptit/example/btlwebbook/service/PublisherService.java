package ptit.example.btlwebbook.service;

import ptit.example.btlwebbook.dto.request.PublisherDTO;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.dto.response.PublisherResponse;
import ptit.example.btlwebbook.model.Publisher;

public interface PublisherService {
    PublisherResponse savePublisher(PublisherDTO publisherDTO);

    PublisherResponse updatePublisher(Long id, PublisherDTO publisherDTO);

    void deletePublisher(Long id);

    PublisherResponse getDetailResponse(Long id);

    PageResponse<?> getAllPublisher(int pageNo, int pageSize);
}
