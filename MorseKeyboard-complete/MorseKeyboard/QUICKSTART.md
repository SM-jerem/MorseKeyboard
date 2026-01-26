# ⚡ Démarrage Rapide - Clavier Morse

## 🎯 En 3 étapes

### 1️⃣ Compiler l'application
```bash
# Avec Android Studio
File → Open → MorseKeyboard → Build → Build APK

# Ou en ligne de commande
cd MorseKeyboard
./gradlew assembleDebug
```

### 2️⃣ Installer sur le téléphone
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### 3️⃣ Activer et utiliser
1. Ouvrir "Clavier Morse" sur le téléphone
2. Appuyer sur "Ouvrir les Paramètres"
3. Activer le clavier
4. L'utiliser dans n'importe quelle application !

---

## 📋 Checklist Complète

### Avant de commencer
- [ ] JDK 17 installé
- [ ] Android Studio installé (ou Android SDK)
- [ ] Téléphone Android 8.0+ disponible
- [ ] Câble USB ou autre moyen de transfert

### Compilation
- [ ] Projet ouvert dans Android Studio
- [ ] Gradle synchronisé
- [ ] APK compilé avec succès
- [ ] APK localisé dans `app/build/outputs/apk/debug/`

### Installation
- [ ] APK transféré sur le téléphone
- [ ] Installation autorisée (sources inconnues)
- [ ] Application "Clavier Morse" visible dans les apps

### Configuration
- [ ] Paramètres ouverts depuis l'app
- [ ] Clavier Morse activé dans la liste
- [ ] Avertissement de sécurité accepté

### Test
- [ ] Application de test ouverte (Messages, Notes, etc.)
- [ ] Clavier Morse sélectionné
- [ ] Test d'écriture "SOS" réussi
- [ ] Vibrations fonctionnelles
- [ ] Texte correctement inséré

---

## 🚨 Problèmes Courants

### ❌ Gradle sync failed
**Solution** : Vérifier la connexion Internet, attendre et réessayer

### ❌ SDK not found
**Solution** : Créer `local.properties` avec `sdk.dir=/chemin/vers/sdk`

### ❌ Le clavier n'apparaît pas
**Solution** : Redémarrer le téléphone après installation

### ❌ Impossible d'activer le clavier
**Solution** : Vérifier les permissions dans Paramètres → Apps → Clavier Morse

---

## 📚 Documentation Complète

Pour plus de détails, consultez :

- 📖 **README.md** : Documentation générale
- 🔨 **COMPILATION.md** : Guide de compilation détaillé
- 👤 **GUIDE_UTILISATEUR.md** : Mode d'emploi complet
- 🏗️ **ARCHITECTURE.md** : Architecture technique

---

## 🎮 Premier Test

### Écrire "SOS"

1. **S** = `...`
   - Tap rapide × 3
   - Appuyer sur ESPACE

2. **O** = `---`
   - Maintenir × 3
   - Appuyer sur ESPACE

3. **S** = `...`
   - Tap rapide × 3
   - Appuyer sur VALIDER

✅ "SOS" doit apparaître !

---

## 🎯 Mémo Morse Rapide

```
Lettres simples:
E = .
T = -
A = .-
I = ..
N = -.
S = ...
O = ---

Chiffres:
1 = .----
2 = ..---
5 = .....
0 = -----

Ponctuation:
? = ..--..
! = -.-.--
```

---

## ⚙️ Configuration Recommandée

### Android Studio
- Version : Hedgehog (2023.1.1) ou supérieur
- Plugins : Kotlin, Android

### Téléphone
- OS : Android 8.0 minimum
- RAM : 2 GB minimum
- Stockage : 50 MB libre

---

## 🏆 Objectifs de Test

### Niveau 1 : Basique
- [ ] Écrire "HELLO"
- [ ] Écrire "TEST"
- [ ] Écrire votre prénom

### Niveau 2 : Intermédiaire
- [ ] Écrire une phrase avec ponctuation
- [ ] Utiliser dans WhatsApp
- [ ] Utiliser dans un navigateur

### Niveau 3 : Avancé
- [ ] Conversation complète en Morse
- [ ] Test dans 5 applications différentes
- [ ] Personnaliser le seuil court/long

---

## 📞 Support

En cas de problème :

1. Consulter la section Dépannage du README
2. Vérifier l'ARCHITECTURE.md pour les détails techniques
3. Relire le GUIDE_UTILISATEUR.md pour l'utilisation

---

## 🎉 Prêt à Morse-er !

Vous avez maintenant tout ce qu'il faut pour :
- ✅ Compiler l'application
- ✅ L'installer sur votre téléphone
- ✅ L'activer et l'utiliser
- ✅ Écrire en Morse comme un pro !

**Bon code ! 📟**

---

### Temps estimés

| Étape | Durée |
|-------|-------|
| Installation Android Studio | 30 min |
| Configuration SDK | 15 min |
| Compilation première fois | 10 min |
| Compilations suivantes | 2 min |
| Installation sur téléphone | 2 min |
| Activation du clavier | 2 min |
| Premier test | 5 min |
| **TOTAL** | **~1h** |

---

### Fichiers générés

```
MorseKeyboard/
├── app/build/outputs/apk/debug/
│   └── app-debug.apk          ← Fichier à installer
└── app/build/intermediates/
    └── ... (fichiers temporaires)
```

**Fichier important** : `app-debug.apk` (~2-3 MB)

---

**C'est parti ! 🚀**
