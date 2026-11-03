package lv.tehnikums.bookcatalog.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lv.tehnikums.bookcatalog.model.Shapes;
import lv.tehnikums.bookcatalog.model.Category;
import lv.tehnikums.bookcatalog.repository.ShapesRepository;
import lv.tehnikums.bookcatalog.repository.CategoryRepository;
import lv.tehnikums.bookcatalog.web.ShapesForm;

@Service
@Transactional(readOnly = true)
public class ShapesService {

    private final ShapesRepository books;
    private final CategoryRepository categories;

    public ShapesService(ShapesRepository books, CategoryRepository categories) {
        this.books = books;
        this.categories = categories;
    }

    public List<Shapes> getBooks(Integer categoryId) {
        if (categoryId == null) {
            return books.findAllByOrderByTitleAsc();
        }
        return books.findByCategory_IdOrderByTitleAsc(categoryId);
    }

    public List<Category> getCategories() {
        return categories.findAllByOrderByNameAsc();
    }

    public Optional<Shapes> getBook(Integer id) {
        return books.findById(id);
    }

    @Transactional
    public void createBook(ShapesForm form) {
        Category category = categories.findById(form.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Izvēlētā kategorija vairs neeksistē."));

        Shapes book = new Shapes();
        book.setTitle(form.getTitle().trim());
        book.setAuthor(form.getAuthor().trim());
        book.setPublicationYear(form.getPublicationYear());
        book.setDescription(form.getDescription());
        book.setCategory(category);

        books.save(book);
    }
}