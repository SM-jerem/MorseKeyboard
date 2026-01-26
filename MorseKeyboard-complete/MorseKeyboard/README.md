# 📱 Clavier Morse pour Android

Application de clavier personnalisé permettant d'écrire du texte en utilisant le code Morse.

## 🎯 Fonctionnalités

- **Saisie Morse intuitive** : Appui court pour point (.), appui long pour tiret (-)
- **Interface simple** : Grand bouton Morse, boutons espace et validation
- **Retour haptique** : Vibrations à chaque action
- **Prévisualisation en temps réel** : Voir le texte avant validation
- **Table Morse complète** : A-Z, 0-9, ponctuation courante
- **Tolérance d'erreur** : Les combinaisons inconnues deviennent "?"

## 📂 Structure du projet

```
MorseKeyboard/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/morsekeyboard/
│           │   ├── MainActivity.kt
│           │   └── MorseKeyboardService.kt
│           └── res/
│               ├── layout/
│               │   ├── activity_main.xml
│               │   └── keyboard.xml
│               ├── values/
│               │   └── strings.xml
│               └── xml/
│                   └── method.xml
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## 🔧 Compilation et Installation

### Prérequis

- Android Studio Hedgehog ou supérieur
- Android SDK 26+ (Android 8.0)
- JDK 17

### Étapes de compilation

1. **Cloner ou importer le projet**
   ```bash
   # Ouvrir Android Studio
   # File → Open → Sélectionner le dossier MorseKeyboard
   ```

2. **Synchroniser Gradle**
   - Android Studio synchronisera automatiquement les dépendances
   - Ou cliquer sur "Sync Project with Gradle Files"

3. **Compiler l'APK**
   - Menu : Build → Build Bundle(s) / APK(s) → Build APK(s)
   - L'APK sera généré dans : `app/build/outputs/apk/debug/app-debug.apk`

4. **Installer sur le téléphone**
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

### Compilation en ligne de commande

```bash
# Dans le répertoire du projet
./gradlew assembleDebug

