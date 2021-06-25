# MechaWars Kundengespräch 12.05.21
Teilnehmer: Prof. Karsten Weicker, Felix Schmeißer

## Konsolenmodus und daraus resultierende Architektur
Entwicklung eines gut gekapselten Spiel-Kerns, welcher:
- die Spielumgebung abbildet (Spieler, Position, Leben, Hindernisse, etc.)
- mit Parametern gestartet wird (verwendete Karte(n), Spielerzahl, etc.)

Sowohl GUI als auch Konsolenmodus bauen auf diesem Spielkern auf. Die GUI verhält sich dabei als Spiel, die Konsolenanwendung ist stark reduziert und erlaubt durch ihre Ausgaben eine Analyse getroffener Entscheidungen.

## KI
Die Komplexität der KI hängt stark vom Projektfortschritt ab. Grundsätzlich sollte die KI in der Lage sein, das Spielziel zu Erreichen. Fortgeschrittenere Konzepte wären dann die Erfassung von Gegenspielern, um:
- die eigene Bewegung anzupassen
- Beschuss zu vermeiden
- zu evaluieren, ob Reparatur notwendig und sinnvoll ist

Bei der Entwicklung sollte der Fokus darauf liegen, die bestehenden Fähigkeiten der KI inkrementell zu verbessern.

## Editor
Der Editor ist kein notwendiges Feature. Sollten besonders im zweiten Projektabschnitt freie Kapazitäten da sein oder sich Entwickler auf den Füßen stehen, dann kann man mit dieser Aufgabe 1-2 Studenten beschäftigen. Dieser Teil wäre dann gut abgekapselt von der restlichen Entwicklung implementierbar.

Unabhängig davon ist es sinnvoll, eine einfache Möglichkeit zu haben, Spielkarten durch ein eigenes Format zu darstellen und einlesen zu können. Nicht als Möglichkeit für den Spieler, sondern um uns die Entwicklung zu vereinfachen.

Teil der GUI-Anwendung könnte aber ggf. die Möglichkeit sein, eine begrenzte Anzahl an Karten wie im echten Spiel auch kombinieren zu können. Man hat also die Liste mit Karten und kann diese z.B. in einem 2x2 Feld (also max. 4) anordnen.

## Sonstige Bemerkungen
- Extra Mechaniken außen vor lassen
- Wir sind frei in der grafischen Umsetzung
- Musik und Sounds haben keinerlei Priorität