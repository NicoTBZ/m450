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

## Übung 2: Funktionale Black-Box Tests für eine Autovermietung-Webseite

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
    ob die Login-Daten ordnungsgemäß validiert werden.
-   **Testfall 4**: Test der `getKontostand()` Methode, um zu überprüfen, ob der
    Kontostand korrekt aus der Datenbank abgerufen wird.

### 3. Verbesserungsvorschläge und Best Practices

-   **Code-Qualität**: Der Code könnte modularer gestaltet werden, um die
    Wiederverwendbarkeit zu erhöhen und die Testbarkeit zu verbessern.
-   **Fehlerbehandlung**: Die Fehlerbehandlung sollte einheitlicher und robuster
    sein, insbesondere für Fälle wie falsche Eingaben oder fehlende
    Netzwerkverbindungen.
-   **Sicherheit**: Sicherheitsmechanismen wie Verschlüsselung für sensible
    Daten (z. B. Passwörter, Transaktionsdaten) sollten implementiert werden, um
    die Daten der Benutzer zu schützen.
-   **Dokumentation**: Eine vollständige Dokumentation der Funktionen und ihrer
    Eingabewerte sowie der erwarteten Ergebnisse ist erforderlich, um Testfälle
    effizient zu entwickeln.
