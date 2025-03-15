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
        resultView.text ="0";

        val gridLayout: GridLayout = findViewById<GridLayout>(R.id.gridLayout);

        for(i in 0 until gridLayout.childCount) {
            val view = gridLayout.getChildAt(i);


            if(view is Button) {
                view.setOnClickListener {
                    val buttonText = view.text.toString();
                    when(buttonText) {
                        "C" -> {
                            currentInput = "";
                            resultView.text = currentValue.toString();
                        };
                        "=" -> {
                            currentValue += currentInput.toInt();
                            resultView.text = currentValue.toString();
                        }
                        in "0".."9" -> {
                            currentInput += buttonText;
                            resultView.text = currentInput;
                        };
                        "+" -> {
                            currentValue += currentInput.toInt();
                            currentInput = "";
                            currentOperator = buttonText;
                            resultView.text = currentValue.toString();
                        }
                        "-" -> {
                            currentValue += currentInput.toInt();
                            currentInput = "";
                            currentOperator = buttonText;
                            resultView.text = currentValue.toString();
                        }
                        "x" -> {
                            currentValue += currentInput.toInt();
                            currentInput = "";
                            currentOperator = "*";
                            resultView.text = currentValue.toString();
                        }
                        "/" -> {
                            currentValue += currentInput.toInt();
                            currentInput = "";
                            currentOperator = buttonText;
                            resultView.text = currentValue.toString();
                        }
                    }
                }
            }
        }

    }
}