package pe.edu.idat.apppatitasidatccm.retrofit

import pe.edu.idat.apppatitasidatccm.retrofit.request.RequestLogin
import pe.edu.idat.apppatitasidatccm.retrofit.request.RequestRegister
import pe.edu.idat.apppatitasidatccm.retrofit.response.ResponseLogin
import pe.edu.idat.apppatitasidatccm.retrofit.response.ResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface PatitasServicio {

  @POST("login.php")
  fun login(@Body requestLogin: RequestLogin): Call<ResponseLogin>

  @PUT("persona.php")
  fun registro(@Body requestRegister: RequestRegister): Call<ResponseRegister>

}