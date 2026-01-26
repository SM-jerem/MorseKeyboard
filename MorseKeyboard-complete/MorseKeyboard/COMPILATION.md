# 🚀 Guide de Compilation Rapide

## Option 1 : Avec Android Studio (Recommandé)

### Installation
1. Télécharger et installer [Android Studio](https://developer.android.com/studio)
2. Installer Android SDK (via Android Studio)

### Compilation
1. Ouvrir Android Studio
2. **File → Open** → Sélectionner le dossier `MorseKeyboard`
3. Attendre la synchronisation Gradle (première fois peut prendre 5-10 minutes)
4. **Build → Build Bundle(s) / APK(s) → Build APK(s)**
5. Une fois terminé, cliquer sur **locate** dans la notification
6. L'APK est dans : `app/build/outputs/apk/debug/app-debug.apk`

### Installation sur téléphone
1. Connecter le téléphone en USB avec mode débogage activé
2. Ou transférer l'APK et l'installer manuellement

---

## Option 2 : Ligne de commande (Linux/Mac)

### Prérequis
```bash
# Installer JDK 17
sudo apt install openjdk-17-jdk  # Ubuntu/Debian
brew install openjdk@17           # macOS

# Télécharger Android Command Line Tools
# https://developer.android.com/studio#command-tools
```

### Configuration SDK
```bash
# Définir les variables d'environnement
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
export PATH=$PATH:$ANDROID_HOME/platform-tools

# Installer les packages SDK nécessaires
sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
```

### Compilation
```bash
cd MorseKeyboard

# Donner les droits d'exécution au wrapper Gradle
chmod +x gradlew

# Compiler l'APK
./gradlew assembleDebug

# L'APK est créé dans :
# app/build/outputs/apk/debug/app-debug.apk
```

### Installation
```bash
# Installer via ADB
adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## Option 3 : Ligne de commande (Windows)

### Prérequis
1. Installer [JDK 17](https://adoptium.net/)
2. Télécharger [Android Command Line Tools](https://developer.android.com/studio#command-tools)

### Configuration
```cmd
REM Définir ANDROID_HOME
set ANDROID_HOME=C:\Users\VotreNom\AppData\Local\Android\Sdk
set PATH=%PATH%;%ANDROID_HOME%\cmdline-tools\latest\bin
set PATH=%PATH%;%ANDROID_HOME%\platform-tools

REM Installer SDK
sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
```

### Compilation
```cmd
cd MorseKeyboard

REM Compiler
gradlew.bat assembleDebug

REM APK dans : app\build\outputs\apk\debug\app-debug.apk
```

---

## 🔧 Résolution des problèmes courants

### Erreur : "SDK location not found"
**Solution** : Créer `local.properties` dans le dossier racine :
```properties
sdk.dir=/chemin/vers/Android/Sdk
```

### Erreur : "JAVA_HOME is not set"
**Solution** :
```bash
# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-17
```

### Erreur de synchronisation Gradle
**Solution** :
```bash
# Nettoyer et reconstruire
./gradlew clean
./gradlew build
```

### Téléchargement lent des dépendances
**Solution** : Utiliser un miroir Maven plus proche :
```gradle
// Dans build.gradle (racine)
repositories {
    maven { url 'https://maven.aliyun.com/repository/google' }
    maven { url 'https://maven.aliyun.com/repository/public' }
    google()
    mavenCentral()
}
```

---

## 📲 Installation de l'APK sur le téléphone

### Méthode 1 : Via ADB (Câble USB)
```bash
# Activer le débogage USB sur le téléphone
# Paramètres → Options développeur → Débogage USB

# Vérifier la connexion
adb devices

# Installer
adb install app-debug.apk
```

### Méthode 2 : Transfert direct
1. Copier `app-debug.apk` sur le téléphone (USB, Bluetooth, email, etc.)
2. Sur le téléphone, ouvrir le gestionnaire de fichiers
3. Appuyer sur `app-debug.apk`
4. Autoriser l'installation depuis des sources inconnues si demandé
5. Appuyer sur "Installer"

### Méthode 3 : Via Google Drive
1. Uploader `app-debug.apk` sur Google Drive
2. Sur le téléphone, télécharger depuis Drive
3. Ouvrir et installer

---

## ✅ Vérification de l'installation

1. Ouvrir l'application "Clavier Morse"
2. Appuyer sur "Ouvrir les Paramètres"
3. Activer le clavier
4. Tester dans n'importe quelle application

---

## 🎯 Compilation pour production (APK signé)

### Créer une clé de signature
```bash
keytool -genkey -v -keystore release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias morse-key
```

### Configurer la signature
Ajouter dans `app/build.gradle` :
```gradle
android {
    signingConfigs {
        release {
            storeFile file("../release-key.jks")
            storePassword "votre-mot-de-passe"
            keyAlias "morse-key"
            keyPassword "votre-mot-de-passe"
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

### Compiler la version release
```bash
./gradlew assembleRelease
# APK dans : app/build/outputs/apk/release/app-release.apk
```

---

## 📊 Tailles approximatives

- **APK Debug** : ~2-3 MB
- **APK Release** : ~1-2 MB (avec ProGuard)
- **Temps de compilation** : 1-3 minutes (première fois : 5-10 minutes)

---

**Bonne compilation ! 🎉**
