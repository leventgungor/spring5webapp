package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * created by levent.gungor in 04.02.2020
 */

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author ilber = new Author("İlber","Ortaylı");
        Book omur = new Book("Bir Ömür Nasıl Yaşanır", 45678);
        Publisher kronik = new Publisher("Kronik Yayınevi", 500000.0);


        ilber.addBook(omur);
        omur.addAuthor(ilber);
        omur.setPublisher(kronik);
        kronik.addBook(omur);


        authorRepository.save(ilber);
        bookRepository.save(omur);
        publisherRepository.save(kronik);

        System.out.println("Started in Bootstrap");
        System.out.println("Publisher count : " + publisherRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number oF Books: " + kronik.getBooks().size());

    }
}
