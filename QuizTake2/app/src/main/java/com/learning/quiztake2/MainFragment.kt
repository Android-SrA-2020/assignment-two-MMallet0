package com.learning.quiztake2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.learning.quiztake2.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,
            container, false
        )

        //rest of the logic

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

        return binding.root
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
            Toast.makeText(activity, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Wrong!", Toast.LENGTH_SHORT).show()
        }
    }

}
