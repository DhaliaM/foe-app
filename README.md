Dies ist nur ein Übungsprojekt, welches mir ursprünglich dazu diente das auslesen einer .yaml Datei ohne Spring zu üben.
Wurde nach und nach komplexer um verschiedene Techniken auszuprobieren und zu lernen.

Ziel des Tools ist es eine Ressourcenproduktion von einem Browsergame zu berechnen.

- ohne Spring:

Die erste Version war nur eine Konsoloenausgabe der Berechnung, während die Daten in einer yaml gespeichert sind.
Danach wurde die Konsolenversion durch Servlet und HTML eretzt, und mittels WAR auf einen Tomcat deployed.
Der nächste Schritt war das überführen der Datenspeicherung von yaml in eine DB (MariaDB, Jdbc).
Die letzte Version ohne Spring war der Wechsel von Jdbc zu Hibernate.

- mit Spring:

Das Programm wurde auf Spring umgestellt und die HTML Seiten wurden über Thymeleaf generiert.
Es folgten die DB Anbindung über Spring-Data und eine Login Sicherheitsprüfung mittels Spring-Security und HTTP BasicAuth.

# foe-app