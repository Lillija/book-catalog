package lv.tehnikums.bookcatalog.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import lv.tehnikums.bookcatalog.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @EntityGraph(attributePaths = "category")
    List<Book> findAllByOrderByTitleAsc();

    @EntityGraph(attributePaths = "category")
    List<Book> findByCategory_IdOrderByTitleAsc(Integer categoryId);

    @Override
    @EntityGraph(attributePaths = "category")
    Optional<Book> findById(Integer id);
}