package ptit.example.btlwebbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ptit.example.btlwebbook.dto.request.GenreDTO;
import ptit.example.btlwebbook.dto.response.GenreResponse;
import ptit.example.btlwebbook.model.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre ToGenre(GenreDTO genreDTO);

    void updateGenre(@MappingTarget Genre genre, GenreDTO genreDTO);

    GenreResponse toGenreResponse(Genre genre);
}
