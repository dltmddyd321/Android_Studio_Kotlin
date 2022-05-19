package com.example.mvpbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvpbasic.databinding.ActivityMainBinding
import org.json.JSONObject

/*
Model은 데이터의 처리, View는 데이터의 출력, Presenter는 MV의 중재자 -> MVP 패턴
 */

class MainActivity : AppCompatActivity(), Contractor.View {

    private lateinit var presenter: Presenter
    private lateinit var repository: InfoRepository
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        repository = InfoRepository(this)
        presenter = Presenter(this@MainActivity, repository)

        presenter.initInfo()
        initButtonListener()
    }

    override fun showInfo(info: JSONObject) {
        binding.outputName.text = info.getString("name")
        binding.outputEmail.text = info.getString("email")
    }

    private fun initButtonListener() {
        binding.btnSave.setOnClickListener {
            val info = JSONObject()
            info.put("name", binding.editName.text.toString())
            info.put("email", binding.editEmail.text.toString())

            binding.editName.text.clear()
            binding.editEmail.text.clear()

            presenter.setInfo(info)
            presenter.saveInfo(info)
        }
    }
}