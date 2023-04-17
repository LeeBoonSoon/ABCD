package my.edu.utar.abcd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    homeFragment homeF = new homeFragment();
    profileFragment profileF = new profileFragment();
    searchFragment searchF = new searchFragment();
    addFragment addF = new addFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.person:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, profileF)
                        .commit();
                return true;

            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, homeF)
                        .commit();
                return true;

            case R.id.search:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, searchF)
                        .commit();
                return true;

            case R.id.add:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment,addF)
                        .commit();
                return true;
        }
        return false;
    }
}