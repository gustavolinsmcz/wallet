package com.gustavolins.wallet.ui.activity.SignUp
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gustavolins.wallet.databinding.SignupBinding
import com.gustavolins.wallet.model.Client
import com.gustavolins.wallet.ui.activity.MainActivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUp : AppCompatActivity() {

    private val singUpViewModel: SingUpViewModel by viewModels()
    private lateinit var binding: SignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref: SharedPreferences = this.getSharedPreferences("firstaccess", 0)
        val editor = pref.edit()
        val firstRun = pref.getBoolean("firstRun", true)
        if (firstRun) {
            editor.putBoolean("firstRun", false)
            editor.commit()
            singUpViewModel.signUp(Client(1, "Gustavo"))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }

        singUpViewModel.register.observe(this, androidx.lifecycle.Observer {
            startActivity(Intent(this, MainActivity::class.java))
            editor.putBoolean("firstAccess", false);
        })


    }
}