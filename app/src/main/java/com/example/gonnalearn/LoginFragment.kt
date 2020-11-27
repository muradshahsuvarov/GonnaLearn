import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gonnalearn.ProfileActivity
import com.example.gonnalearn.R


// Here ":" symbol is indicate that LoginFragment
// is child class of Fragment Class 
class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val myInflater = inflater.inflate(
                R.layout.login_fragment, container, false
        )


        myInflater.findViewById<Button>(R.id.signInButton).setOnClickListener {

            try{

                val intent = Intent(context, ProfileActivity::class.java)
                startActivity(intent)


            }catch(e : Exception){
                Toast.makeText(getActivity(), "$e", Toast.LENGTH_LONG).show();
            }

       }



        return myInflater
    }
    // Here "layout_login" is a name of layout file
    // created for LoginFragment
}
