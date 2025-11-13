package ro.pub.cs.systems.eim.practicaltest01var09;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var09SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var09_secondary);

        Intent intent = getIntent();
        String expression = intent.getStringExtra(Constants.RESULT_KEY);

        int sum = 0;

        if (expression != null && !expression.isEmpty()) {
            String[] numbers = expression.split("\\+");
            for (String nr : numbers) {
                sum += Integer.parseInt(nr.trim());
            }
        }
        Toast.makeText(this, "Sum is: " + sum, Toast.LENGTH_LONG).show();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(Constants.RESULT_KEY, sum);
        setResult(RESULT_OK, resultIntent);

        finish();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
