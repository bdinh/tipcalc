package edu.washington.bdinh.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tipButton.isEnabled = false

        amountInput.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var currentAmount: String = s.toString()
                if (!currentAmount.startsWith("$")) {
                    amountInput.setText("$$s")
                } else if (currentAmount.isEmpty()) {
                    amountInput.setText("$")
                } else if (currentAmount.contains(".")) {
                    var digits = currentAmount.split(".")
                    if (digits[1].length > 2) {
                        var trimmedDigits: String = digits[1].slice(0..1)
                        var newResult: String = digits[0] + "." + trimmedDigits
                        amountInput.setText(newResult)
                    }
                }
                tipButton.isEnabled = !currentAmount.isEmpty()
                amountInput.setSelection(amountInput.text.length)
            }
        })
    }

    fun onClickTip(v: View) :Unit {
        var currentAmount: Double = amountInput.text.toString().replace("$","").toDouble()
        var tipAmount: Double = currentAmount * 0.15
        Log.i(TAG, tipAmount.toString())
        Toast.makeText(this, "$" + String.format("%.2f", tipAmount), Toast.LENGTH_LONG).show()
    }
}
