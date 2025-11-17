## Übung 1: Testfälle für eine Verkaufssoftware mit Rabattregeln

### Beschreibung:

Die Verkaufssoftware bietet eine Rabattregelung für den Kauf von Autos. Die
Rabattregeln basieren auf dem Kaufpreis des Autos:

-   Preis < 15'000 CHF: Kein Rabatt
-   Preis <= 20'000 CHF: 5% Rabatt
-   Preis < 25'000 CHF: 7% Rabatt
-   Preis >= 25'000 CHF: 8,5% Rabatt

### 1. Abstrakte Testfälle (mit logischen Operatoren)

| ID   | Beschreibung                      | Erwartetes Ergebnis |
| ---- | --------------------------------- | ------------------- |
| TC01 | Preis < 15'000 CHF                | Kein Rabatt         |
| TC02 | 15'000 CHF <= Preis <= 20'000 CHF | 5% Rabatt           |
| TC03 | 20'000 CHF < Preis < 25'000 CHF   | 7% Rabatt           |
| TC04 | Preis >= 25'000 CHF               | 8,5% Rabatt         |

### 2. Konkrete Testfälle (mit konkreten Werten)

| ID   | Beschreibung                                        | Eingabewert (Preis) | Erwartetes Ergebnis |
| ---- | --------------------------------------------------- | ------------------- | ------------------- |
| TC01 | Test ohne Rabatt für einen Preis unter 15'000 CHF   | 14'000 CHF          | Kein Rabatt         |
| TC02 | Test mit 5% Rabatt für einen Preis von 18'000 CHF   | 18'000 CHF          | 5% Rabatt           |
| TC03 | Test mit 7% Rabatt für einen Preis von 23'000 CHF   | 23'000 CHF          | 7% Rabatt           |
| TC04 | Test mit 8,5% Rabatt für einen Preis von 30'000 CHF | 30'000 CHF          | 8,5% Rabatt         |

---

## Übung 2: Funktionale Black-Box Tests für eine Autovermietung-Webseite MERBAG.CH

### Funktionale Black-Box Tests für eine Autovermietung

Für eine Autovermietungsplattform müssen verschiedene funktionale Tests
durchgeführt werden. Die folgenden fünf Testfälle sind Beispiele für
Black-Box-Tests, bei denen nur die Eingaben und die erwarteten Ergebnisse
berücksichtigt werden, ohne dass der interne Code bekannt ist.

| ID   | Beschreibung                                         | Erwartetes Resultat                                                | Effektives Resultat     | Status | Mögliche Ursache |
| ---- | ---------------------------------------------------- | ------------------------------------------------------------------ | ----------------------- | ------ | ---------------- |
| TC01 | Benutzer kann sich erfolgreich einloggen             | Login-Fenster öffnet sich nach Eingabe der Anmeldedaten            | Erfolgreiches Login     | OK     | -                |
| TC02 | Fahrzeug kann korrekt gebucht werden                 | Bestätigung der Buchung wird angezeigt                             | Buchung bestätigt       | OK     | -                |
| TC03 | Fahrzeugauswahl zeigt nur verfügbare Fahrzeuge       | Liste der verfügbaren Fahrzeuge wird angezeigt                     | Fahrzeugliste angezeigt | OK     | -                |
| TC04 | Buchung eines Fahrzeugs für den gewünschten Zeitraum | Verfügbarkeit des Fahrzeugs im gewünschten Zeitraum wird angezeigt | Verfügbarkeit bestätigt | OK     | -                |
| TC05 | Bezahlung mit Kreditkarte funktioniert korrekt       | Bestätigung der erfolgreichen Bezahlung erscheint                  | Bezahlung bestätigt     | OK     | -                |

---

## Übung 3: Testfälle für eine Bank-Software

Die Bank-Software bietet grundlegende Funktionen zur Verwaltung von Konten. Wir
wollen Black-Box- und White-Box-Tests identifizieren, um die Qualität der
Software zu überprüfen.

### 1. Black-Box Testfälle

Black-Box-Tests berücksichtigen nur die Eingabewerte und die erwarteten
Ergebnisse, ohne den internen Code zu kennen. Hier sind einige wichtige
Testfälle:

| ID   | Beschreibung                                              | Erwartetes Resultat                                                   | Effektives Resultat     | Status | Mögliche Ursache |
| ---- | --------------------------------------------------------- | --------------------------------------------------------------------- | ----------------------- | ------ | ---------------- |
| TC01 | Überprüfung des Logins mit gültigen Benutzerdaten         | Erfolgreiches Login, Benutzer wird zur Hauptseite weitergeleitet      | Login erfolgreich       | OK     | -                |
| TC02 | Überprüfung des Logins mit ungültigen Benutzerdaten       | Fehlermeldung: "Ungültige Anmeldedaten"                               | Fehlermeldung angezeigt | OK     | -                |
| TC03 | Überprüfung der Kontostand-Abfrage                        | Der aktuelle Kontostand wird korrekt angezeigt                        | Kontostand korrekt      | OK     | -                |
| TC04 | Überweisung von einem Konto auf ein anderes               | Überweisung wird erfolgreich durchgeführt, Bestätigung wird angezeigt | Überweisung erfolgreich | OK     | -                |
| TC05 | Abhebung von einem Konto, das nicht genügend Guthaben hat | Fehlermeldung: "Unzureichendes Guthaben"                              | Fehlermeldung angezeigt | OK     | -                |