# L'APK se trouve dans :
# app/build/outputs/apk/debug/app-debug.apk
```

## 📱 Activation du clavier

### Étape 1 : Activer le clavier dans les paramètres

1. Ouvrir l'application "Clavier Morse"
2. Appuyer sur "Ouvrir les Paramètres"
3. Ou manuellement : **Paramètres → Système → Langues et saisie → Clavier virtuel → Gérer les claviers**
4. Activer **"Clavier Morse"**

### Étape 2 : Sélectionner le clavier

1. Ouvrir n'importe quelle application (SMS, Notes, WhatsApp, etc.)
2. Appuyer sur un champ de texte
3. Appuyer sur l'icône de clavier (en bas à droite)
4. Sélectionner **"Clavier Morse"**

## ⌨️ Utilisation

### Interface du clavier

```
┌─────────────────────────────────────┐
│  Buffer Morse : .- .- .-            │
├─────────────────────────────────────┤
│  Prévisualisation : AAA             │
├─────────────────────────────────────┤
│                                     │
│          MORSE                      │
│    (Appui court/long)               │
│                                     │
├─────────────────────────────────────┤
│  ESPACE  │  VALIDER  │  ⌫          │
└─────────────────────────────────────┘
```

### Actions

- **Bouton MORSE** :
  - **Appui court (< 250ms)** → Ajoute un point (.)
  - **Appui long (≥ 250ms)** → Ajoute un tiret (-)

- **Bouton ESPACE** : Sépare les lettres en Morse

- **Bouton VALIDER** : Convertit le Morse en texte et l'insère

- **Bouton ⌫** : Efface le dernier symbole

### Exemple de saisie

Pour écrire "HELLO" :

1. **H** : `....` 
   - 4 appuis courts → `....` → Espace
2. **E** : `.`
   - 1 appui court → `.` → Espace
3. **L** : `.-..`
   - Court, Long, Court, Court → `.-..` → Espace
4. **L** : `.-..`
   - Court, Long, Court, Court → `.-..` → Espace
5. **O** : `---`
   - 3 appuis longs → `---` → Valider

Résultat : "HELLO" apparaît dans le champ de texte

## 📊 Table Morse complète

### Lettres
```
A .-      B -...    C -.-.    D -..     E .       F ..-.
G --.     H ....    I ..      J .---    K -.-     L .-..
M --      N -.      O ---     P .--.    Q --.-    R .-.
S ...     T -       U ..-     V ...-    W .--     X -..-
Y -.--    Z --..
```

### Chiffres
```
0 -----   1 .----   2 ..---   3 ...--   4 ....-
5 .....   6 -....   7 --...   8 ---..   9 ----.
```

### Ponctuation
```
? ..--..  ! -.-.--  . .-.-.-  , --..--  : ---.--
/ -..-.   - -....-  ' .----.  " .-..-.  + .-.-.
= -...-   ( -.--.   ) -.--.-
```

## 🧪 Tests

Testez le clavier dans différentes applications :

- ✅ **SMS / Messages**
- ✅ **WhatsApp**
- ✅ **Notes / Bloc-notes**
- ✅ **Navigateur web** (champs de recherche, formulaires)
- ✅ **Email**
- ✅ **Réseaux sociaux**

## 🔧 Configuration technique

### Seuil court/long

Le seuil par défaut est de **250ms**. Pour le modifier :

```kotlin
// Dans MorseKeyboardService.kt, ligne ~30
private val longPressThreshold = 250L  // Modifier cette valeur
```

### Vibrations

- **Vibration courte** (30ms) : Point/Tiret/Espace/Effacer
- **Vibration longue** (100ms) : Validation

Pour désactiver les vibrations, commentez les lignes dans :
```kotlin
private fun vibrateShort() { ... }
private fun vibrateLong() { ... }
```

## 🐛 Dépannage

### Le clavier n'apparaît pas dans les paramètres

1. Vérifier que l'application est installée
2. Redémarrer le téléphone
3. Réinstaller l'application

### Le clavier ne se sélectionne pas

1. S'assurer qu'il est activé dans les paramètres
2. Essayer de changer de clavier avec l'icône en bas à droite du clavier actuel
3. Ou via la notification de méthode de saisie

### Les symboles ne s'affichent pas correctement

- Vérifier que vous séparez bien les lettres avec le bouton ESPACE
- Chaque lettre doit être séparée par au moins un espace

### Le texte n'est pas inséré

- Vérifier que vous appuyez sur VALIDER après avoir saisi le Morse
- Certaines applications peuvent bloquer les claviers personnalisés (ex: champs de mot de passe)

## 🎨 Personnalisation

### Modifier les couleurs

Éditez `app/src/main/res/layout/keyboard.xml` :

```xml
<!-- Fond du clavier -->
android:background="#1E1E1E"

<!-- Bouton Morse -->
android:background="#3A7CA5"

<!-- Bouton Valider -->
android:background="#2E7D32"

<!-- Bouton Effacer -->
android:background="#C62828"
```

### Modifier la taille des boutons

```xml
<!-- Hauteur du bouton Morse -->
android:layout_height="180dp"

<!-- Hauteur des boutons secondaires -->
android:layout_height="70dp"
```

## 📦 Versions

- **v1.0** : Version initiale
  - Détection appui court/long
  - Table Morse complète (A-Z, 0-9, ponctuation)
  - Vibrations
  - Prévisualisation temps réel
  - Interface sombre

## 🔒 Permissions

L'application nécessite uniquement :
- **VIBRATE** : Pour le retour haptique
- **BIND_INPUT_METHOD** : Requis pour tous les claviers personnalisés

## 📄 Licence

Projet open-source pour usage éducatif.

## 🤝 Contribution

Améliorations possibles :
- [ ] Mode d'entraînement avec statistiques
- [ ] Sons personnalisables (bips)
- [ ] Réglage du seuil court/long dans l'interface
- [ ] Mode automatique (détection automatique de fin de lettre)
- [ ] Dictionnaire de mots courants
- [ ] Thèmes personnalisables
- [ ] Support tablette avec layout adapté
- [ ] Statistiques de vitesse de frappe
- [ ] Mode nuit/jour
- [ ] Support iOS

## 📞 Support

Pour toute question ou problème :
1. Vérifier la section Dépannage
2. Consulter la table Morse
3. Tester dans différentes applications

---

**Bon amusement avec le clavier Morse ! 📟**
