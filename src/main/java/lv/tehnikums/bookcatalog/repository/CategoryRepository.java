package lv.tehnikums.bookcatalog.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import lv.tehnikums.bookcatalog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByOrderByNameAsc();
}
