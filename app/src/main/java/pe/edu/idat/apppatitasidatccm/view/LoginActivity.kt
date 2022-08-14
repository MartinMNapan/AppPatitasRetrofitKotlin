package pe.edu.idat.apppatitasidatccm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qbo.apppatitas2qbo.utilitarios.TipoMensaje
import com.qbo.apppatitasidarccm.utilitarios.AppMensaje
import pe.edu.idat.apppatitasidatccm.R
import pe.edu.idat.apppatitasidatccm.databinding.ActivityLoginBinding
import pe.edu.idat.apppatitasidatccm.retrofit.response.ResponseLogin
import pe.edu.idat.apppatitasidatccm.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)

        binding.btnlogin.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)

        authViewModel.responseLogin.observe(this, Observer {
            response -> obtenerDatosLogin(response)
        })
    }

    private fun obtenerDatosLogin(response: ResponseLogin) {
       if (response.rpta){
           startActivity(Intent(applicationContext, HomeActivity::class.java))
       }else{
           AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ERROR)
       }
        binding.btnregistrar.isEnabled = true
        binding.btnlogin.isEnabled = true
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnlogin -> autenticarUsuario()
            R.id.btnregistrar -> startActivity(Intent(applicationContext,
                RegisterActivity::class.java))
        }
    }

    private fun autenticarUsuario() {
        binding.btnregistrar.isEnabled = false
        binding.btnlogin.isEnabled = false
        if (validarUsuarioPassword()){
            authViewModel.autenticarUsuario(binding.etusuario.text.toString(),
            binding.etpassword.text.toString())

        }else{
            AppMensaje.enviarMensaje(binding.root, getString(R.string.msgloginincompleto),
            TipoMensaje.ERROR)
            binding.btnregistrar.isEnabled = true
            binding.btnlogin.isEnabled = true
        }
    }

    private fun validarUsuarioPassword(): Boolean {
        var okValidacion = true
        if (binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okValidacion = false
        }else if (
            binding.etpassword.text.toString().trim().isEmpty()){
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            okValidacion = false
        }
        return okValidacion
    }
}