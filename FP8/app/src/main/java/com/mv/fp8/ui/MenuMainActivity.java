package com.mv.fp8.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.mv.fp8.R;
import com.mv.fp8.data.prefs.PreferencesHelper;
import com.mv.fp8.ui.booksList.BooksListFragment;
import com.mv.fp8.ui.booksList.DetalhesLivroActivity;
import com.mv.fp8.ui.booksList.GrelhaLivrosFragment;

public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MAIL = "com.mv.fp5.MAIL";
    public static final int REQUEST_DETAIL_ACTIVITY = 1;

    private NavigationView navView;
    private DrawerLayout drawer;
    private FragmentManager fragManag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        fragManag = getSupportFragmentManager();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.ndOpen, R.string.ndClose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        carregarCabecalho();
        carregarFragmentoInicial();

    }

    private void carregarCabecalho() {
        String email = PreferencesHelper.getInstance(this).getEmailPreference();
        TextView navHeaderEmail = navView.getHeaderView(0).findViewById(R.id.nav_email);
        navHeaderEmail.setText(email);
    }


    private boolean carregarFragmentoInicial() {
        Menu menu = navView.getMenu();
        MenuItem defaultItem = menu.getItem(0);
        defaultItem.setChecked(true);
        return onNavigationItemSelected(defaultItem);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.navBooksList:
                fragment = new BooksListFragment();
                setTitle(item.getTitle());
                break;
            case R.id.navBooksGrid:
                fragment = new GrelhaLivrosFragment();
                setTitle(item.getTitle());
                break;
            case R.id.navMail:
                Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
                sendEmail.setData(Uri.parse("mailto: testing@gmail.com"));
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, "PSI - AMSI 2022/2023");
                startActivity(sendEmail);
                break;
        }

        if(fragment != null)
            fragManag.beginTransaction().replace(R.id.contentFragment, fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // Para Recycler View
    public void showBookDetails(int bookId) {
        Intent bookDetailsActivity = new Intent(this, DetalhesLivroActivity.class);
        bookDetailsActivity.putExtra(DetalhesLivroActivity.SCENARIO_KEY, DetalhesLivroActivity.SCENARIO_UPDATE);
        bookDetailsActivity.putExtra(DetalhesLivroActivity.BOOK_ID,bookId);
        startActivityForResult(bookDetailsActivity, REQUEST_DETAIL_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_DETAIL_ACTIVITY) {
            if(resultCode == RESULT_OK) {
                String resultMessage = data.getStringExtra(DetalhesLivroActivity.RESULT_MESSAGE);
                Snackbar.make(drawer, resultMessage, Snackbar.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}