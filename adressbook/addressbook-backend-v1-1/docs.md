Aufgabe 1

-   Tests hinzugefügt: Unit-Tests für `Address`, `AddressService` (mit gemocktem
    `AddressRepository` statt H2) und `AddressController` (MockMvc, Service
    gemockt). `@BeforeEach` wird in allen Testklassen verwendet.
-   Comparator implementiert: `AddressComparator` sortiert standardmässig nach
    `lastname`, dann `firstname`, dann `id` (null-sicher, case-insensitive).

Aufgabe 2

-   Comparator erweitert: Unterstützung für zusätzliche Sortierschlüssel via
    `AddressComparator.SortKey` und konfigurierbare Reihenfolge in neuem
    Konstruktor.
-   Tests ergänzt: Zusätzliche Tests für Sortierung nach `REGISTRATION_DATE`,
    Null-Handling und Case-Insensitivity.

Hinweis

-   Service verwendet weiterhin den Default-Comparator ohne Codeänderungen
    (`new AddressComparator()`), erhält damit automatisch die korrekte und
    erweiterte Sortierung.
