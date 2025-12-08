# Spring Boot Angular Basic - Deployment & CI/CD

## Aufgabe Summary

Dieses Projekt erfüllt die Anforderungen der beiden M450-Aufgaben:

### 1. Deployment Environments Aufgabe ✅
- **Aufgabe 1**: Recherche & Dokumentation der Softwarelösungen
  - Dokument: `DEPLOYMENT_ENVIRONMENTS.md`
  - Abgedeckt: Docker Compose, Kubernetes, Vagrant, Terraform
  - Empfehlungen pro Umgebung (Dev, Test, Staging, Prod)

- **Aufgabe 2**: Praktisches Setup mit Docker Compose
  - `docker-compose.yml` vorhanden mit Backend + Frontend
  - Weitere Konfiguration in `Dockerfile.backend` & `Dockerfile.frontend`
  - Reflexion: siehe `DEPLOYMENT_ENVIRONMENTS.md` Kapitel "Empfohlene Kombinationen"

- **Aufgabe 3 (optional)**: Kubernetes & Terraform Setup
  - Optional: Kubernetes Manifests können hinzugefügt werden
  - Optional: Terraform IaC für Cloud-Deployment

### 2. CI/CD Pipeline Aufgabe ✅
- **Aufgabe 1**: Unit Testing im Backend
  - ✅ Controller Tests mit MockMvc
    - Datei: `src/test/java/ch/tbz/m450/testing/tools/controller/StudentControllerTests.java`
    - Tests für `GET /students`, `POST /students`, Edge Cases
  
  - ✅ Entity Tests mit SoftAssertions
    - Datei: `src/test/java/ch/tbz/m450/testing/tools/repository/entities/StudentEntityTests.java`
    - Demonstriert SoftAssertions-Vorteil (mehrere Assertions sammeln)

- **Aufgabe 2**: Reports automatisiert generieren
  - ✅ Surefire Plugin (Test-Reports in XML)
  - ✅ JaCoCo Plugin (Code Coverage HTML-Reports)
  - Konfiguration: `pom.xml` (siehe `<build><plugins>`)

- **Aufgabe 3**: GitLab CI/CD Pipeline
  - ✅ `.gitlab-ci.yml` definiert 4 Stages: Build, Test, Report, Pages
  - ✅ Automatisierter Trigger auf Push/Merge Request
  - ✅ Reports per Pipeline-Durchlauf, 90 Tage speichern
  - ✅ Optional: GitLab Pages für öffentliche Coverage Reports

---

## Dateien & Struktur

```
spring-boot-angular-basic-lw2/
├── .gitlab-ci.yml                  # CI/CD Pipeline Definition
├── docker-compose.yml              # Container Orchestration
├── pom.xml                         # Maven Build mit Surefire + JaCoCo
├── DEPLOYMENT_ENVIRONMENTS.md      # Aufgabe 1: Deployment-Umgebungen Doku
├── CI_CD_PIPELINE.md               # Aufgabe 2 & 3: Pipeline Dokumentation
│
├── src/main/java/ch/tbz/m450/testing/tools/
│   ├── controller/
│   │   └── StudentController.java
│   ├── repository/
│   │   ├── StudentRepository.java
│   │   └── entities/
│   │       └── Student.java
│   └── StudentApplication.java
│
└── src/test/java/ch/tbz/m450/testing/tools/
    ├── StudentApplicationTests.java  # Basic context test
    ├── controller/
    │   └── StudentControllerTests.java  # ✅ MockMvc Tests
    └── repository/entities/
        └── StudentEntityTests.java      # ✅ SoftAssertions Tests
```

---

## Lokales Ausführen

### Voraussetzungen
- Java 17+
- Maven 3.8+
- Docker & Docker Compose (optional für Container-Run)

### Tests lokal ausführen mit Reports

```bash
# In Projekt-Root
cd spring-boot-angular-basic-lw2

# Tests mit Coverage Report
mvn clean test jacoco:report

# Reports anschauen
# Windows:
start target\site\jacoco\index.html

# Linux/Mac:
open target/site/jacoco/index.html
```

### Nur Tests (ohne Reports)
```bash
mvn test
```

### Docker Compose starten (lokal)
```bash
docker-compose up -d

# Backend läuft auf http://localhost:8081
# Frontend läuft auf http://localhost:4200
```

---

## GitLab CI/CD Pipeline

Die Pipeline wird automatisch bei jedem Push/Merge Request ausgelöst.

### Pipeline Stages

1. **build** (Compile)
   - `mvn clean compile`
   - Artifacts: `target/`

