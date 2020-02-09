package com.learning.rickandmortyquiz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.learning.rickandmortyquiz.databinding.ActivityMainBinding

/**
 * Created by Martin Mallet on 2020-01-14
 * Assignment 1
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        //navigation
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

        //rest of the logic
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.nextButton.setOnClickListener{
            questionIndex = (questionIndex + 1) % 20
            updateView()
        }

        binding.previewsButton.setOnClickListener{

            if(questionIndex == 0){
                questionIndex = questionBank.size - 1
            } else {
                questionIndex = (questionIndex - 1)
            }
            updateView()
        }

        binding.trueButton.setOnClickListener{
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener{
            checkAnswer(false)
        }

        updateView()
    }

    /**
     * Updates the view according to the question in the  list
     */
    private fun updateView(){
        binding.questionText.setText(questionBank[questionIndex].resourceId)
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

    //Adding the up button
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
