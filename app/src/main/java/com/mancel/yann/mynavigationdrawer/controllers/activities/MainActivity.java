package com.mancel.yann.mynavigationdrawer.controllers.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.mancel.yann.mynavigationdrawer.R;
import com.mancel.yann.mynavigationdrawer.controllers.fragments.NewsPageFragment;
import com.mancel.yann.mynavigationdrawer.controllers.fragments.ParameterPageFragment;
import com.mancel.yann.mynavigationdrawer.controllers.fragments.ProfilePageFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // FIELDS --------------------------------------------------------------------------------------

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private NewsPageFragment mNewsPageFragment;
    private ProfilePageFragment mProfilePageFragment;
    private ParameterPageFragment mParameterPageFragment;

    // METHODS -------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Associates the layout file to this class
        setContentView(R.layout.activity_main);

        // Configures the ToolBar field
        this.configureToolBar();

        // Configures the DrawerLayout field
        this.configureDrawerLayout();

        // Configures the NavigationView field
        this.configureNavigationView();

        // Initializes the first Fragment object at the launching of the application
        this.configureFirstFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Retrieves the item id of the item selected int the NavigationView field
        final int id = menuItem.getItemId();

        switch (id) {
            case R.id.menu_drawer_news: {
                // Initializes the NewsPageFragment object and displays it
                this.configureNewsPageFragment();
                break;
            }
            case R.id.menu_drawer_profile: {
                // Initializes the ProfilePageFragment object and displays it
                this.configureProfilePageFragment();
                break;
            }
            case R.id.menu_drawer_setting: {
                // Initializes the ParameterPageFragment object and displays it
                this.configureParameterPageFragment();
                break;
            }
            default: {
                Log.e(getClass().getSimpleName(), "onNavigationItemSelected: none of ids selected among the list");
            }
        }

        // Closes the NavigationView at the end of the user action
        this.mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        // Handles back click to close menu
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    /**
     * Configures the ToolBar field
     */
    private void configureToolBar() {
        // Initializes le ToolBar field thanks to the XML file
        this.mToolbar = (Toolbar) findViewById(R.id.activity_main_ToolBar);

        // Adds the ToolBar field to the activity
        setSupportActionBar(this.mToolbar);
    }

    /**
     * Configures the DrawerLayout field
     */
    private void configureDrawerLayout() {
        // Initializes le DrawerLayout field thanks to the XML file
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_DrawerLayout);

        // Creates the "Hamburger" button
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                  this.mDrawerLayout,
                                                                  this.mToolbar,
                                                                  R.string.navigation_drawer_open,
                                                                  R.string.navigation_drawer_close);

        // Adds the listener (the "Hamburger" button) to the DrawerLayout field
        this.mDrawerLayout.addDrawerListener(toggle);

        // Synchronization
        toggle.syncState();
    }

    /**
     * Configures the NavigationView field
     */
    private void configureNavigationView() {
        // Initializes le NavigationView field thanks to the XML file
        this.mNavigationView = (NavigationView) findViewById(R.id.activity_main_NavigationView);

        // Adds the Listener to each item selected
        // MainActivity class implements the NavigationView.OnNavigationItemSelectedListener interface
        this.mNavigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Starts a Transaction object with the Fragment object in argument
     *
     * @param fragment a Fragment object that contains the new Fragment to display
     */
    private void StartTransactionFragment(final Fragment fragment) {
        // If the fragment is not displayed
        if (!fragment.isVisible()) {
            // Adds the transaction to create the fragment [FragmentManager -> FragmentTransaction -> int]
            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.activity_main_FrameLayout, fragment)
                                       .commit();
        }
    }

    /**
     * Initializes the NewsPageFragment object and displays it
     */
    private void configureNewsPageFragment() {
        // Creates a Fragment object
        if (this.mNewsPageFragment == null) {
            this.mNewsPageFragment = NewsPageFragment.newInstance();
        }

        // Starts a Transaction object with the Fragment object in argument
        this.StartTransactionFragment(this.mNewsPageFragment);
    }

    /**
     * Initializes the ProfilePageFragment object and displays it
     */
    private void configureProfilePageFragment() {
        // Creates a Fragment object
        if (this.mProfilePageFragment == null) {
            this.mProfilePageFragment = ProfilePageFragment.newInstance();
        }

        // Starts a Transaction object with the Fragment object in argument
        this.StartTransactionFragment(this.mProfilePageFragment);
    }

    /**
     * Initializes the ParameterPageFragment object and displays it
     */
    private void configureParameterPageFragment() {
        // Creates a Fragment object
        if (this.mParameterPageFragment == null) {
            this.mParameterPageFragment = ParameterPageFragment.newInstance();
        }

        // Starts a Transaction object with the Fragment object in argument
        this.StartTransactionFragment(this.mParameterPageFragment);
    }

    /**
     * Initializes the first Fragment object at the launching of the application
     */
    private void configureFirstFragment() {
        // Creates the Fragment thanks to the XML file
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_FrameLayout);

        // If nothing is displayed into the FrameLayout widget, the NewsPageFragment will be displayed
        if (fragment == null) {
            // Configures the NewsPageFragment object and displays it
            this.configureNewsPageFragment();

            // Marks as selected the menu item corresponding to the NewsPageFragment
            this.mNavigationView.getMenu().getItem(0).setChecked(true);
        }
    }
}
