//Q: Was sind das für Kommentare?
/**
 * @(#)FoE.java
 *
 */

package drunk.homebrew.forge.of.empires.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse zum starten der App
 *
 * @author Dhalia
 * FIXME: Unnötige Leerzeilen vermeiden
 *
 */

@SpringBootApplication
public class FoE {
	public static void main(String[] args) {
        SpringApplication.run(FoE.class,args);
	}
}