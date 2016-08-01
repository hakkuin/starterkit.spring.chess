package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {
	
    @Autowired
    private BookService bookService;
    
    @Autowired
    private CacheManager cacheManager;
    
    @Before
    public void setUp() {
        cacheManager.getCache("booksCache").clear();
    }
    
    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        String resultTitle = booksByTitle.get(0).getTitle();
        
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
        assertEquals(title, resultTitle);
    }
    
    @Test
    public void testShouldAddNextId() {
        // given
        final BookTo bookToSave = new BookTo(null, "Biblia", "nieznany");

        // when
        BookTo result = bookService.saveBook(bookToSave);
        
        // then
        assertNotNull(result.getId());
        assertEquals(new Long(6), result.getId());
    }
    
    @Test
    public void testShouldFindAllBooksByAuthor() {
        // given
        final String author = "Wiliam Szekspir";
        final BookTo newBook = new BookTo(7L, "Makbet", author);
        
        // when
        bookService.saveBook(newBook);
        List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
        String resultAuthor = booksByAuthor.get(0).getAuthors();
        
		// then
		assertNotNull(booksByAuthor);
		assertEquals(2, booksByAuthor.size());
		assertEquals(author, resultAuthor);
    }
}
