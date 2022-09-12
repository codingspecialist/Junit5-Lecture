package site.metacoding.junitproject.web.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookListRespDto {
    List<BookRespDto> items;

    @Builder
    public BookListRespDto(List<BookRespDto> bookList) {
        this.items = bookList;
    }
}
