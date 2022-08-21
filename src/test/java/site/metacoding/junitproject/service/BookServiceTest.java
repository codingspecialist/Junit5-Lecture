package site.metacoding.junitproject.service;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import site.metacoding.junitproject.domain.Book;
import site.metacoding.junitproject.domain.BookRepository;
import site.metacoding.junitproject.util.MailSender;
import site.metacoding.junitproject.web.dto.BookRespDto;
import site.metacoding.junitproject.web.dto.BookSaveReqDto;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;

    // 문제점 -> 서비스만 테스트하고 싶은데, 레포지토리 레이어가 함께 테스트 된다는 점!!
    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("junit강의");
        dto.setAuthor("메타코딩");

        // stub (가설)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        // when
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        // then
        // assertEquals("junit하하하", bookRespDto.getTitle());
        // assertEquals(dto.getAuthor(), bookRespDto.getAuthor());

        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());

    }

    @Test
    public void 책목록보기_테스트() {
        // given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "junit강의", "메타코딩"));
        books.add(new Book(2L, "spring강의", "겟인데어"));

        books.stream().forEach((b) -> {
            System.out.println(b.getId());
            System.out.println(b.getTitle());
            System.out.println("============");
        });

        // stub
        when(bookRepository.findAll()).thenReturn(books);

        // when
        List<BookRespDto> bookRespDtoList = bookService.책목록보기();

        bookRespDtoList.stream().forEach((b) -> {
            System.out.println(b.getId());
            System.out.println(b.getTitle());
            System.out.println("============");
        });

        // then
        assertThat(bookRespDtoList.get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookRespDtoList.get(0).getAuthor()).isEqualTo("메타코딩");
        assertThat(bookRespDtoList.get(1).getTitle()).isEqualTo("spring강의");
        assertThat(bookRespDtoList.get(1).getAuthor()).isEqualTo("겟인데어");
    }

    @Test
    public void 책한건보기_테스트() {
        // given
        Long id = 1L;

        // stub
        Book book = new Book(1L, "junit강의", "메타코딩");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);

        // when
        BookRespDto bookRespDto = bookService.책한건보기(id);

        // then
        assertThat(bookRespDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(book.getAuthor());
    }

    @Test
    public void 책수정하기_테스트() {
        // given
        Long id = 1L;
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("spring강의"); // spring강의
        dto.setAuthor("겟인데어"); // 겟인데어

        // stub
        Book book = new Book(1L, "junit강의", "메타코딩");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);

        // when
        BookRespDto bookRespDto = bookService.책수정하기(id, dto);

        // then
        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());

    }
}
