package ptit.example.btlwebbook.service;

import ptit.example.btlwebbook.dto.request.GenreDTO;
import ptit.example.btlwebbook.dto.response.GenreResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;

import java.util.List;

public interface GenreService {
    GenreResponse saveGenre(GenreDTO genreDTO);

    GenreResponse updateGenre(Long Id, GenreDTO genreDTO);

    GenreResponse getDetailGenre(Long Id);

    PageResponse<?> getAllGenre(int pageNo, int pageSize);

    GenreResponse getBySLug(String slug);

    void deleteGenre(Long id);
}
