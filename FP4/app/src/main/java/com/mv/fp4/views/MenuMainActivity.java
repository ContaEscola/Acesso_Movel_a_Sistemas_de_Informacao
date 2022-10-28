package com.mv.fp4.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;
import com.mv.fp4.R;

public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String MAIL = "com.mv.fp4.MAIL";
    private String email;

    private NavigationView navView;
    private DrawerLayout drawer;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        navView = findViewById(R.id.navView);
        drawer = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        fragmentManager = getSupportFragmentManager();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nOpen, R.string.nClose);


        setSupportActionBar(toolbar);
        toggle.syncState();
        drawer.addDrawerListener(toggle);
        navView.setNavigationItemSelectedListener(this);
        carregarCabecalho();
        carregarFragmentoInicial();
    }

    private void carregarCabecalho() {
        email = getIntent().getStringExtra(MAIL);
        View hview = navView.getHeaderView(0);
        TextView tvMail = hview.findViewById(R.id.tvHeaderMail);
        tvMail.setText(email);
    }

    private void carregarFragmentoInicial(){
        Fragment fragment = new EstaticoFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navEstatico:
                fragment = new EstaticoFragment();
                break;
            case R.id.navDinamico:
                fragment = new DinamicoFragment();
                break;
            case R.id.navMail:
                break;
        }

        if(fragment != null) {
            setTitle(item.getTitle());
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}