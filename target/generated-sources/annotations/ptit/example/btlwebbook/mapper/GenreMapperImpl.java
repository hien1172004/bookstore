package ptit.example.btlwebbook.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.GenreDTO;
import ptit.example.btlwebbook.dto.response.GenreResponse;
import ptit.example.btlwebbook.model.Genre;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:34+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre ToGenre(GenreDTO genreDTO) {
        if ( genreDTO == null ) {
            return null;
        }

        Genre.GenreBuilder<?, ?> genre = Genre.builder();

        genre.name( genreDTO.getName() );

        return genre.build();
    }

    @Override
    public void updateGenre(Genre genre, GenreDTO genreDTO) {
        if ( genreDTO == null ) {
            return;
        }

        genre.setName( genreDTO.getName() );
    }

    @Override
    public GenreResponse toGenreResponse(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreResponse.GenreResponseBuilder genreResponse = GenreResponse.builder();

        genreResponse.id( genre.getId() );
        genreResponse.name( genre.getName() );
        genreResponse.slug( genre.getSlug() );

        return genreResponse.build();
    }
}
