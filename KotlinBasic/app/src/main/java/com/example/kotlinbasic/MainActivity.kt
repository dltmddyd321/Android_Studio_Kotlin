package com.example.kotlinbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinbasic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding) {
            btn.setOnClickListener {

            }
            text.text = "반갑습니다."
        }
    }

    fun studyRun() {
        val phones = mutableListOf("010-5385-1748", "010-6543-8605", "010-6588-7413")

        val list = mutableListOf(1,2,3,4,5,6,7,8)

        val names = mutableListOf("Scott", "Kane", "Jason")

        val koreanPeople = KoreanPeople()

        //코드를 축약시켜 객체에 대한 함수를 { } 내부에서 적용 가능
        phones.run {
            add("010-6548-0903")
        }

        //변수에 대한 Action의 특정 값을 가져올 시 사용 -> Run / Let
        val resultRun = koreanPeople.person.run {
            add(Person("Max", "010-5432-0094", 23))
            add(Person("Kelly", "010-8432-1296", 21))
        }
        val resultApply = koreanPeople.person.apply {
            add(Person("Max", "010-5432-0094", 23))
            add(Person("Kelly", "010-8432-1296", 21))
        }

        //파라미터를 it 또는 직접 명시하여 그에 대한 메서드를 { } 내부에서 적용 가능
        //상수로 지정 후, 그 배열 값을 반환
        val resultLet = koreanPeople.person.let { task ->
            task.add(Person("Max", "010-5432-0094", 23))
        }
        val resultAlso = koreanPeople.person.also { person ->
            person.add(Person("Max", "010-5432-0094", 23))
        }
    }
}

class KoreanPeople {
    var person = mutableListOf<Person>()
    init {
        person.add(Person("Scott", "010-6543-8605", 20))
        person.add(Person("Kane", "010-6333-6542", 20))
        person.add(Person("Jason", "010-6123-5783", 20))
    }
}

data class Person (
    var name:String = "",
    var phone:String = "",
    var age:Int = 21
)
