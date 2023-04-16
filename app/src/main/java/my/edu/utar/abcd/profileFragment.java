package my.edu.utar.abcd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class profileFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    public static final int EDIT_PROFILE_REQUEST_CODE = 1;

    private ImageView profileImage;
    private Button updateButton;
    private Button updateInformation;
    private TextView usernameTextView;
    private TextView statusTextView;
    private TextView birthdayTextView;
    private TextView emailTextView;

    private String defaultUsername = "username";
    private String defaultStatus = "Love to travel around the world";
    private String defaultBirthday = "birthday";
    private String defaultEmail = "email";
    private String savedUsername;
    private String savedStatus;
    private String savedBirthday;
    private String savedEmail;

    public profileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", savedUsername);
        outState.putString("status", savedStatus);
        outState.putString("birthday",savedBirthday);
        outState.putString("email",savedEmail);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        profileImage = view.findViewById(R.id.profileImage);
        updateButton = view.findViewById(R.id.update_button);
        updateInformation = view.findViewById(R.id.update_information);
        usernameTextView = view.findViewById(R.id.username);
        statusTextView = view.findViewById(R.id.statusTV);
        birthdayTextView = view.findViewById(R.id.birthday);
        emailTextView = view.findViewById(R.id.e_mail);

        //Retrieve saved data from sharedPreferences
        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        savedUsername = sharedPrefs.getString("username", defaultUsername);
        savedStatus = sharedPrefs.getString("status", defaultStatus);
        savedBirthday = sharedPrefs.getString("birthday",defaultBirthday);
        savedEmail = sharedPrefs.getString("email",defaultEmail);

        usernameTextView.setText(savedUsername);
        statusTextView.setText(savedStatus);
        birthdayTextView.setText(savedBirthday);
        emailTextView.setText(savedEmail);

        updateInformation.setOnClickListener((v) -> {
            Intent i = new Intent(v.getContext(), EditProfile.class);
            i.putExtra("username", usernameTextView.getText().toString());
            i.putExtra("statusTV", statusTextView.getText().toString());
            i.putExtra("birthday", birthdayTextView.getText().toString());
            i.putExtra("email", emailTextView.getText().toString());
            startActivityForResult(i, EDIT_PROFILE_REQUEST_CODE);
        });

        updateButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            profileImage.setImageURI(imageUri);

        }else if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            String newUsername = data.getStringExtra("newUsername");
            String newStatusTV = data.getStringExtra("newStatusTV");
            String newBirthday = data.getStringExtra("newBirthday");
            String newEmail= data.getStringExtra("newEmail");

            // Save the updated profile data to SharedPreferences
            SharedPreferences sharedPrefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("username", newUsername);
            editor.putString("status", newStatusTV);
            editor.putString("birthday", newBirthday);
            editor.putString("email", newEmail);
            editor.apply();

            usernameTextView.setText(newUsername);
            statusTextView.setText(newStatusTV);
            birthdayTextView.setText(newBirthday);
            emailTextView.setText(newEmail);
        }
    }

}