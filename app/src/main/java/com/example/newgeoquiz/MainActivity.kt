package com.example.newgeoquiz

import android.os.Bundle
import android.util.Log

import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.Question



private lateinit var trueButton: Button
private lateinit var falseButton: Button
private lateinit var nextButton: Button
private lateinit var prevButton: Button
private lateinit var questionTextView: TextView

private val questionBank = listOf(
    Question(R.string.question_australia, true),
    Question(R.string.question_oceans, true),
    Question(R.string.question_mideast, false),
    Question(R.string.question_africa, false),
    Question(R.string.question_americas, true),
    Question(R.string.question_asia, true)
)
private var currentIndex = 0




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        trueButton = findViewById(R.id.true_button)//переменная trueButton ищет виджет по ID в ресурсах проекта и присваивает его
        falseButton = findViewById(R.id.false_button)//переменная falseButton ищет виджет по ID в ресурсах проекта и присваивает его
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)


        questionTextView.setOnClickListener(View.OnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion() })

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        prevButton.setOnClickListener{
            if(currentIndex > 0)
            {currentIndex = (currentIndex - 1)
                updateQuestion()}
            else {
                currentIndex=5}
        }
        updateQuestion()


        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }


    }

    //функция которая проверяет правда/ложь ответ пользователя и утверждения в вопросе
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        if (userAnswer == correctAnswer) {
            viewToastTrue()
        } else {
            viewToastFalse()
        }

    }

    //функция которая берет вопрос по ID преобразовывает в текст и отправляет в переменную для реализации на экране
    fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    //функция вызова тоста True
    fun viewToastTrue() {

        val toast = Toast.makeText(this,
            R.string.correct_toast,
            LENGTH_LONG)//задаются параметры Уведомление
        toast.setGravity(Gravity.TOP, 0, 150)//задаются координаты уведомления
        toast.show()//вызов уведомления
    }

    //функция вызова тоста False
    fun viewToastFalse() {

        val toast = Toast.makeText(this,
            R.string.incorrect_toast,
            LENGTH_LONG)//задаются параметры Уведомление
        toast.setGravity(Gravity.TOP, 0, 150)//задаются координаты уведомления
        toast.show()//вызов уведомления
    }
}