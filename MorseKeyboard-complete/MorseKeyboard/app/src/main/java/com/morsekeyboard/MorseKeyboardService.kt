package com.morsekeyboard

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView

/**
 * Service de clavier personnalisé pour saisie en code Morse
 * Gère la détection des appuis courts/longs et la conversion Morse → Texte
 */
class MorseKeyboardService : InputMethodService() {

    // Vues du clavier
    private lateinit var morseButton: Button
    private lateinit var spaceButton: Button
    private lateinit var enterButton: Button
    private lateinit var deleteButton: Button
    private lateinit var morseDisplay: TextView
    private lateinit var previewDisplay: TextView

    // Buffer Morse (symboles . et -)
    private val morseBuffer = StringBuilder()

    // Vibration
    private lateinit var vibrator: Vibrator

    // Durée pour différencier court/long (en millisecondes)
    private val longPressThreshold = 250L

    // Temps du début de l'appui
    private var pressStartTime = 0L

    // Table de conversion Morse → Caractère
    private val morseTable = mapOf(
        // Lettres
        ".-" to "A", "-..." to "B", "-.-." to "C", "-.." to "D",
        "." to "E", "..-." to "F", "--." to "G", "...." to "H",
        ".." to "I", ".---" to "J", "-.-" to "K", ".-.." to "L",
        "--" to "M", "-." to "N", "---" to "O", ".--." to "P",
        "--.-" to "Q", ".-." to "R", "..." to "S", "-" to "T",
        "..-" to "U", "...-" to "V", ".--" to "W", "-..-" to "X",
        "-.--" to "Y", "--.." to "Z",
        
        // Chiffres
        "-----" to "0", ".----" to "1", "..---" to "2", "...--" to "3",
        "....-" to "4", "....." to "5", "-...." to "6", "--..." to "7",
        "---.." to "8", "----." to "9",
        
        // Ponctuation
        "..--.." to "?", "-.-.--" to "!", ".-.-.-" to ".", "--..--" to ",",
        "---..." to ":", "-..-." to "/", "-....-" to "-", ".----." to "'",
        ".-..-." to "\"", ".-.-." to "+", "-...-" to "=", "-.--." to "(",
        "-.--.-" to ")"
    )

    /**
     * Appelé lors de la création du clavier
     */
    override fun onCreateInputView(): View {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.keyboard, null)

        // Initialiser le vibrator
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Récupérer les vues
        morseButton = view.findViewById(R.id.morseButton)
        spaceButton = view.findViewById(R.id.spaceButton)
        enterButton = view.findViewById(R.id.enterButton)
        deleteButton = view.findViewById(R.id.deleteButton)
        morseDisplay = view.findViewById(R.id.morseDisplay)
        previewDisplay = view.findViewById(R.id.previewDisplay)

        setupMorseButton()
        setupSpaceButton()
        setupEnterButton()
        setupDeleteButton()

        return view
    }

    /**
     * Configuration du bouton Morse principal
     * Détecte les appuis courts (point) et longs (tiret)
     */
    private fun setupMorseButton() {
        morseButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Enregistrer le moment de l'appui
                    pressStartTime = System.currentTimeMillis()
                    true
                }
                MotionEvent.ACTION_UP -> {
                    // Calculer la durée de l'appui
                    val pressDuration = System.currentTimeMillis() - pressStartTime
                    
                    // Déterminer si c'est un point ou un tiret
                    val symbol = if (pressDuration < longPressThreshold) "." else "-"
                    
                    // Ajouter au buffer
                    morseBuffer.append(symbol)
                    
                    // Vibration courte
                    vibrateShort()
                    
                    // Mettre à jour l'affichage
                    updateDisplay()
                    
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Configuration du bouton Espace
     * Sépare les lettres en Morse
     */
    private fun setupSpaceButton() {
        spaceButton.setOnClickListener {
            morseBuffer.append(" ")
            vibrateShort()
            updateDisplay()
        }
    }

    /**
     * Configuration du bouton Valider
     * Convertit le Morse en texte et l'envoie à l'application
     */
    private fun setupEnterButton() {
        enterButton.setOnClickListener {
            if (morseBuffer.isNotEmpty()) {
                val text = convertMorseToText(morseBuffer.toString())
                
                // Envoyer le texte à l'application
                currentInputConnection?.commitText(text, 1)
                
                // Vibration de confirmation
                vibrateLong()
                
                // Réinitialiser le buffer
                morseBuffer.clear()
                updateDisplay()
            }
        }
    }

    /**
     * Configuration du bouton Effacer
     * Supprime le dernier caractère du buffer
     */
    private fun setupDeleteButton() {
        deleteButton.setOnClickListener {
            if (morseBuffer.isNotEmpty()) {
                morseBuffer.deleteCharAt(morseBuffer.length - 1)
                vibrateShort()
                updateDisplay()
            }
        }
    }

    /**
     * Convertit une chaîne Morse en texte
     * @param morse Chaîne contenant des points, tirets et espaces
     * @return Texte traduit
     */
    private fun convertMorseToText(morse: String): String {
        val result = StringBuilder()
        
        // Diviser par espaces pour obtenir les lettres individuelles
        val letters = morse.trim().split(" ")
        
        for (letter in letters) {
            if (letter.isEmpty()) continue
            
            // Chercher dans la table Morse
            val char = morseTable[letter]
            
            if (char != null) {
                result.append(char)
            } else {
                // Si la combinaison est inconnue, ajouter "?"
                result.append("?")
            }
        }
        
        return result.toString()
    }

    /**
     * Met à jour l'affichage du buffer et de la prévisualisation
     */
    private fun updateDisplay() {
        // Afficher le buffer Morse brut
        morseDisplay.text = if (morseBuffer.isEmpty()) {
            "Appuyez sur le bouton Morse"
        } else {
            morseBuffer.toString()
        }
        
        // Afficher la prévisualisation du texte
        if (morseBuffer.isNotEmpty()) {
            val preview = convertMorseToText(morseBuffer.toString())
            previewDisplay.text = "Prévisualisation : $preview"
        } else {
            previewDisplay.text = ""
        }
    }

    /**
     * Vibration courte (pour point/tiret)
     */
    private fun vibrateShort() {
        if (vibrator.hasVibrator()) {
            val effect = VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
        }
    }

    /**
     * Vibration longue (pour validation)
     */
    private fun vibrateLong() {
        if (vibrator.hasVibrator()) {
            val effect = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
        }
    }

    /**
     * Appelé lors de la destruction du clavier
     */
    override fun onDestroy() {
        super.onDestroy()
        morseBuffer.clear()
    }
}
