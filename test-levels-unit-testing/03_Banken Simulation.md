# Banken-Simulation – Kurzbeschreibung

## Überblick

Die Software bildet eine sehr einfache Banken-Simulation ab. Es gibt **Bank**,
**Account**, **Counter**, **ExchangeRateOkhttp** und eine **Main**-Klasse,
welche das Programm startet. Das Klassendiagramm zeigt die Beziehungen: Eine
Bank besitzt mehrere Accounts, auf denen Buchungen ausgeführt werden können.

---

## Funktionsweise & Zusammenhänge (Stichworte)

### **Bank**

-   Verwaltet eine Liste aller **Account**-Objekte
-   Methoden: Konto erstellen, einzahlen, auszahlen, Saldo anzeigen
-   Ruft intern die jeweiligen Methoden des Account auf
-   Dient als zentrale Steuerstelle für alle Kontoaktionen

### **Account**

-   Speichert: Kontonummer / Benutzer, Währung, aktueller Kontostand
-   Methoden: `deposit()`, `withdraw()`, `getBalance()`, `print()`
-   Enthält Logik, ob eine Transaktion möglich ist
-   Jede Buchung verändert den Kontostand des Accounts

### **Booking**

-   Repräsentiert eine einzelne Buchung (Betrag, Datum)
-   Wird beim Einzahlen und Abheben erstellt
-   Dient als Protokoll für Transaktionen

### **SavingsAccount / SalaryAccount / PromoYouthSavingsAccount**

-   Erben von **Account**
-   Überschreiben einzelne Methoden (z. B. spezielle Regeln für Ein- oder
    Auszahlungen)
-   Beispiel: Jugendkonto erlaubt Bonuszahlungen

### **BankUtils**

-   Hilfsklasse für Formatierung (Beträge, Datumsformat)
-   Wird von Bank / Account / Booking genutzt

### **Counter**

-   Einfache Konsolen-UI
-   Fragt Benutzer nach gewünschter Aktion (einzahlen, abheben, Saldo, …)
-   Delegiert alles an die Bank-Instanz

### **ExchangeRateOkhttp**

-   Holt Wechselkurse per HTTP (OkHttp + JSON/GSON)
-   Wird zum Konvertieren zwischen verschiedenen Währungen genutzt

### **Main**

-   Startpunkt der Anwendung
-   Erstellt Bank-Objekt
-   Startet den **Counter**, um die Interaktion zu ermöglichen

---

## Gesamtzusammenhang

-   **Main** startet → **Counter** öffnet eine Menüführung
-   **Counter** ruft Methoden in **Bank** auf
-   **Bank** leitet Transaktionen an **Account** weiter
-   **Account** ändert Kontostand und erzeugt **Booking**-Einträge
-   **BankUtils** formatiert die Ausgabe
-   **ExchangeRateOkhttp** liefert externe Kurse, wenn Währungsumrechnungen
    nötig sind
