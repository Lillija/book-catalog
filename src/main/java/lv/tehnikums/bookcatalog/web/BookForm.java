package lv.tehnikums.bookcatalog.web;

import jakarta.validation.constraints.*;

public class BookForm {

    @NotBlank(message = "Ievadiet nosaukumu.")
    @Size(max = 200, message = "Ne vairāk kā 200 rakstzīmes.")
    private String title;

    @NotBlank(message = "Ievadiet autoru.")
    @Size(max = 150, message = "Ne vairāk kā 150 rakstzīmes.")
    private String author;

    @NotNull(message = "Ievadiet izdošanas gadu.")
    @Min(value = 1, message = "Gadam jābūt pozitīvam.")
    private Integer publicationYear;

    @Size(max = 5000, message = "Apraksts ir pārāk garš.")
    private String description;

    @NotNull(message = "Izvēlieties kategoriju.")
    private Integer categoryId;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
}
