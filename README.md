# 🕹️ BoredGame

**BoredGame** is a JavaFX-based desktop application featuring classic games like Chess and Snakes & Ladders. Built with modular Java and Maven, it follows the MVC architecture and Google Java Style.

---

## 📥 Download & Run

Download the latest release from the [Releases](https://github.com/kasper280403/BoredGame) page.

### ✅ Run the Game

1. Make sure you have **Java 21** installed.
2. Run the app using the terminal:

```bash
java -jar BoredGame.jar
```

---

## 🛠️ Development

### Clone the Repository

```bash
git clone https://github.com/yourusername/BoredGame.git
cd BoredGame
```

### Build the JAR

```bash
mvn clean package
```

The runnable JAR will be created under:

```
target/BoredGame-1.0-SNAPSHOT-shaded.jar
```

---

## 🧪 Run Tests

```bash
mvn test
```

---

## 🧹 Code Style

- Google CheckStyle

## 🧱 Project Structure

```
BoredGame/
├── gameData/
│   └── music/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/
│   │   │       └── ntnu/
│   │   │           └── idi/
│   │   │               └── idattx2002/
│   │   │                   ├── controller/
│   │   │                   │   ├── chess/
│   │   │                   │   ├── common/
│   │   │                   │   └── ladderGame/
│   │   │                   ├── exception/
│   │   │                   ├── io/
│   │   │                   │   ├── chess/
│   │   │                   │   ├── common/
│   │   │                   │   └── ladderGame/
│   │   │                   ├── module/
│   │   │                   │   ├── chess/
│   │   │                   │   │   ├── board/
│   │   │                   │   │   ├── pieces/
│   │   │                   │   │   └── player/
│   │   │                   │   ├── common/
│   │   │                   │   │   ├── board/
│   │   │                   │   │   ├── music/
│   │   │                   │   │   └── player/
│   │   │                   │   └── ladderGame/
│   │   │                   │       ├── board/
│   │   │                   │       ├── dice/
│   │   │                   │       └── player/
│   │   │                   └── view/
│   │   │                       ├── chess/
│   │   │                       ├── common/
│   │   │                       └── ladderGame/
│   │   └── resources/
│   └── test/
├── target/
└── pom.xml

```

---
## 👤 Author

**Sindre Mjøs and Kasper KArlsen**  
_Developed for IDATT2002 at NTNU_  
[GitHub Profile](https://github.com/sindrelm)
[GitHub Profile](https://github.com/kasper280403)


---