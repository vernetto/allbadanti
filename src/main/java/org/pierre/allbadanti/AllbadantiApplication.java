package org.pierre.allbadanti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@SpringBootApplication
public class AllbadantiApplication implements CommandLineRunner {
	public static final String ALLBADANTI_TXT = "C:\\pierre\\github\\mamma\\allbadanti.txt";
	@Autowired
	BadantiFileLoader badantiFileLoader;

	public static void main(String[] args) {
		SpringApplication.run(AllbadantiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File file = new File(ALLBADANTI_TXT);
		String directory = file.getParent();
		File vcardFile = new File(directory, "allbadantivcard.vcf");
		List<Badante> badanti = badantiFileLoader.loadAllBadanti(ALLBADANTI_TXT);
		StringBuilder sb = new StringBuilder();
		badanti.forEach(badante -> sb.append(badantiFileLoader.generateVCard(badante)).append("\n"));
        FileWriter writer = new FileWriter(vcardFile);
		writer.write(sb.toString());
		writer.close();
	}
}