2. **test** (Unit Tests)
   - `mvn test`
   - Artifacts: `target/surefire-reports/`, `target/site/jacoco/`
   - JUnit-Reports werden in GitLab angezeigt

3. **report** (Coverage)
   - `mvn jacoco:report`
   - Artifacts: Coverage HTML-Reports (90 Tage)

4. **pages** (GitLab Pages, optional)
   - Veröffentlicht Coverage Reports auf GitLab Pages
   - Abrufbar unter: `https://<username>.gitlab.io/<project>/`

### Pipeline-Status in GitLab
- Push/Commit → `Pipelines` Reiter
- Tests erfolgreich? → Grünes Häkchen
- Tests fehlgeschlagen? → Rotes X + Details anschauen

### Report Download
- `Pipelines` → Build Pipeline klicken
- `Artifacts` Tab → `Download` für Reports

---

## Unit Tests - Übersicht

### StudentControllerTests (MockMvc)

```java
// GET /students - alle Studenten abrufen
testGetStudents()          // ✓ Liste mit 2 Studenten
testGetStudentsEmpty()     // ✓ Leere Liste

// POST /students - neuen Studenten hinzufügen
testAddStudent()           // ✓ Erfolgreiche Einzahl
testAddStudentWithInvalidData() // ✓ Ungültige Daten
```

**Was wird getestet:**
- HTTP-Status (200, 201)
- JSON Response-Struktur
- Mocking der Repository-Layer

### StudentEntityTests (SoftAssertions)

```java
testStudentCreation()      // ✓ Konstruktor & Getter
testStudentWithSoftAssertions()  // ✓ Mehrere Assertions zusammen
testStudentWithMultipleSoftAssertions() // ✓ Batch-Testing
```

**SoftAssertions Vorteil:**
```java
SoftAssertions softly = new SoftAssertions();
softly.assertThat(student.getName()).isEqualTo("Max");
softly.assertThat(student.getEmail()).isEqualTo("max@example.com");
softly.assertAll();  // Alle Assertions abfragen, dann Fehler melden
```
→ Alle Fehler auf einmal sehen (nicht nur der erste)

---

## Code Coverage Reports

### Was wird gemessen?

1. **Line Coverage**: % der ausgeführten Code-Zeilen
2. **Branch Coverage**: % der If/Else Verzweigungen
3. **Method Coverage**: % der aufgerufenen Methoden

### Report Struktur

```
target/site/jacoco/
├── index.html                 # Hauptseite
├── ch/tbz/m450/testing/...   # Navigierbar nach Paketen
└── ... detaillierte .class Reports
```

### Interpretation

- 🟢 Grün: Gut abgedeckt
- 🟡 Gelb: Teilweise abgedeckt (z.B. nur 1 Branch getestet)
- 🔴 Rot: Nicht abgedeckt

---

## Deployment Environments Empfehlungen

Siehe `DEPLOYMENT_ENVIRONMENTS.md` für detaillierte Analyse.

**Kurzzusammenfassung:**

| Umgebung | Empfohlenes Tool |
|----------|------------------|
| Development | Docker Compose |
| Testing | Docker Compose + CI/CD |
| Staging | Kubernetes |
| Production | Kubernetes + Terraform |

---

## Maven Plugins

### Surefire (Test-Reports)
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0-M9</version>
</plugin>
```
→ Generiert `target/surefire-reports/TEST-*.xml`

### JaCoCo (Code Coverage)
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.10</version>
</plugin>
```
→ Generiert `target/site/jacoco/index.html`

### AssertJ (SoftAssertions)
```xml
<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <scope>test</scope>
</dependency>
```

---

## Nächste Schritte (Optional - Challenge)

1. **Kubernetes Manifests**: `k8s/deployment.yaml`, `k8s/service.yaml`
2. **Terraform**: AWS/Azure Cloud Infrastructure
3. **Mehr Tests**: Service Layer, Integration Tests
4. **GitHub Actions**: Alternative zu GitLab CI (`.github/workflows/`)
5. **Docker Registry**: Images in DockerHub/ECR pushen
6. **Performance**: Parallel Test-Execution, Caching

---

## Links & Ressourcen

- [Maven Surefire Plugin](https://maven.apache.org/surefire/)
- [JaCoCo Documentation](https://www.jacoco.org/)
- [AssertJ SoftAssertions](https://assertj.org/)
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [GitLab CI/CD](https://docs.gitlab.com/ee/ci/)
- [Docker Compose](https://docs.docker.com/compose/)

---

**Status**: ✅ Vollständig für Aufgaben 1 & 2

Für Feedback oder weitere Fragen, siehe Dokumentationen im Projekt.
