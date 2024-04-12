package com.example.menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    FrameLayout frm_home;
    BottomNavigationView nav_btn;

    // Initialize the fragments only once
    private Fragment inicioFragment;
    private Fragment chatFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            inicioFragment = new InicioFragment();
            chatFragment = new ChatFragment();
            LoadFragment(inicioFragment); // inicia el frame en inicio
        } else {
            inicioFragment = getSupportFragmentManager().findFragmentByTag(InicioFragment.class.getSimpleName());
            chatFragment = getSupportFragmentManager().findFragmentByTag(ChatFragment.class.getSimpleName());
        }

        frm_home = findViewById(R.id.frm_home);
        nav_btn = findViewById(R.id.nav_btn);

        nav_btn.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_inicio) {
                    LoadFragment(inicioFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_chat) {
                    LoadFragment(chatFragment);
                }
                return false;
            }
        });



    }

    public void LoadFragment(Fragment fr) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_home, fr, fr.getClass().getSimpleName());
        transaction.commit();
    }
}

