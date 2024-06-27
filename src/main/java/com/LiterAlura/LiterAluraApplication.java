package com.LiterAlura;

import com.LiterAlura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private Principal principal; // Spring inyecta automáticamente la instancia

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.showMenu(); // Llama al método showMenu() de la instancia inyectada
	}
}