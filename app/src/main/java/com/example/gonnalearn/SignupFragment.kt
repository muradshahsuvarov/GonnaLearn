import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.gonnalearn.MainActivity
import com.example.gonnalearn.R
import java.util.*


// Here ":" symbol is indicate that SignupFragment
// is child class of Fragment Class
class SignupFragment : Fragment() {

    val roles = arrayOf("Student", "Tutor")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val myInflater = inflater.inflate(
            R.layout.signup_fragment, container, false
        )

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


                // Calling authenticating user
                (activity as MainActivity?)?.RegisterUser(
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



    // Here "layout_signup" is a name of layout file
    // created for SignFragment
}
