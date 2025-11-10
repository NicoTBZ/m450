### Aufgabe 1: Formen von Tests in der Informatik

In der Informatik gibt es verschiedene Testarten, die in unterschiedlichen
Phasen der Softwareentwicklung angewendet werden. Drei gängige Beispiele sind:

1. **Unit-Test**:

    - **Beschreibung**: Testet einzelne Funktionen oder Methoden eines Programms
      isoliert. Ziel ist es, Fehler in einzelnen Komponenten der Software zu
      erkennen.
    - **Durchführung**: Jeder Testfall überprüft, ob eine Methode mit bestimmten
      Eingabewerten das erwartete Ergebnis liefert. Dies geschieht oft mithilfe
      von Testframeworks wie JUnit (für Java) oder pytest (für Python).

2. **Integrationstest**:

    - **Beschreibung**: Testet das Zusammenspiel zwischen verschiedenen Modulen
      oder Komponenten eines Systems. Hier wird überprüft, ob die verschiedenen
      Teile der Software korrekt miteinander interagieren.
    - **Durchführung**: Es werden verschiedene Module zusammen ausgeführt, um
      sicherzustellen, dass die Schnittstellen korrekt arbeiten. Beispielsweise
      könnte man einen Datenbankzugriff mit einer API testen, um zu sehen, ob
      die Kommunikation zwischen den Systemen korrekt funktioniert.

3. **Akzeptanztest**:

    - **Beschreibung**: Verifiziert, ob die Software die Anforderungen des
      Kunden erfüllt. Dieser Test wird häufig am Ende eines Entwicklungszyklus
      durchgeführt, um die Benutzerakzeptanz zu überprüfen.
    - **Durchführung**: Der Test wird in einer echten oder simulierten
      Benutzerumgebung durchgeführt. Der Tester überprüft, ob die Software die
      definierten Anforderungen und Kriterien erfüllt, die vor der Entwicklung
      festgelegt wurden.

### Aufgabe 2: Beispiel für Software-Fehler und Software-Mangel

1. **Software-Fehler (Bug)**:

    - **Beispiel**: Ein Softwarefehler könnte auftreten, wenn eine Anwendung
      einen Absturz verursacht, weil eine Division durch null versucht wird.

        - **Beispiel**: Eine mathematische Berechnung in einer Finanzsoftware
          verursacht einen Absturz, weil der Divisor null ist.
        - **Schaden**: Dies kann zu Systemausfällen führen, was die
          Benutzererfahrung beeinträchtigt und in kritischen Systemen (wie
          Banken) zu schweren Verlusten führen kann.

2. **Software-Mangel (Defect)**:

    - **Beispiel**: Ein Software-Mangel könnte sein, dass eine Anwendung eine
      Funktion nicht korrekt implementiert, obwohl sie laut Spezifikation
      erwartet wird.

        - **Beispiel**: In einem Kalkulationstool wird der Umsatzsteuerbetrag
          nicht korrekt berechnet, auch wenn die Formel korrekt ist.
        - **Schaden**: Der Mangel könnte zu falschen Steuerberechnungen führen,
          was rechtliche und finanzielle Konsequenzen nach sich ziehen könnte.

3. **Beispiel für einen hohen Schaden bei einem Software-Fehler**:

    - **Beispiel**: In einer Flugbuchungssoftware wird aufgrund eines
      Programmfehlers ein Flug überbucht, was dazu führt, dass mehrere
      Passagiere nicht befördert werden.

        - **Schaden**: Dies könnte zu erheblichen finanziellen Verlusten für die
          Fluggesellschaft und zu negativen Auswirkungen auf das Kundenvertrauen
          und den Ruf führen.

### Aufgabe 3: Test der Preisberechnung in einer Auto-Verkauf Software

#### 1. Der Code zur Preisberechnung

Hier ist der gegebene Code zur Berechnung des Preises:

```java
double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
    double addon_discount;
    double result;

    if (extras >= 3)
        addon_discount = 10;
    else if (extras >= 5)
        addon_discount = 15;
    else
        addon_discount = 0;

    if (discount > addon_discount)
        addon_discount = discount;

    result = baseprice / 100.0 * (100 - discount) + specialprice
            + extraprice / 100.0 * (100 - addon_discount);

    return result;
}
```

#### 2. Testtreiber erstellen

Ein Testtreiber ruft die `calculatePrice` Methode auf und prüft, ob die
Berechnung korrekt erfolgt.

```java
boolean test_calculate_price() {
    double price;
    boolean test_ok = true;

    // Test 1: Keine Extras und kein Rabatt
    price = calculatePrice(20000, 1000, 5000, 0, 5);
    if (price != 21500.0) {
        System.out.println("Fehler bei Test 1");
        test_ok = false;
    }

    // Test 2: Extras >= 3, Rabatt auf Extras 10%
    price = calculatePrice(20000, 1000, 5000, 4, 5);
    if (price != 21450.0) {
        System.out.println("Fehler bei Test 2");
        test_ok = false;
    }

    // Test 3: Extras >= 5, Rabatt auf Extras 15%
    price = calculatePrice(20000, 1000, 5000, 5, 5);
    if (price != 21325.0) {
        System.out.println("Fehler bei Test 3");
        test_ok = false;
    }

    // Test 4: Rabatt auf Extras höher als der Händlerrabatt
    price = calculatePrice(20000, 1000, 5000, 3, 12);
    if (price != 21300.0) {
        System.out.println("Fehler bei Test 4");
        test_ok = false;
    }

    return test_ok;
}
```

#### 3. Tests durchführen

-   **Test 1**: Grundpreis 20.000 CHF, Sondermodellaufschlag 1.000 CHF, Extras
    0, Rabatt 5%. Erwartetes Ergebnis: 21.500 CHF.
-   **Test 2**: Grundpreis 20.000 CHF, Sondermodellaufschlag 1.000 CHF, Extras
    4, Rabatt 5%. Erwartetes Ergebnis: 21.450 CHF.
-   **Test 3**: Grundpreis 20.000 CHF, Sondermodellaufschlag 1.000 CHF, Extras
    5, Rabatt 5%. Erwartetes Ergebnis: 21.325 CHF.
-   **Test 4**: Grundpreis 20.000 CHF, Sondermodellaufschlag 1.000 CHF, Extras
    3, Rabatt 12%. Erwartetes Ergebnis: 21.300 CHF.