### 2. White-Box Testfälle

White-Box-Tests berücksichtigen den internen Code und identifizieren Bereiche,
die getestet werden müssen, um die Funktionalität und den Codefluss zu
überprüfen. Im Fall der Bank-Software könnten dies Testfälle auf der Grundlage
der Methoden und ihrer Implementierung sein.

-   **Testfall 1**: Test der `berechneZinsen()` Methode, um zu überprüfen, ob
    die Zinsen korrekt berechnet werden.
-   **Testfall 2**: Test der `überweiseBetrag()` Methode, um sicherzustellen,
    dass eine Überweisung korrekt durchgeführt wird und alle Bedingungen (wie
    ausreichendes Guthaben) geprüft werden.
-   **Testfall 3**: Test der `loginVerifizierung()` Methode, um zu überprüfen,
    ob die Login-Daten ordnungsgemäss validiert werden.
-   **Testfall 4**: Test der `getKontostand()` Methode, um zu überprüfen, ob der
    Kontostand korrekt aus der Datenbank abgerufen wird.

# Übung 3 – Testanalyse der simplen Bank-Software

## Black-Box Testfälle

_(Aus Sicht einer Benutzerin, ohne Kenntnis der internen Implementierung)_

| Testfall                       | Beschreibung                                      | Erwartetes Ergebnis                                  |
| ------------------------------ | ------------------------------------------------- | ---------------------------------------------------- |
| Konto erstellen                | Neues Konto mit Name, Startsaldo, Währung anlegen | Konto wird korrekt angelegt, ID automatisch vergeben |
| Einzahlung                     | Betrag auf ein Konto einzahlen                    | Saldo steigt um den Betrag                           |
| Auszahlung (genug Guthaben)    | Betrag abheben                                    | Saldo sinkt um den Betrag                            |
| Auszahlung (zu wenig Guthaben) | Mehr abheben als verfügbar                        | Fehlermeldung bzw. kein negativer Saldo              |
| Kontosuche                     | Konto anhand von Owner-Namen abrufen              | Korrektes Konto wird zurückgegeben oder `null`       |
| Anzahl Konten                  | Anzahl der bestehenden Konten abfragen            | korrekte Anzahl                                      |
| Wechselkurs-Abfrage            | API-Request ausführen                             | Erfolgreiche Antwort mit Resultat                    |
| Wechselkurs-Abfrage Fehlerfall | Falsche Währung oder keine Internetverbindung     | Fehlermeldung / Ausnahme wird behandelt              |
| Thread-Zugriff auf Counter     | Mehrere Threads erhöhen den Counter               | Wert erhöht sich korrekt und ohne Race Condition     |

---

## White-Box Testfälle

_(Basierend auf einzelnen Methoden im Code)_

| Methode                        | Was testen?                                        | Ziel                                                        |
| ------------------------------ | -------------------------------------------------- | ----------------------------------------------------------- |
| `Account.deposit()`            | Verschiedene positive/negative Werte               | Korrekte Anpassung des Saldos, keine negativen Einzahlungen |
| `Account.withdraw()`           | Randfälle, z.B. 0, negativer Wert, zu hoher Betrag | Sicherstellen, dass Fehlverhalten abgefangen wird           |
| `Bank.getAccountByOwner()`     | Case-Sensitivity, nicht vorhandene Namen           | Sicherstellen, dass die Suche robust funktioniert           |
| `Counter.add()`                | Parallelzugriffe                                   | Thread-Safety durch `synchronized` überprüfen               |
| `ExchangeRateOkhttp.convert()` | Erfolgs- und Fehlerfälle des JSON-Parsens          | API-Handling, Exception-Verhalten                           |

---

## Verbesserungsmöglichkeiten / Best Practices

-   **Fehlende Fehlerbehandlung** – z. B. `withdraw` sollte nicht einfach
    negatives Guthaben erlauben.
-   **Input Validation** – Methoden sollten ungültige Parameter abfangen (z. B.
    negative Einzahlungen).
-   **Encapsulation stärken** – einige Felder sind nicht `private`.
-   **Logging statt `System.out.println`** – erleichtert Fehlersuche.
-   **Unit Tests einführen** – JUnit für White-Box Tests einsetzen.
-   **API-Aufruf entkoppeln** – Interface einführen, um HTTP-Client besser
    mocken zu können.
-   **Enum verbessern** – ggf. ISO-Codes hinterlegen.
-   **Thread-Sicherheit der Bank** – Kontoliste ist nicht synchronisiert.
-   **Mehrschichtige Architektur** – Trennung zwischen Domain, Service, API, UI.

---

## Kurzfazit

Die Software eignet sich gut, um grundlegende Testkonzepte zu üben.  
Viele potenzielle Testfälle ergeben sich aus Randwerten, Fehlerfällen und dem
parallelen Zugriff.
