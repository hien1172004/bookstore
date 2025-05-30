package ptit.example.btlwebbook.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.PublisherDTO;
import ptit.example.btlwebbook.dto.response.PublisherResponse;
import ptit.example.btlwebbook.model.Publisher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:34+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public Publisher toPublisher(PublisherDTO publisherDTO) {
        if ( publisherDTO == null ) {
            return null;
        }

        Publisher.PublisherBuilder<?, ?> publisher = Publisher.builder();

        publisher.name( publisherDTO.getName() );

        return publisher.build();
    }

    @Override
    public void updatePublisher(Publisher publisher, PublisherDTO publisherDTO) {
        if ( publisherDTO == null ) {
            return;
        }

        publisher.setName( publisherDTO.getName() );
    }

    @Override
    public PublisherResponse toPublisherResponse(Publisher publisher) {
        if ( publisher == null ) {
            return null;
        }

        PublisherResponse.PublisherResponseBuilder publisherResponse = PublisherResponse.builder();

        publisherResponse.id( publisher.getId() );
        publisherResponse.name( publisher.getName() );

        return publisherResponse.build();
    }
}
