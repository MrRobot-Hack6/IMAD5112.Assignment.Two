package com.example.imad5112_assignment_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class ScoreScreen : AppCompatActivity() {

    // Initializing the views for displaying the score, correct answers, review button, and return button
    lateinit var txtScore : TextView
    lateinit var btnReview : Button
    lateinit var txtReviewDisplay : TextView
    lateinit var btnReturn : Button
    lateinit var txtCorrect : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_screen)

        // Finding the views in the layout and assigning them to the variables
        txtScore = findViewById(R.id.txtScore)
        txtCorrect = findViewById(R.id.txtCorrect)
        txtReviewDisplay = findViewById(R.id.txtReviewDisplay)
        btnReview = findViewById(R.id.btnReview)
        btnReturn = findViewById(R.id.btnReturn)

        // Retrieving the score, total questions, and correct answers from the intent extras
        val score = intent.getIntExtra("Score", 0)
        val total = intent.getIntExtra("total", 0)
        val answersArray = intent.getIntArrayExtra("Answers")

        // Generating a feedback message based on the user's score
        val feedbackMessage = when (score) {
            0 -> "Practice more, you got 0 out of 6 correct!"
            1 -> "Not bad, you got 1 out of 6 correct!"
            2 -> "Keep trying, you got 2 out of 6 correct!"
            3 -> "Good job, you got 3 out of 6 correct!"
            4 -> "Great work, you got 4 out of 6 correct!"
            5 -> "Excellent, you got 5 out of 6 correct!"
            6 -> "Perfect score, you got 6 out of 6 correct!"
            else -> "Quiz complete!"
        }

        // Displaying the score and feedback message in the txtScore TextView
        txtScore.text = "Score: $score / $total \n\n $feedbackMessage"

        // Displaying the correct answers in the txtCorrect TextView by mapping the answers array to "Hack" or "Myth"
        if (answersArray != null) {
            val displayAnswers = answersArray.joinToString("\n ") { value ->
                if (value == 0) "Hack" else "Myth"
            }
            txtCorrect.text = "Correct answers: \n $displayAnswers"
        } else {
            txtCorrect.text = "Correct answers: \n Data not found"
        }

        // Setting an onClickListener for the return button to navigate back to the WelcomeScreen
        btnReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Setting an onClickListener for the review button to display the questions and their corresponding answers in the txtReviewDisplay TextView
        btnReview.setOnClickListener {
            txtReviewDisplay.text =
                "1. Putting a wet phone in rice is the best way to dry it? \n : Myth \n" +
                        "2. A pinch of salt in coffee removes the bitterness? \n : Hack \n" +
                        "3. If you're in an elevator and someone presses all the buttons you can cancel them by double-pressing them? \n : Hack \n" +
                        "4. Blowing into a game cartridge fixes connection issues? \n : Myth \n" +
                        "5. Charging your phone overnight will destroy the battery? \n : Myth \n" +
                        "6. You need to drink 8 glasses of water a day? \n : Myth"
            txtReviewDisplay.visibility = View.VISIBLE

        }

        }

    }
