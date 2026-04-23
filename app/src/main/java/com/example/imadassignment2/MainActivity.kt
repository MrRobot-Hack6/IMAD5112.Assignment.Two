//Main page
package com.example.imadassignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var txtQuestion: TextView
    lateinit var rbGroup: RadioGroup
    lateinit var rbHack: RadioButton
    lateinit var rbMyth: RadioButton
    lateinit var btnNext: Button
    lateinit var btnBack: Button

    var currentQuestion = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val questions = arrayOf(
            "Putting a wet phone in rice is the best way to dry it?",
            "A pinch of salt in coffee removes the bitterness?",
            "If you're in an elevator and someone presses all the buttons you can cancel them by double-pressing them?",
            "Blowing into a game cartridge fixes connection issues?",
            "Charging your phone overnight will destroy the battery?",
            "You need to drink 8 glasses of water a day?",
        )

        // Standardized answers: 0 = Hack, 1 = Myth
        val correctAnswers = intArrayOf(1, 0, 0, 1, 1, 1)

        txtQuestion = findViewById(R.id.txtQuestion)
        rbGroup = findViewById(R.id.rbGroup)
        rbHack = findViewById(R.id.rbHack)
        rbMyth = findViewById(R.id.rbMyth)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)

        // Initial setup
        txtQuestion.text = questions[currentQuestion]

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()}

            btnNext.setOnClickListener {
                val selectedId = rbGroup.checkedRadioButtonId

                val answerIndex = when (selectedId) {
                    R.id.rbHack -> 0
                    R.id.rbMyth -> 1
                    else -> -1
                }

                if (answerIndex == correctAnswers[currentQuestion]) {
                    score++
                }

                if (currentQuestion < questions.size - 1) {
                    currentQuestion++
                    txtQuestion.text = questions[currentQuestion]
                    rbGroup.clearCheck()
                } else {
                    val intent = Intent(this, ScoreScreen::class.java)
                    intent.putExtra("Score", score)
                    intent.putExtra("total", questions.size)
                    // Sending as a primitive intArray ensures it is received correctly
                    intent.putExtra("Answers", correctAnswers)
                    startActivity(intent)
                    finish()
                }
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
