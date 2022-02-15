package net.maribat.recyclerviewviewbindingkt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.maribat.recyclerviewviewbindingkt.RvAdapter.OnItemClickListener
import net.maribat.recyclerviewviewbindingkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // view binding for the activity
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // create reference to the adapter and the list
    // in the list pass the model of Language
    private lateinit var rvAdapter: RvAdapter
    private lateinit var languageList: List<Language>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load data to language list
        loadLanguage()

        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        // pass it to rvLists layoutManager
        binding.rvList.setLayoutManager(layoutManager)

        // initialize the adapter,
        // and pass the required argument
        rvAdapter = RvAdapter()
        rvAdapter.setList(languageList)
        // attach adapter to the recycler view
        binding.rvList.adapter = rvAdapter

        rvAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(language: Language?) {
                /*  Toast.makeText(
                      this@MainActivity,
                      language?.name + " " + language?.exp,
                      Toast.LENGTH_SHORT
                  ).show();*/

                openDialog(language)
            }
        })
    }

    private fun openDialog(language: Language?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("Clicked item is" + language?.name)
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
            Toast.makeText(
                applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNeutralButton("GoToCustomDialog") { dialog, which ->
            Toast.makeText(
                applicationContext,
                "Custom", Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, CustomDialogActivity::class.java)
            startActivity(intent)
        }
        builder.show()
    }

    // add items to the list manually in our case
    private fun loadLanguage() {
        languageList = listOf(
            Language("Java", "Exp : 3 years"),
            Language("Kotlin", "Exp : 2 years"),
            Language("Python", "Exp : 4 years"),
            Language("JavaScript", "Exp : 6 years"),
            Language("PHP", "Exp : 1 years"),
            Language("CPP", "Exp : 8 years"),
            Language("Android", "Exp : 14 years"),
        )
    }

    // on destroy of view make
    // the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}