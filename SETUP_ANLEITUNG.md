# 🚀 Cyberware Mod - Setup & Build Anleitung

## Phase 1: Voraussetzungen & Umgebung

### Benötigte Software:
1. **Java Development Kit (JDK) 17+**
   - Download: https://www.oracle.com/java/technologies/downloads/
   - WICHTIG: Version 17 oder höher!
   - Speicherpfad merken (z.B. `C:\Program Files\Java\jdk-17`)

2. **Gradle** (optional, wird mit Scripts automatisch installiert)

3. **IDE** (empfohlen):
   - IntelliJ IDEA Community Edition (kostenlos)
   - Oder: Eclipse IDE
   - Oder: Visual Studio Code + Java Extensions

### Environment Variables (Windows):
```
JAVA_HOME = C:\Program Files\Java\jdk-17
PATH = %JAVA_HOME%\bin;%PATH%
```

### Environment Variables (Linux/Mac):
```bash
export JAVA_HOME=/path/to/jdk-17
export PATH=$JAVA_HOME/bin:$PATH
```

## Phase 2: Projekt-Struktur einrichten

### Ordnerstruktur erstellen:
```
cyberware-mod/
├── .gradle/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── cyberware/
│   │   │           ├── CyberwareMod.java
│   │   │           ├── block/
│   │   │           ├── item/
│   │   │           ├── entity/
│   │   │           ├── capabilities/
│   │   │           └── network/
│   │   └── resources/
│   │       ├── META-INF/
│   │       │   └── mods.toml
│   │       └── assets/
│   │           └── cyberware/
│   │               └── lang/
│   │                   ├── en_us.json
│   │                   └── de_de.json
│   └── test/
├── build.gradle
├── settings.gradle
└── README.md
```

### Datei: `settings.gradle`
```gradle
pluginManagement {
    repositories {
        maven { url = 'https://maven.minecraftforge.net' }
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = 'cyberware'
```

### Datei: `gradle.properties`
```gradle
# Gradle Settings
org.gradle.daemon=false
org.gradle.parallel=true
org.gradle.workers.max=4

# Minecraft & Forge
minecraft_version=1.20.1
forge_version=47.2.0
jdk_version=17
```

## Phase 3: Projekt in IDE importieren

### IntelliJ IDEA:
1. `File → Open` → Ordner `cyberware-mod/` wählen
2. "Trust Project" klicken wenn gefragt
3. Gradle lädt automatisch Dependencies
4. Warten bis Index-Erstellung fertig ist (~5 Minuten)

### Eclipse:
1. `File → Import → Gradle → Existing Gradle Project`
2. Root-Ordner wählen
3. Finish klicken

### VS Code:
1. Ordner in VS Code öffnen
2. Extension Pack for Java installieren
3. Gradle ist automatisch integriert

## Phase 4: Kompilieren & Builden

### Mit Gradle Wrapper (Windows):
```bash
cd cyberware-mod
gradlew.bat build
```

### Mit Gradle Wrapper (Linux/Mac):
```bash
cd cyberware-mod
chmod +x gradlew
./gradlew build
```

### Direktes kompilieren:
```bash
gradle build
```

### Nur JAR erstellen (schneller):
```bash
gradle jar
```

### Ausgabe:
```
✅ JAR-Datei: cyberware-mod/build/libs/cyberware-1.0.0.jar
```

## Phase 5: Development Environment Setup

### Minecraft Launch Profile erstellen:

**IntelliJ IDEA:**
1. `Run → Edit Configurations`
2. `+ → Application` hinzufügen
3. Main class: `cpw.mods.bootstrapfx.BootstrapFXMain`
4. VM options: `-DignoreList=bootstrapfx-shade.jar -DmergeModules=true`
5. Working directory: `cyberware-mod/run`
6. Classpath: `cyberware.main`
7. Module: `cyberware.main`

### Im Spiel starten:
1. IDE: Run Button drücken
2. Oder Terminal: `gradlew runClient`
3. Minecraft startet mit der Mod

## Phase 6: Testing & Debugging

### Breakpoints setzen:
1. Im Code Zeile klicken um Breakpoint zu setzen
2. Debug starten (nicht Run!)
3. Code pausiert bei Breakpoint

### Logs anschauen:
```
Pfad: cyberware-mod/run/logs/latest.log
```

### Häufige Fehler:

**Error: JAVA_HOME nicht gesetzt**
```
→ Environment Variable prüfen
→ IDE neu starten
```

**Error: Forge Version nicht gefunden**
```
→ Internetverbindung prüfen
→ build.gradle Forge-Version überprüfen
```

**Error: Gradle-Daemon Fehler**
```bash
gradlew --stop
```

## Phase 7: Custom Code hinzufügen

### Neue Item-Klasse:
```java
package com.cyberware.item;

import net.minecraft.world.item.Item;

public class CustomItem extends Item {
    public CustomItem(Item.Properties properties) {
        super(properties);
    }
}
```

### Registrieren in ModItems.java:
```java
public static final RegistryObject<Item> CUSTOM_ITEM = ITEMS.register("custom_item",
    () -> new CustomItem(new Item.Properties()));
```

### In Spiel sichtbar:
```json
// en_us.json hinzufügen
"item.cyberware.custom_item": "Custom Item"
```

## Phase 8: Mod in Minecraft installieren

### Fertige JAR in Minecraft:
```
1. Datei: cyberware-mod/build/libs/cyberware-1.0.0.jar
2. Kopieren zu: %appdata%/.minecraft/mods/ (Windows)
   oder ~/.minecraft/mods/ (Linux/Mac)
3. Minecraft neustarten
4. Mod ist sichtbar!
```

## Phase 9: Deployment & Sharing

### JAR signieren (optional):
```bash
jarsigner -keystore keystore.jks cyberware-1.0.0.jar alias
```

### Für CurseForge hochladen:
1. Auf CurseForge.com anmelden
2. Projekt erstellen
3. JAR hochladen
4. Versionsinfo ausfüllen
5. Publish!

## 🐛 Troubleshooting

| Problem | Lösung |
|---------|--------|
| `Error: Could not find matching toolchain` | JDK 17 installieren, JAVA_HOME setzen |
| `Gradle Sync fehlgeschlagen` | `gradlew clean build` probieren |
| `Mod lädt nicht im Spiel` | mods.toml überprüfen, JAR in mods-Ordner |
| `Keine Klassen-Autocomplete` | IDE re-indexieren (IntelliJ: File → Invalidate Caches) |
| `Zu viel RAM-Verbrauch` | `gradle.properties`: `org.gradle.workers.max=2` |

## 📚 Zusätzliche Ressourcen

- **Forge Dokumentation:** https://docs.minecraftforge.net/
- **Forge Discord:** https://discord.gg/UvedJ9m
- **Minecraft Wiki:** https://minecraft.wiki/

## 🎯 Nächste Schritte

1. ✅ Environment aufsetzen
2. ✅ Projekt importieren
3. ✅ Kompilieren & Testen
4. 🔄 **Eigene Features hinzufügen**
5. 🔄 Texture & Models erstellen
6. 🔄 Bei CurseForge hochladen

---

**Viel Spaß beim Modden! 🎮⚡**

Fragen? → Minecraft Forge Discord oder GitHub Issues

