package ptit.example.btlwebbook.service;

import org.springframework.stereotype.Service;
import ptit.example.btlwebbook.dto.request.AuthorDTO;
import ptit.example.btlwebbook.dto.response.AuthorResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;


public interface AuthorService {
    long saveAuthor(AuthorDTO author);

    void updateAuthor(long authorId, AuthorDTO author);

    void deleteAuthor(long authorId);

    AuthorResponse findAuthorById(long authorId);

    PageResponse<?>getAllAuthor(int pageNo, int pageSize, String ...sorts);
}
