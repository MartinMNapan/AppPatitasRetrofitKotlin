package pe.edu.idat.apppatitasidatccm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.apppatitasidatccm.repository.AuthRepository
import pe.edu.idat.apppatitasidatccm.retrofit.request.RequestLogin
import pe.edu.idat.apppatitasidatccm.retrofit.request.RequestRegister
import pe.edu.idat.apppatitasidatccm.retrofit.response.ResponseLogin
import pe.edu.idat.apppatitasidatccm.retrofit.response.ResponseRegister

class AuthViewModel: ViewModel() {

        var responseLogin: LiveData<ResponseLogin>
        var responseRegister: LiveData<ResponseRegister>
        private var repository = AuthRepository()

        init{
            responseLogin = repository.loginresponse
            responseRegister = repository.registerresponse
        }

        fun autenticarUsuario(usuario: String, password: String){
            responseLogin = repository.autenticarUsuario(RequestLogin(usuario, password))
        }

        fun registrarUsuario(nombres: String, apellidos: String, email: String, celular: String,
        usuario: String, password: String){
            responseRegister = repository.registroUsuario(RequestRegister
                (nombres, apellidos, email, celular, usuario, password))
        }
}