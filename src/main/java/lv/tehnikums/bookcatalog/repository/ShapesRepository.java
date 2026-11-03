package lv.tehnikums.bookcatalog.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import lv.tehnikums.bookcatalog.model.Shapes;

public interface ShapesRepository extends JpaRepository<Shapes, Integer> {

    @EntityGraph(attributePaths = "category")
    List<Shapes> findAllByOrderByTitleAsc();

    @EntityGraph(attributePaths = "category")
    List<Shapes> findByCategory_IdOrderByTitleAsc(Integer categoryId);

    @Override
    @EntityGraph(attributePaths = "category")
    Optional<Shapes> findById(Integer id);
}