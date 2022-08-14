package pe.edu.idat.apppatitasidatccm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.apppatitasidatccm.retrofit.PatitasCliente
import pe.edu.idat.apppatitasidatccm.retrofit.request.RequestLogin
import pe.edu.idat.apppatitasidatccm.retrofit.request.RequestRegister
import pe.edu.idat.apppatitasidatccm.retrofit.response.ResponseLogin
import pe.edu.idat.apppatitasidatccm.retrofit.response.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var loginresponse = MutableLiveData<ResponseLogin>()
    var registerresponse = MutableLiveData<ResponseRegister>()

    fun autenticarUsuario(requestLogin: RequestLogin): MutableLiveData<ResponseLogin>{

        val call: Call<ResponseLogin> = PatitasCliente.retrofifService.login(requestLogin)
        call.enqueue(object: Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                loginresponse.value = response.body()
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }
        })
        return loginresponse
    }

    fun registroUsuario(requestRegister: RequestRegister): MutableLiveData<ResponseRegister>{

        val call: Call<ResponseRegister> = PatitasCliente.retrofifService.registro(requestRegister)
        call.enqueue(object: Callback<ResponseRegister>{
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                registerresponse.value = response.body()
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                Log.e("ErrorRegister", t.message.toString())
            }
        })
        return registerresponse
    }
}