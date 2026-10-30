# JohnPork Companion – Fabric 1.21.11

Dies ist ein sauberes Fabric-Mod-Grundgeruest fuer Minecraft Java 1.21.11.

## Voraussetzungen
- JDK 21
- Minecraft Java 1.21.11
- Fabric Loader 0.19.x
- Fabric API fuer 1.21.11

## Lokal bauen (Windows)
Installiere JDK 21 und Gradle 9.2.1. Oeffne PowerShell in diesem Ordner und fuehre aus:

```powershell
gradle build
```

Die Mod-JAR wird unter `build/libs/` erstellt.

## Mit GitHub bauen
Lade den gesamten Inhalt dieses Projektordners in das Stammverzeichnis deines GitHub-Repositories hoch. Die Datei `.github/workflows/build.yml` startet den Build bei jedem Push. Danach findest du die JAR unter:
GitHub -> Actions -> erfolgreicher Lauf -> Artifacts -> johnpork-build.

## Aktueller Funktionsumfang
Dieses Paket ist absichtlich ein minimales, sauberes Build-Grundgeruest. Es registriert die Mod und schreibt beim Laden eine Logmeldung. Es enthaelt noch NICHT:
- eine sichtbare 3D-Begleiterin oder Follow-KI
- das NameMC-Skinmodell
- ein Interaktionsmenue oder Animationen
- Simple Voice Chat / Spracherkennung / TTS

Diese Funktionen muessen als naechste Entwicklungsschritte implementiert und getestet werden. Es wird keine fertige Begleiter-Mod vorgetaeuscht.
