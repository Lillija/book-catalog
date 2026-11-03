package lv.tehnikums.bookcatalog.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lv.tehnikums.bookcatalog.service.ShapesService;
import lv.tehnikums.bookcatalog.web.ShapesForm;

@Controller
public class ShapesController {

    private final ShapesService service;

    public ShapesController(ShapesService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String list(@RequestParam(name = "categoryId", required = false) Integer categoryId, Model model) {
        model.addAttribute("books", service.getBooks(categoryId));
        model.addAttribute("categories", service.getCategories());
        model.addAttribute("selectedCategoryId", categoryId);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        var book = service.getBook(id);
        if (book.isEmpty()) {
            return "redirect:/books?notFound";
        }
        model.addAttribute("book", book.get());
        return "book-details";
    }

    @GetMapping("/books/new")
    public String form(Model model) {
        model.addAttribute("bookForm", new ShapesForm());
        model.addAttribute("categories", service.getCategories());
        return "book-form";
    }

    @PostMapping("/books")
    public String create(@Valid @ModelAttribute("bookForm") ShapesForm form,
                         BindingResult result, Model model) {
        if (!result.hasErrors()) {
            try {
                service.createBook(form);
                return "redirect:/books?created";
            } catch (IllegalArgumentException ex) {
                result.rejectValue("categoryId", "invalid", ex.getMessage());
            }
        }
        model.addAttribute("categories", service.getCategories());
        return "book-form";
    }
}