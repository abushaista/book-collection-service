package com.arif.bookservice.infrastructure.query.book;

import com.arif.bookservice.application.book.query.BookQueryRepository;
import com.arif.bookservice.application.book.query.BookView;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcBookQueryRepository implements BookQueryRepository {
    private final JdbcTemplate jdbc;

    public JdbcBookQueryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<BookView> findAll() {
        return this.searchByTitleOrAuthor(null);
    }

    @Override
    public List<BookView> searchByTitleOrAuthor(String keyword) {
        String sql = """
            SELECT
                b.id,
                b.title,
                b.author,
                b.isbn,
                b.publication_year,
                b.category_id,
                c.name AS category_name,
                b.description
            FROM books b
            JOIN categories c ON c.id = b.category_id
            WHERE (? IS NULL
               OR LOWER(b.title) LIKE LOWER(CONCAT('%', ?, '%'))
               OR LOWER(b.author) LIKE LOWER(CONCAT('%', ?, '%'))
               OR LOWER(c.name) LIKE LOWER(CONCAT('%', ?, '%')))
            ORDER BY b.title
        """;

        return jdbc.query(
                sql,
                (rs, i) -> new BookView(
                        rs.getObject("id", UUID.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getObject("publication_year", Integer.class),
                        rs.getObject("category_id", UUID.class),
                        rs.getString("category_name"),
                        rs.getString("description")
                ),
                keyword, keyword, keyword, keyword
        );
    }

    @Override
    public Optional<BookView> findById(UUID id) {
        String sql = """
                select * from categories where id = ?
                """;
        return jdbc.query(
                sql,
                rs -> rs.next() ?
                        Optional.of(new BookView(
                                rs.getObject("id", UUID.class),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("isbn"),
                                rs.getObject("publication_year", Integer.class),
                                rs.getObject("category_id", UUID.class),
                                rs.getString("category_name"),
                                rs.getString("description")
                        )) : Optional.empty(),
                id
        );
    }


}
