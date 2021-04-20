package com.yusril.kpperamalan.activity.des

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.ActivityAddDataBinding
import com.yusril.kpperamalan.databinding.ActivityInputRamalBinding
import com.yusril.kpperamalan.viewmodel.DesViewModel
import com.yusril.kpperamalan.viewmodel.MainViewModel

class InputRamalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputRamalBinding
    private lateinit var desViewModel: DesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_ramal)
        binding = ActivityInputRamalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title=getString(R.string.rama_periode)
        desViewModel= ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DesViewModel::class.java)

        binding.btnSubmit.setOnClickListener {
            val bulan:Int=binding.edtBulan.text.toString().toInt()
            desViewModel.setProsesRamal(bulan)
            Toast.makeText(
                this@InputRamalActivity,
                getString(R.string.proses_ramal_succsess),
                Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}