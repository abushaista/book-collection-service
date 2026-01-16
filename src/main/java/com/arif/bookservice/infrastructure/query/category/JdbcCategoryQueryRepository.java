package com.arif.bookservice.infrastructure.query.category;

import com.arif.bookservice.application.category.query.CategoryQueryRepository;
import com.arif.bookservice.application.category.query.CategoryView;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcCategoryQueryRepository implements CategoryQueryRepository {
    private final JdbcTemplate jdbc;
    public JdbcCategoryQueryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<CategoryView> findAll() {
        String sql = "select * from categories order by id desc";

        return jdbc.query(sql, (rs, rowNum) -> new CategoryView(
                rs.getObject("id", UUID.class),
                rs.getString("name"),
                rs.getString("description")
        ));
    }

    @Override
    public Optional<CategoryView> findById(UUID id) {
        String sql = "select * from categories where id = ?";
        return jdbc.query(
                sql,
                rs -> rs.next() ?
                        Optional.of(new CategoryView(
                                rs.getObject("id", UUID.class),
                                rs.getString("name"),
                                rs.getString("description")
                        )): Optional.empty(),
                id
        );

    }

    @Override
    public boolean existsById(UUID id) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM categories WHERE id = ?",
                Integer.class,
                id
        );
        return count != null && count > 0;
    }
}
