import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gonnalearn.MainActivity
import com.example.gonnalearn.R
import com.example.gonnalearn.data.User
import com.example.gonnalearn.data.UserViewModel
import java.util.*


// Here ":" symbol is indicate that SignupFragment
// is child class of Fragment Class
class SignupFragment : Fragment() {

    val roles = arrayOf("Student", "Tutor")

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val myInflater = inflater.inflate(
            R.layout.signup_fragment, container, false
        )

        // Initializing View Model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val spinner = myInflater.findViewById<Spinner>(R.id.signUpRoleSpinner)
        spinner?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, roles) } as SpinnerAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("nothing selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }

        myInflater.findViewById<Button>(R.id.signUpButton).setOnClickListener {

            try {
                var signUpEmail = myInflater.findViewById<EditText>(R.id.signUpEmail).text.toString()
                var signUpPassword = myInflater.findViewById<EditText>(R.id.signUpPassword).text.toString()
                var signUpFullName = myInflater.findViewById<EditText>(R.id.signUpFullName).text.toString()
                var signUpRoleSpinner = myInflater.findViewById<Spinner>(R.id.signUpRoleSpinner).selectedItem.toString()


                // Registering User
                RegisterUser(
                    signUpEmail,
                    signUpPassword,
                    signUpFullName,
                    signUpRoleSpinner
                )

            }catch(e : Exception){
                Toast.makeText(getActivity(), "$e", Toast.LENGTH_LONG).show();
            }

        }

        return myInflater
    }



    // Registering user to our GonnaLearn Database
    fun RegisterUser(email : String, password: String, fullName: String, role: String){

        try{

            // Inserting user into the database
            if(insertUserToDatabase(email, password, fullName, role) == true){
                // Authenticating User
                (activity as MainActivity?)?.AuthenticateUser()
            }



        }catch(e : Exception){
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
        }

    }



    private fun insertUserToDatabase(email : String, password : String, fullName : String,
                                     role : String) : Boolean{

        try{

            if(InputCheck(email,password,fullName, role)){

                // TODO: Insert user object into real database
                    // TODO: Create User Object
                        // Id is a primary key nad will be autogenerated by Room Library
                        val user = User(0,email,fullName,"1998/10/22",role,password)
                //TODO: Add Data to Database
                mUserViewModel.addUser(user) // Adding user to the database

                Toast.makeText(context, "User successfully created! role=$role", Toast.LENGTH_SHORT).show()
                return true
            }else{
                Toast.makeText(context, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
                return false
            }
        }catch(e : Exception){
            Toast.makeText(context, "ERROR: + $e", Toast.LENGTH_SHORT).show()
        }

        return false
    }

    private fun InputCheck(email : String, password : String, fullName : String,
                           role : String) : Boolean{

        if(TextUtils.isEmpty(email) == false &&
            TextUtils.isEmpty(password) == false &&
            TextUtils.isEmpty(fullName) == false &&
            TextUtils.isEmpty(role) == false){

            return true

        }

        return false
    }


    // Here "layout_signup" is a name of layout file
    // created for SignFragment
}
