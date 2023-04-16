package my.edu.utar.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText username1, description,birthday1,email1;
    Button savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent data = getIntent();
        String username = data.getStringExtra("username");
        String statusTV = data.getStringExtra("statusTV");
        String birthday = data.getStringExtra("birthday");
        String email = data.getStringExtra("email");

        username1 = findViewById(R.id.username1);
        description = findViewById(R.id.description);
        birthday1 = findViewById(R.id.birthday1);
        email1 = findViewById(R.id.email1);
        savebutton = findViewById(R.id.save_button);

        username1.setText(username);
        description.setText(statusTV);
        birthday1.setText(birthday);
        email1.setText(email);

        Log.d(TAG,"onCreate: " + username + " " + statusTV + " "+ birthday + " " + email);

        savebutton.setOnClickListener(v -> {
            // Create an Intent to store the new data
            Intent resultIntent = new Intent();

            // Get the new data from the EditTexts
            String newUsername = username1.getText().toString();
            String newStatusTV = description.getText().toString();
            String newBirthday = birthday1.getText().toString();
            String newEmail = email1.getText().toString();

            // Put the new data into the Intent
            resultIntent.putExtra("newUsername", newUsername);
            resultIntent.putExtra("newStatusTV", newStatusTV);
            resultIntent.putExtra("newBirthday", newBirthday);
            resultIntent.putExtra("newEmail", newEmail);

            // Set the result code to indicate success and attach the Intent
            setResult(RESULT_OK, resultIntent);

            // Close the activity and return to the previous activity
            finish();
        });
    }
}