package site.metacoding.junitproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.junitproject.web.dto.response.BookRespDto;

@NoArgsConstructor
@Getter
@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 20, nullable = false)
    private String author;

    @Builder
    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public void update(String titie, String author) {
        this.title = titie;
        this.author = author;
    }

    public BookRespDto toDto() {
        return BookRespDto.builder()
                .id(id)
                .title(title)
                .author(author)
                .build();
    }
}
