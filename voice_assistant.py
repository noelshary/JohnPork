import json
import queue
import subprocess
import tempfile
import wave
from pathlib import Path

import sounddevice as sd
from vosk import Model, KaldiRecognizer

BASE = Path(__file__).resolve().parent
CFG = json.loads((BASE / "config.json").read_text(encoding="utf-8"))
MODEL_DIR = BASE / CFG["vosk_model_path"]
OUT_FILE = BASE / "latest_command.txt"
SAMPLE_RATE = int(CFG.get("sample_rate", 16000))
audio_queue = queue.Queue()

COMMANDS = {
    "komm her": ("come", "Ich komme!"),
    "folge mir": ("follow", "Ich folge dir!"),
    "setz dich": ("sit", "Okay, ich setze mich."),
    "sitz": ("sit", "Okay, ich setze mich."),
    "steh auf": ("stand", "Ich stehe wieder auf."),
    "tanz": ("dance", "Na gut, ich tanze!"),
    "warte": ("wait", "Ich warte hier."),
    "hör auf": ("stop", "Alles klar, ich höre auf.")
}

def audio_callback(indata, frames, time_info, status):
    if status:
        print(status)
    audio_queue.put(bytes(indata))

def speak(text):
    exe = CFG["piper_executable"]
    model = str(BASE / CFG["piper_model_path"])
    with tempfile.NamedTemporaryFile(suffix=".wav", delete=False) as tmp:
        wav_path = Path(tmp.name)
    try:
        subprocess.run(
            [exe, "--model", model, "--output_file", str(wav_path)],
            input=text.encode("utf-8"),
            check=True
        )
        with wave.open(str(wav_path), "rb") as wf:
            audio = wf.readframes(wf.getnframes())
            import numpy as np
            samples = np.frombuffer(audio, dtype=np.int16)
            sd.play(samples, wf.getframerate())
            sd.wait()
    finally:
        wav_path.unlink(missing_ok=True)

def main():
    if not MODEL_DIR.exists():
        raise SystemExit(
            f"Vosk-Modell fehlt: {MODEL_DIR}\n"
            "Lade ein deutsches Modell von https://alphacephei.com/vosk/models herunter."
        )

    model = Model(str(MODEL_DIR))
    recognizer = KaldiRecognizer(model, SAMPLE_RATE)
    print("JohnPork Offline-Sprachdemo läuft. Sag z. B. 'JohnPork sitz'.")
    print("Beenden mit Strg+C.")

    with sd.RawInputStream(
        samplerate=SAMPLE_RATE,
        blocksize=8000,
        dtype="int16",
        channels=1,
        callback=audio_callback
    ):
        while True:
            data = audio_queue.get()
            if recognizer.AcceptWaveform(data):
                result = json.loads(recognizer.Result())
                text = result.get("text", "").lower().strip()
                if not text:
                    continue
                print("Erkannt:", text)
                if "johnpork" not in text:
                    continue
                cleaned = text.replace("johnpork", "").strip()
                for phrase, (command, response) in COMMANDS.items():
                    if phrase in cleaned:
                        OUT_FILE.write_text(command, encoding="utf-8")
                        print("Befehl:", command)
                        speak(response)
                        break

if __name__ == "__main__":
    main()
