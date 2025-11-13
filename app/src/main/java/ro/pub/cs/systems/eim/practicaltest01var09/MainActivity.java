package ro.pub.cs.systems.eim.practicaltest01var09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private Button addButton;
    private Button computeButton;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.add) {
                String input = editText1.getText().toString();
                if (input.isEmpty()) return;
                String currentText = editText2.getText().toString();
                if (currentText.isEmpty()) {
                    editText2.setText(input);
                } else {
                    editText2.setText(currentText + " + " + input);
                }
                editText1.setText("");
            }
            if (view.getId() == R.id.compute) {
                Intent intent = new Intent(getApplicationContext(),
                        PracticalTest01Var09SecondaryActivity.class);

                String resultText = editText2.getText().toString();
                intent.putExtra(Constants.RESULT_KEY, resultText);

                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        addButton = findViewById(R.id.add);
        computeButton = findViewById(R.id.compute);

        addButton.setOnClickListener(new ButtonClickListener());
        computeButton.setOnClickListener(new ButtonClickListener());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.LEFT_EDIT_TEXT_KEY, editText1.getText().toString());
        outState.putString(Constants.RIGHT_EDIT_TEXT_KEY, editText2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.LEFT_EDIT_TEXT_KEY)) {
            editText1.setText(savedInstanceState.getString(Constants.LEFT_EDIT_TEXT_KEY));
        } else {
            editText1.setText("0");
        }

        if (savedInstanceState.containsKey(Constants.RIGHT_EDIT_TEXT_KEY)) {
            editText2.setText(savedInstanceState.getString(Constants.RIGHT_EDIT_TEXT_KEY));
        } else {
            editText2.setText("0");
        }
    }

}
