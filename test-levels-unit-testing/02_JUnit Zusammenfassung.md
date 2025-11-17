# JUnit – Kurze Zusammenfassung

## Wichtige Features

### **@Test**

Markiert eine Methode als Testfall.  
_Beispiel:_

```java
@Test
void testAddition() {
    assertEquals(5, calc.add(2, 3));
}
```

---

### **Assertions**

Prüfen, ob ein erwartetes Ergebnis eintritt. _Beispiele:_

```java
assertEquals(10, result);
assertTrue(value > 0);
assertThrows(Exception.class, () -> methode());
```

---

### **@BeforeEach / @AfterEach**

Wird vor bzw. nach jedem Test ausgeführt (z.B. Objekt initialisieren).

```java
@BeforeEach
void setup() {
    calc = new Calculator();
}
```

---

### **@BeforeAll / @AfterAll**

Einmal vor bzw. nach allen Tests (z.B. teure Ressourcen laden).

```java
@BeforeAll
static void initAll() { ... }
```

---

### **@DisplayName**

Verleiht einem Test einen lesbaren Namen.

```java
@DisplayName("Addition funktioniert korrekt")
@Test
void addTest() { ... }
```

---

### **@ParameterizedTest**

Ausführen des gleichen Tests mit mehreren Werten.

```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void testPositiveNumbers(int n) {
    assertTrue(n > 0);
}
```

---

### **Test Suites**

Mehrere Testklassen gemeinsam starten.

```java
@RunWith(JUnitPlatform.class)
@SelectPackages("calculator")
class AllTests { }
```
