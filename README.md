Dies ist nur ein Übungsprojekt, welches mir ursprünglich dazu diente das auslesen einer .yaml Datei ohne Spring zu üben.
Wurde nach und nach komplexer um verschiedene Techniken auszuprobieren und zu lernen.

Ziel des Tools ist es eine Ressourcenproduktion von einem Browsergame zu berechnen.

- ohne Spring:

Die erste Version war nur eine Konsolenausgabe der Berechnung, während die Daten in einer yaml gespeichert sind.
Danach wurde die Konsolenversion durch Servlet und HTML ersetzt, und mittels WAR auf einen Tomcat deployed.
Der nächste Schritt war das überführen der Datenspeicherung von yaml in eine DB (MariaDB, Jdbc).
Die letzte Version ohne Spring war der Wechsel von Jdbc zu Hibernate.

- mit Spring:

Das Programm wurde auf Spring umgestellt und die HTML Seiten wurden über Thymeleaf generiert.
Es folgten die DB Anbindung über Spring-Data und eine Login Sicherheitsprüfung mittels Spring-Security und HTTP BasicAuth.

- HowTo start in Linux

MariaDB starten:

Datenbank mit dem Namen "FoE1" anlegen 

User "FoE_User" mit Pw "roflcopter" hinzufügen

foe-app Verzeichnis:

Befehl: mvn clean install  (Maven muß installiert sein)

target Verzeichnis:

Befehl:java  -jar ForgeOfEmpires-0.0.1-SNAPSHOT.jar

Nun ist die App/Seite über http://localhost:8080/ erreichbar

Login:

name: admin

Pw: roflcopter
# foe-app