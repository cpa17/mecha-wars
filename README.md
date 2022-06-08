# Mecha Wars

Software Projekt mit 9 anderen Studenten über 2 Semester. <br>
Aufgabe war es das Spiel Robo Rally als Computer Version in Java zu programmieren.

Alle Informationen zum Quellcode: [MechaWars.pdf](https://github.com/cpa17/mecha-wars/files/8861135/MechaWars.pdf)


## Bedienung

Startet man das Spiel, so kann man im Startbildschirm auf Spiel starten klicken und somit die Mapauswahl, die Anzahl der Spieler (Jeder über 1 entspricht einem KI-Spieler) und der Map (Karte). Sollte keine expliziete Karte eingegeben werden, so wird automatisch die Standardkarte ausgewählt.

Anschließend kann man wiederrum das Spiel beginnen.

Im Spielbildschirm angekommen, sieht man links das Spielfeld (=board) und rechts eine Anzahl an buttons. Diese dienen zur Interaktion. So findet man im Schwarz unterlegten Bereich die Spielkarten des Spielers z.B.

Spielanleitung: [roborally-manual.pdf](https://github.com/cpa17/mecha-wars/files/8861140/roborally-manual.pdf)

## Konsolen-Modus (Terminal)

### 1. Einrichtung

Zunächst muss im Ordner code die gradlew.bat einmal ausgeführt werden. Anschließend kann in die Kommandozeile folgendes eingegeben werden:
* gradlew.bat desktop:dist Dadurch wird im (neuerstellten) Unterordner code/desktop/builds/libs eine .jar Datei erstellt.

### 2. Starten

Diese muss nun ausgeführt werden. Dazu ist der Befehl:
* java -jar desktop-1.0.jar im entsprechenden Verzeichnis einzugeben.

### 3. Konfiguration(en)

Für die Darstellung aller möglichen Konfigurationen muss in die Kommandozeile wiederrum die Eingabe erfolgen:
* java -jar desktop-1.0.jar -h

Das h steht dabei für help (dt. = Hilfe).

Sollte das Spiel im Konsolenmodus gestartet werden und der Befehl -w eingetragen werden, so ist das spiel im Fullscreen-Modus (dt. = Vollbild).
