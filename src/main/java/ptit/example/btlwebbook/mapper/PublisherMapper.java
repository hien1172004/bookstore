package ptit.example.btlwebbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ptit.example.btlwebbook.dto.request.PublisherDTO;
import ptit.example.btlwebbook.dto.response.PublisherResponse;
import ptit.example.btlwebbook.model.Publisher;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    Publisher toPublisher(PublisherDTO publisherDTO);

    void updatePublisher(@MappingTarget Publisher publisher, PublisherDTO publisherDTO);

    PublisherResponse toPublisherResponse(Publisher publisher);
}
