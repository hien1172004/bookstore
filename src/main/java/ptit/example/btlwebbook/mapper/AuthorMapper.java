package ptit.example.btlwebbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ptit.example.btlwebbook.dto.request.AuthorDTO;
import ptit.example.btlwebbook.dto.response.AuthorResponse;
import ptit.example.btlwebbook.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorDTO authorDTO);
    void updateAuthor(@MappingTarget Author author, AuthorDTO authorDTO);

    AuthorResponse toAuthorResponse(Author author);
}
