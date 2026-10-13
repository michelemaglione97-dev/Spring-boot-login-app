# 🍃 Spring Boot App - Gestione Dipendenti e Autenticazione

Applicazione Web Full-Stack sviluppata in Java con **Spring Boot**, **Spring Data JPA** e **MySQL**. Il progetto implementa un sistema di autenticazione, gestione delle ruoli/permessi dei dipendenti e pannello di amministrazione.

## 🛠️ Tecnologie Utilizzate
* **Backend:** Java 21, Spring Boot, Spring Data JPA, Hibernate
* **Frontend:** HTML5, CSS3, Thymeleaf
* **Database:** MySQL
* **Build Tool:** Maven

## 📋 Architettura dei Pacchetti
Il progetto è strutturato seguendo il pattern MVC e una pulita separazione delle responsabilità:
- `com.example.springstep1.controller`: Gestione delle rotte e delle richieste HTTP (`PageController`)
- `com.example.springstep1.services`: Logica di business dell'applicazione (`DipendenteService`)
- `com.example.springstep1.repositories`: Interfacce di persistenza con Spring Data JPA (`IDipendenteRepository`)
- `com.example.springstep1.entities`: Modelli di dominio e ORM (`Dipendente`, `Account`, `Persona`, `Ruolo`, `Permesso`)
- `com.example.springstep1.dto`: Data Transfer Object per la gestione dei moduli (`DtoDipendente`)
- `com.example.springstep1.exceptions`: Gestione centralizzata delle eccezioni (`GlobalExceptionHandler`)
- `com.example.springstep1.utility`: Classi helper e validazione dati (`Validation`)

## 🚀 Come Eseguire il Progetto in Locale
1. **Clona il repository:**
   ```bash
   git clone [https://github.com/michelemaglione97-dev/Spring-boot-login-app.git](https://github.com/michelemaglione97-dev/Spring-boot-login-app.git)

2. **Configura il Database MySQL:**
* Crea un database MySQL sul tuo server locale.
* Configura le credenziali di accesso nel file `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_tuo_db
spring.datasource.username=tuo_username
spring.datasource.password=tua_password

```




3. **Avvia l'applicazione:**
* Esegui la classe principale `Springstep1Application.java` da Eclipse / IDE come *Spring Boot App*.


4. **Accedi all'app:**
* Apri il browser e vai su `http://localhost:8080`.















