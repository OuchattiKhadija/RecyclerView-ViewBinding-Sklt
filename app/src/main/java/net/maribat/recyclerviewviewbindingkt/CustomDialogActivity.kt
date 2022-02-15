package net.maribat.recyclerviewviewbindingkt

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.maribat.recyclerviewviewbindingkt.databinding.ActivityCustomDialogBinding
import net.maribat.recyclerviewviewbindingkt.databinding.ActivityMainBinding
import net.maribat.recyclerviewviewbindingkt.databinding.CustomDialogLayoutBinding

class CustomDialogActivity : AppCompatActivity() {

    private var _binding: ActivityCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCustomDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customDialogBtn.setOnClickListener {
            openCustomDialog()
        }
    }

    private fun openCustomDialog() {
        val dialog = Dialog(this)
        val dialogLayoutBinding = CustomDialogLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(dialogLayoutBinding.root)
        dialog.setCancelable(false)
        dialogLayoutBinding.dialogDismissButton.setOnClickListener{
            val str = dialogLayoutBinding.editText.text
            binding.showTvTv.setText(str)
            dialog.dismiss()
        }
        dialog.show();

    }
}