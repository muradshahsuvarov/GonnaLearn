import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gonnalearn.MainActivity
import com.example.gonnalearn.ProfileFragment
import com.example.gonnalearn.R
import com.example.gonnalearn.data.User
import com.example.gonnalearn.data.UserViewModel
import kotlinx.android.synthetic.main.login_fragment.view.*


// Here ":" symbol is indicate that LoginFragment
// is child class of Fragment Class
class LoginFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val myInflater = inflater.inflate(
                R.layout.login_fragment, container, false
        )

        //Initialize User View Model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        myInflater.findViewById<Button>(R.id.signInButton).setOnClickListener {

            try {

                var email = myInflater.findViewById<EditText>(R.id.loginEmail).text.toString()
                var password = myInflater.findViewById<EditText>(R.id.loginPassword).text.toString()

                // Authenticating user

                // Set Up Session Status of the user
                MainActivity.userRemembered = myInflater.rememberBeCheckBox.isChecked()
                var authUser : User? = null

                var userExists : Boolean = false
                mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { users ->
                    for(user in users){
                        if(user.email == email && user.password == password){

                            userExists = true
                            ProfileFragment.fullName = user.fullName
                            ProfileFragment.email = user.email
                            ProfileFragment.role = user.role
                            ProfileFragment.dateOfBirth = user.dateOfBirth

                            if(MainActivity.userRemembered){
                                authUser = user
                            }

                            break
                        }
                    }
                })


                if(userExists){

                    Toast.makeText(context, "Successfully Authenticated!", Toast.LENGTH_SHORT).show()
                    MainActivity.rememberedUser = authUser
                    (activity as MainActivity?)?.AuthenticateUser()
                    userExists = false

                }else{
                    Toast.makeText(context, "Wrong password or username!", Toast.LENGTH_SHORT).show()
                }

                // Authenticating user

            }catch(e : Exception){
                Toast.makeText(getActivity(), "$e", Toast.LENGTH_LONG).show();
            }

       }



        return myInflater
    }
    // Here "layout_login" is a name of layout file
    // created for LoginFragment
}
