package ptit.example.btlwebbook.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.AuthorDTO;
import ptit.example.btlwebbook.dto.response.AuthorResponse;
import ptit.example.btlwebbook.model.Author;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:34+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author toAuthor(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author.AuthorBuilder<?, ?> author = Author.builder();

        author.name( authorDTO.getName() );

        return author.build();
    }

    @Override
    public void updateAuthor(Author author, AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return;
        }

        author.setName( authorDTO.getName() );
    }

    @Override
    public AuthorResponse toAuthorResponse(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorResponse.AuthorResponseBuilder authorResponse = AuthorResponse.builder();

        authorResponse.id( author.getId() );
        authorResponse.name( author.getName() );

        return authorResponse.build();
    }
}
