package bookstore.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.training.TrainingApplication;
import bookstore.training.domain.Book;
import bookstore.training.domain.BookRepository;
import bookstore.training.domain.Category;
import bookstore.training.domain.CategoryRepository;


@SpringBootApplication
public class TrainingApplication {
	private static final Logger log = LoggerFactory.getLogger(TrainingApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("example data");
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Thriller"));
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Action"));
			crepository.save(new Category("Adventure"));
			
			repository.save(new Book("The Snowman", "Jo Nesbo", 1990, "1234567-89", 30.00, crepository.findByName("Thriller").get(0)));
			repository.save(new Book("Dark forces", "Stephen Leather", 1999, "9876543-21", 25.00, crepository.findByName("Adventure").get(0)));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
