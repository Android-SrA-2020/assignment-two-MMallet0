package com.learning.quiztake2


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
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

        //To turn on the option menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        //having these 2 here crashes the app
        if(savedInstanceState != null){
            questionIndex = savedInstanceState.getInt("currentIndex", 0)
        }


        //Buttons Logic
        binding.apply{
            nextButton.setOnClickListener{
                questionIndex = (questionIndex + 1) % 20
                updateView()
            }

            previewsButton.setOnClickListener{

                if(questionIndex == 0){
                    questionIndex = questionBank.size - 1
                } else {
                    questionIndex = (questionIndex - 1)
                }
                updateView()
            }

            trueButton.setOnClickListener{
                checkAnswer(true)
            }

            falseButton.setOnClickListener{
                checkAnswer(false)
            }

            cheatButton.setOnClickListener{

                //using safe args to send the question index
                navController.navigate(
                    MainFragmentDirections.actionMainFragmentToCheatFragment(questionIndex)
                )
            }
        }

        updateView()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("currentIndex", questionIndex)
    }


    //inflating the option menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.overflow_menu, menu)
    }

    //hooking up the option button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item, navController
        ) || super.onOptionsItemSelected(item)
    }

    /**
     * Updates the view according to the question in the list
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
