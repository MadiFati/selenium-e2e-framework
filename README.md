# selenium-e2e-framework

![Tests](https://github.com/YOUR_USERNAME/selenium-e2e-framework/actions/workflows/selenium-tests.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.18-green)
![TestNG](https://img.shields.io/badge/TestNG-7.9-blue)

Production-ready Selenium framework with Page Object Model, parallel execution, multi-environment config and Allure reporting.

---

## Architecture

```
src/
├── main/java/com/example/
│   ├── config/      # Multi-env config (Owner library)
│   ├── pages/       # Page Objects (BasePage + pages)
│   └── utils/       # DriverManager (thread-safe), helpers
└── test/java/com/example/
    ├── tests/       # Test classes (BaseTest + suites)
    └── data/        # Test data constants
.github/workflows/   # CI/CD pipeline
```

**Key design decisions:**
- `ThreadLocal<WebDriver>` → thread-safe parallel execution out of the box
- `Owner` library → config from env vars, system properties or files (no hardcoded values)
- `@Step` annotations on all page actions → readable Allure reports
- Two TestNG suites: `smoke` (fast, ~2 min) and `regression` (full, ~8 min)

---

## Run locally in 2 commands

```bash
# Clone and run smoke tests
git clone https://github.com/YOUR_USERNAME/selenium-e2e-framework.git
cd selenium-e2e-framework
mvn test -P smoke
```

**Requirements:** Java 17+, Maven 3.8+ — Chrome is downloaded automatically by WebDriverManager.

```bash
# Run headless (like CI)
mvn test -P smoke -DHEADLESS=true

# Run full regression
mvn test -P regression -DHEADLESS=true

# Run against a different environment
mvn test -DBASE_URL=https://staging.myapp.com -DHEADLESS=true
```

---

## Allure report

```bash
# Generate and open report locally
mvn allure:report
mvn allure:serve
```

The report is also deployed automatically to GitHub Pages on each push to `main`.

---

## Stack

| Layer | Tool |
|---|---|
| Browser automation | Selenium 4.18 |
| Driver management | WebDriverManager 5.7 |
| Test framework | TestNG 7.9 |
| Build tool | Maven |
| Reporting | Allure 2.25 |
| Config | Owner |
| CI/CD | GitHub Actions |
| Language | Java 17 |

---

## Author

**[Your Name]** — Senior Test Automation Engineer  
8 years of experience in test automation (Selenium, Java, CI/CD)  
**Fatima El Madini** — Senior Test Automation Engineer  
[LinkedIn](https://www.linkedin.com/in/fatimazahra-el-madini-58352210b/)· [Malt](https://malt.fr/profile/yourprofile)  
Portfolio : [Projet 2](https://github.com/MadiFati/api-ui-testing-combo) · [Projet 3](https://github.com/MadiFati/selenium-audit-toolkit)

