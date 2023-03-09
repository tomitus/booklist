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
import bookstore.training.domain.User;
import bookstore.training.domain.UserRepository;

@SpringBootApplication
public class TrainingApplication {
	private static final Logger log = LoggerFactory.getLogger(TrainingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}
	
	

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("example data");
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Thriller"));
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Action"));
			crepository.save(new Category("Adventure"));
			
			brepository.save(new Book("The Snowman", "Jo Nesbo", 1990, "1234567-89", 30.00, crepository.findByName("Thriller").get(0)));
			brepository.save(new Book("Dark forces", "Stephen Leather", 1999, "9876543-21", 25.00, crepository.findByName("Adventure").get(0)));
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6","user@user.com", "USER");
			User user2 = new User("admin", "$2a$10$8SN1ndUmG.EcWnxQh3R9tu9tLzo2cSDlGs/PFyt1z5Xghji9p2vrC", "admin@admin.com", "ADMIN");

			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
