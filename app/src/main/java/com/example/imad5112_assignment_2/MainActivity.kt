package com.example.imad5112_assignment_2

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


// MainActivity is the main page of the app where the user will answer the questions and navigate through them using the next and back buttons.
class MainActivity : AppCompatActivity() {

    lateinit var txtQuestion : TextView
    lateinit var rbGroup : RadioGroup
    lateinit var rbHack : RadioButton
    lateinit var rbMyth : RadioButton
    lateinit var btnNext : Button
    lateinit var btnBack : Button

    // currentQuestion keeps track of which question the user is currently on, and score keeps track of how many questions the user has answered correctly.
    var currentQuestion = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Array of questions for the quiz
        val questions = arrayOf(
            "Putting a wet phone in rice is the best way to dry it?",
            "A pinch of salt in coffee removes the bitterness?",
            "If you're in an elevator and someone presses all the buttons you can cancel them by double-pressing them?",
            "Blowing into a game cartridge fixes connection issues?",
            "Charging your phone overnight will destroy the battery?",
            "You need to drink 8 glasses of water a day?",
        )

        // Array of correct answers corresponding to the questions, where 0 represents "Hack" and 1 represents "Myth"
        val correctAnswers = intArrayOf(1, 0, 0, 1, 1, 1)

        // Initializing the views by finding them in the layout
        txtQuestion = findViewById(R.id.txtQuestion)
        rbGroup = findViewById(R.id.rbGroup)
        rbHack = findViewById(R.id.rbHack)
        rbMyth = findViewById(R.id.rbMyth)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)

        // Initial setup
        txtQuestion.text = questions[currentQuestion]

        // Setting onClickListener for the back button to navigate back
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Setting onClickListener for the next button to check the user's answer, update the score, and navigate to the next question or the ScoreScreen if it was the last question
        btnNext.setOnClickListener {
            val selectedId = rbGroup.checkedRadioButtonId

            // Mapping the selected radio button ID to an index (0 for Hack, 1 for Myth)
            val answerIndex = when (selectedId) {
                R.id.rbHack -> 0
                R.id.rbMyth -> 1
                else -> -1
            }

            // Moving to the next question or navigating to the ScoreScreen if it was the last question
            if (currentQuestion < questions.size - 1){
                currentQuestion++
                txtQuestion.text = questions[currentQuestion]
                rbGroup.clearCheck()
            }else{
            }

            // Navigating to the ScoreScreen and passing the score, total questions, and correct answers as extras in the intent
                val intent = Intent(this, ScoreScreen::class.java)
                intent.putExtra("Score", score)
                intent.putExtra("total", questions.size)

            // Sending as a primitive intArray ensures it is received correctly
                intent.putExtra("Answers", correctAnswers)
                startActivity(intent)
                finish()
            }
        }


    }
