package ptit.example.btlwebbook.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.AuthorDTO;
import ptit.example.btlwebbook.dto.request.BookDTO;
import ptit.example.btlwebbook.dto.request.GenreDTO;
import ptit.example.btlwebbook.dto.request.PublisherDTO;
import ptit.example.btlwebbook.dto.response.AuthorResponse;
import ptit.example.btlwebbook.dto.response.BookResponse;
import ptit.example.btlwebbook.dto.response.GenreResponse;
import ptit.example.btlwebbook.dto.response.PublisherResponse;
import ptit.example.btlwebbook.model.Author;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Genre;
import ptit.example.btlwebbook.model.Publisher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:33+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book.BookBuilder<?, ?> book = Book.builder();

        book.authors( authorDTOListToAuthorSet( bookDTO.getAuthors() ) );
        book.code( bookDTO.getCode() );
        book.description( bookDTO.getDescription() );
        book.discount( bookDTO.getDiscount() );
        book.genres( genreDTOListToGenreSet( bookDTO.getGenres() ) );
        book.imageUrl( bookDTO.getImageUrl() );
        book.name( bookDTO.getName() );
        book.pages( bookDTO.getPages() );
        book.price( bookDTO.getPrice() );
        book.publicId( bookDTO.getPublicId() );
        book.publisher( publisherDTOToPublisher( bookDTO.getPublisher() ) );
        book.size( bookDTO.getSize() );
        book.year( bookDTO.getYear() );

        return book.build();
    }

    @Override
    public BookResponse toBookResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponse.BookResponseBuilder bookResponse = BookResponse.builder();

        bookResponse.authors( authorSetToAuthorResponseList( book.getAuthors() ) );
        bookResponse.code( book.getCode() );
        bookResponse.description( book.getDescription() );
        bookResponse.discount( book.getDiscount() );
        bookResponse.genres( genreSetToGenreResponseList( book.getGenres() ) );
        bookResponse.id( book.getId() );
        bookResponse.imageUrl( book.getImageUrl() );
        bookResponse.name( book.getName() );
        bookResponse.pages( book.getPages() );
        bookResponse.price( book.getPrice() );
        bookResponse.publisher( publisherToPublisherResponse( book.getPublisher() ) );
        bookResponse.size( book.getSize() );
        bookResponse.slug( book.getSlug() );
        bookResponse.year( book.getYear() );

        return bookResponse.build();
    }

    @Override
    public void updateBook(Book book, BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return;
        }

        if ( book.getAuthors() != null ) {
            Set<Author> set = authorDTOListToAuthorSet( bookDTO.getAuthors() );
            if ( set != null ) {
                book.getAuthors().clear();
                book.getAuthors().addAll( set );
            }
            else {
                book.setAuthors( null );
            }
        }
        else {
            Set<Author> set = authorDTOListToAuthorSet( bookDTO.getAuthors() );
            if ( set != null ) {
                book.setAuthors( set );
            }
        }
        book.setCode( bookDTO.getCode() );
        book.setDescription( bookDTO.getDescription() );
        book.setDiscount( bookDTO.getDiscount() );
        if ( book.getGenres() != null ) {
            Set<Genre> set1 = genreDTOListToGenreSet( bookDTO.getGenres() );
            if ( set1 != null ) {
                book.getGenres().clear();
                book.getGenres().addAll( set1 );
            }
            else {
                book.setGenres( null );
            }
        }
        else {
            Set<Genre> set1 = genreDTOListToGenreSet( bookDTO.getGenres() );
            if ( set1 != null ) {
                book.setGenres( set1 );
            }
        }
        book.setName( bookDTO.getName() );
        book.setPages( bookDTO.getPages() );
        book.setPrice( bookDTO.getPrice() );
        if ( bookDTO.getPublisher() != null ) {
            if ( book.getPublisher() == null ) {
                book.setPublisher( Publisher.builder().build() );
            }
            publisherDTOToPublisher1( bookDTO.getPublisher(), book.getPublisher() );
        }
        else {
            book.setPublisher( null );
        }
        book.setSize( bookDTO.getSize() );
        book.setYear( bookDTO.getYear() );
    }

    protected Author authorDTOToAuthor(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author.AuthorBuilder<?, ?> author = Author.builder();

        author.name( authorDTO.getName() );

        return author.build();
    }

    protected Set<Author> authorDTOListToAuthorSet(List<AuthorDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Author> set = new LinkedHashSet<Author>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( AuthorDTO authorDTO : list ) {
            set.add( authorDTOToAuthor( authorDTO ) );
        }

        return set;
    }

    protected Genre genreDTOToGenre(GenreDTO genreDTO) {
        if ( genreDTO == null ) {
            return null;
        }

        Genre.GenreBuilder<?, ?> genre = Genre.builder();

        genre.name( genreDTO.getName() );

        return genre.build();
    }

    protected Set<Genre> genreDTOListToGenreSet(List<GenreDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Genre> set = new LinkedHashSet<Genre>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( GenreDTO genreDTO : list ) {
            set.add( genreDTOToGenre( genreDTO ) );
        }

        return set;
    }

    protected Publisher publisherDTOToPublisher(PublisherDTO publisherDTO) {
        if ( publisherDTO == null ) {
            return null;
        }

        Publisher.PublisherBuilder<?, ?> publisher = Publisher.builder();

        publisher.name( publisherDTO.getName() );

        return publisher.build();
    }

    protected AuthorResponse authorToAuthorResponse(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorResponse.AuthorResponseBuilder authorResponse = AuthorResponse.builder();

        authorResponse.id( author.getId() );
        authorResponse.name( author.getName() );

        return authorResponse.build();
    }

    protected List<AuthorResponse> authorSetToAuthorResponseList(Set<Author> set) {
        if ( set == null ) {
            return null;
        }

        List<AuthorResponse> list = new ArrayList<AuthorResponse>( set.size() );
        for ( Author author : set ) {
            list.add( authorToAuthorResponse( author ) );
        }

        return list;
    }

    protected GenreResponse genreToGenreResponse(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreResponse.GenreResponseBuilder genreResponse = GenreResponse.builder();

        genreResponse.id( genre.getId() );
        genreResponse.name( genre.getName() );
        genreResponse.slug( genre.getSlug() );

        return genreResponse.build();
    }

    protected List<GenreResponse> genreSetToGenreResponseList(Set<Genre> set) {
        if ( set == null ) {
            return null;
        }

        List<GenreResponse> list = new ArrayList<GenreResponse>( set.size() );
        for ( Genre genre : set ) {
            list.add( genreToGenreResponse( genre ) );
        }

        return list;
    }

    protected PublisherResponse publisherToPublisherResponse(Publisher publisher) {
        if ( publisher == null ) {
            return null;
        }

        PublisherResponse.PublisherResponseBuilder publisherResponse = PublisherResponse.builder();

        publisherResponse.id( publisher.getId() );
        publisherResponse.name( publisher.getName() );

        return publisherResponse.build();
    }

    protected void publisherDTOToPublisher1(PublisherDTO publisherDTO, Publisher mappingTarget) {
        if ( publisherDTO == null ) {
            return;
        }

        mappingTarget.setName( publisherDTO.getName() );
    }
}
