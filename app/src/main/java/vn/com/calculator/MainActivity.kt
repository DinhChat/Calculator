package vn.com.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var currentInput = "";
    private var currentOperator: String? = null;
    private var currentValue = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val resultView: TextView = findViewById<TextView>(R.id.result);
        val previousResultView: TextView = findViewById<TextView>(R.id.previousResult);
        val operatorView: TextView = findViewById<TextView>(R.id.operator);
        operatorView.text = "";
        resultView.text ="0";
        previousResultView.text = "";

        val gridLayout: GridLayout = findViewById<GridLayout>(R.id.gridLayout);

        for(i in 0 until gridLayout.childCount) {
            val view = gridLayout.getChildAt(i);


            if(view is Button) {
                view.setOnClickListener {
                    val buttonText = view.text.toString();
                    when(buttonText) {
                        "C" -> {
                            currentValue = 0;
                            currentInput = "";
                            currentOperator = null;
                            resultView.text = currentValue.toString();
                            operatorView.text = "";
                            previousResultView.text = "";
                        };
                        "=" -> {
                            previousResultView.text = "";
                            operatorView.text = "";
                            if(currentInput.isNotEmpty() && currentOperator != null) {
                                var value2 = currentInput.toInt();
                                when(currentOperator) {
                                    "+" -> currentValue += value2;
                                    "-" -> currentValue -= value2;
                                    "*" -> currentValue = currentValue * value2;
                                    "/" -> if(value2 != 0) currentValue = currentValue / value2 else {
                                        resultView.text = "ERROR";
                                        return@setOnClickListener;
                                    }
                                    else -> value2;
                                }
                                resultView.text = currentValue.toString();
                                currentOperator = null;
                                currentInput = currentValue.toString();

                            }
                        }
                        in "0".."9" -> {
                            previousResultView.text = currentValue.toString();
                            currentInput += buttonText;
                            resultView.text = currentInput;
                        };
                        in listOf("+", "-", "x", "/") -> {
                            resultView.text = "";
                            previousResultView.text = currentValue.toString();
                            if (currentInput.isNotEmpty()) {
                                if(currentOperator == null) {
                                    currentValue = currentInput.toInt();
                                    currentInput = "";
                                } else {
                                    var value = currentInput.toInt();
                                    when(currentOperator) {
                                        "+" -> currentValue += value;
                                        "-" -> currentValue -= value;
                                        "*" -> currentValue *= value;
                                        "/" -> if(value != 0) currentValue = currentValue / value else {
                                            resultView.text = "ERROR";
                                            return@setOnClickListener;
                                        }
                                    }
                                    currentInput = "";
                                }
                            }
                            currentOperator = if (buttonText == "x") "*" else buttonText;
                            operatorView.text = buttonText;
                        }
                        "CE" -> {
                            currentInput = currentInput.dropLast(1);
                            resultView.text = currentInput;
                        }
                    }
                }
            }
        }

    }
}