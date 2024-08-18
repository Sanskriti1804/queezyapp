package com.example.quizapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    //keeps the track of the current qsn posn
    private var mCurrentPosition : Int = 1
    private var mQuestionList : ArrayList<Question>? = null
    //tracks the user selected answer
    private var mSelectedOptionPosition : Int = 0
    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0

    //? = null - it is a nullable property
    //it allows to handle the components that may not be available or initialized yet
    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView? = null

    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null
    private var btnSubmit : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)

        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()

        setQuestion()
    }

    private fun setQuestion() {

        defaultOptionsView()

        //retrives the current question from the list based on the current posn
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        //sets the img based on the current qsn image resource
        //ivImage?.setImageResource(question.image)

        // SETS THE IMAGE USING GLIDE TO HANDLE GIFS
        Glide.with(this)
            .asGif()
            .load(question.image)
            .into(ivImage!!)

        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question

        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if(mCurrentPosition == mQuestionList!!.size){
            btnSubmit?.text = "FINISH"
        }
        else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    //handling the viuals of the options
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        //?.let - bc it is a 1nullable property
        //adds each option to the list if it is not null
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        //going through each option and reset its appearance
        for (option in options) {
            //parseColor - converts thr int color value to actual color
            option.setTextColor(Color.parseColor("#7A8089"))
            //text style - default
            option.typeface = Typeface.DEFAULT
            //like border thingyy
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.option_border
            )
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        //resets all options to default appearance
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        //tv- textview
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_bg
        )
    }

    //override method of the 'onclick' method from the on the 'view.onclicklistener' interface
    override fun onClick(view: View?) {
        when(view?.id){
            //for each id - it performs specific action
            R.id.tv_option_one ->
                tvOptionOne?.let {      //block of code is only executed only if it is not null
                    //calls this fnn
                    selectedOptionView(it, 1)       //it - non null text view of option one     //1 - option num
                }

            R.id.tv_option_two ->
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }

            R.id.tv_option_three ->
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }

            R.id.tv_option_four ->
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }

            R.id.btn_submit-> {
                //no option has been selected - user wants to skip the question
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else ->{
                            //no more qsns left in the quiz
                            //creates a new intent to start a new activity - result activity
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else{
                    //retrives the current qsn from the list of qsn
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                       //answerView - updates the bg of the selected option
                        answerView(
                            mSelectedOptionPosition, R.drawable.wrong_option_bg
                        )
                    }
                    else{
                        //counts the no of corrext answer
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_bg)

                    if (mCurrentPosition == mQuestionList!!.size){
                        btnSubmit?.text = "End"
                    }
                    else{
                        btnSubmit?.text = "Go to next question"
                    }

                    //resetting the selected option to null
                    mSelectedOptionPosition = 0
                }
            }
        }

    }

    //updates the background of the selected option
    private fun answerView(answer : Int, drawableView: Int){
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}

