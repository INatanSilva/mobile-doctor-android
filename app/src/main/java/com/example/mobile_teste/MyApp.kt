import android.app.Application
import com.google.firebase.FirebaseApp

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicializa o Firebase
        FirebaseApp.initializeApp(this)
    }
}
