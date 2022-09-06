package site.metacoding.junitproject;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import site.metacoding.junitproject.domain.Book;
import site.metacoding.junitproject.web.dto.response.BookRespDto;

public class RealTest {

    @Test
    public void map_test() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "junit강의", "메타코딩"));
        books.add(new Book(2L, "spring강의", "겟인데어"));

    }
}
