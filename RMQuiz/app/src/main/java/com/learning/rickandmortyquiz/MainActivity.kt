package com.learning.rickandmortyquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Martin Mallet on 2020-01-14
 * Assignment 1
 */

class MainActivity : AppCompatActivity() {

    //convenience function that creates a list
    //knows what to return with inference
    //in this case it will return a list of question object
    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true))

    private var questionIndex = 0

    /**
     * Initial creation of the app
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.next_button).setOnClickListener{
            questionIndex = (questionIndex + 1) % 20
            updateView()
        }

        findViewById<Button>(R.id.previews_button).setOnClickListener{

            if(questionIndex == 0){
                questionIndex = questionBank.size - 1
            } else {
                questionIndex = (questionIndex - 1)
            }
            updateView()
        }

        findViewById<Button>(R.id.true_button).setOnClickListener{
            checkAnswer(true)
        }

        findViewById<Button>(R.id.false_button).setOnClickListener{
            checkAnswer(false)
        }

        updateView()
    }

    /**
     * Updates the view according to the question in the  list
     */
    private fun updateView(){
        findViewById<TextView>(R.id.question_text)
            .setText(questionBank[questionIndex].resourceId)
    }

    /**
     * checks the answer and displays the appropriate toast
     */
    private fun checkAnswer(answer: Boolean) {

        if(answer == questionBank[questionIndex].answer){
            Toast.makeText(applicationContext, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Wrong!", Toast.LENGTH_SHORT).show()
        }
    }
}
