# JohnPork Companion – Prototyp (Minecraft Java 1.21.11)

Dies ist ein **Quellcode-Prototyp**, noch keine fertige spielbare Begleiter-Mod.
Enthalten sind:
- Fabric-Mod-Projekt für Minecraft 1.21.11 / Java 21
- ein kleines Einstellungs-/Aktionsmenü (Taste J)
- ein lokaler Python-Sprachdemo-Dienst mit Vosk (STT) und Piper (TTS)

## Ehrlicher Funktionsstand
Noch NICHT enthalten:
- kein sichtbares 3D-Begleiterinnen-Modell und keine echte Follow-KI
- der NameMC-Skin `f867801af478bded` ist noch nicht als Textur eingebaut
- die Aktionen im Menü sind derzeit UI-Prototyp-Aktionen und steuern noch keine NPC-Entity
- der Python-Sprachdienst läuft separat und ist noch nicht mit dem Minecraft-Client oder Simple Voice Chat verbunden
- TTS wird lokal über den PC-Lautsprecher ausgegeben, nicht als Simple-Voice-Chat-Spieler-Audio übertragen

Ich kennzeichne das ausdrücklich, damit du nicht eine unfertige JAR für eine fertige Mod hältst.

## Build-Status
Die ZIP wurde als Quellcode-Paket erstellt, aber in dieser Umgebung nicht gegen Minecraft kompiliert oder im Spiel getestet. Daher ist sie **keine geprüfte JAR**. Die Versionswerte sind auf Fabric/Minecraft 1.21.11 ausgerichtet.

## Voraussetzungen
- Minecraft Java 1.21.11
- Fabric Loader (aktuelle stabile Version)
- Fabric API passend zu 1.21.11
- Java/JDK 21
- Gradle 8.14 oder neuer (wenn du keinen Gradle-Wrapper verwendest)

## Mod bauen
1. Installiere JDK 21 und Gradle.
2. Öffne diesen Ordner im Terminal.
3. Führe aus:
   - Windows: `gradle build`
   - Linux/macOS: `gradle build`
4. Die JAR liegt danach unter `build/libs/`.
5. Lege die JAR zusammen mit Fabric API in `.minecraft/mods`.

Das Menü öffnest du im Spiel mit **J**.

## Offline-Sprachdemo (separat)
Getestete Architektur: Vosk kann offline Sprache erkennen und stellt u. a. deutsche Modelle bereit. Piper kann lokal Text in Sprache umwandeln. Die benötigten Modelle müssen separat geladen werden.

### Python installieren und Pakete einrichten
```bash
cd voice
python -m venv .venv
# Windows:
.venv\Scripts\activate
# Linux/macOS:
source .venv/bin/activate
pip install -r requirements.txt
```

Lade ein deutsches Vosk-Modell von https://alphacephei.com/vosk/models herunter und entpacke es z. B. nach `voice/models/vosk-model-small-de-0.15`.

Installiere Piper und eine deutsche Piper-Stimme nach der Anleitung unter https://github.com/rhasspy/piper .
Trage die Pfade in `voice/config.json` ein.

Starte:
```bash
python voice_assistant.py
```

Der Sprachdienst erkennt einfache Befehle wie „JohnPork komm her“, „JohnPork sitz“ und „JohnPork tanz“ und spricht eine lokale Antwort. In dieser Prototyp-Version werden die Befehle nur in `latest_command.txt` geschrieben; Minecraft führt sie noch nicht aus.

## Simple Voice Chat
Simple Voice Chat muss auf Client und Server installiert sein, wenn du es im Mehrspieler nutzen willst. Die API bietet eine Plugin-Schnittstelle; eine echte Audioeinspeisung von JohnPorks TTS in den Voice-Chat ist in diesem Prototyp noch nicht implementiert. Dafür muss die Audioausgabe als eigener Voice-Chat-Audiokanal integriert und mit deiner konkreten SVC-Version getestet werden.

API-Dokumentation: https://dev.modrepo.de/minecraft/voicechat/api/getting_started

## Skin
Vorgesehene Skin-Referenz:
https://de.namemc.com/skin/f867801af478bded

Die Skin-Textur wird nicht automatisch von NameMC heruntergeladen. Bitte beachte außerdem die Nutzungsrechte des Skin-Erstellers.
