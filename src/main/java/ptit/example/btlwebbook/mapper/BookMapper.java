package ptit.example.btlwebbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ptit.example.btlwebbook.dto.request.BookDTO;
import ptit.example.btlwebbook.dto.response.BookResponse;
import ptit.example.btlwebbook.model.Book;
@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(BookDTO bookDTO);
    BookResponse toBookResponse(Book book);

    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    void updateBook(@MappingTarget Book book, BookDTO bookDTO );

}
