package com.example.daniel.barra_dinamica;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics met = getResources().getDisplayMetrics();
        CoordinatorLayout cluno = new CoordinatorLayout(this);
        cluno.setBackgroundColor(Color.RED);

<<<<<<< HEAD
=======
        // AppBarLayout
        CoordinatorLayout.LayoutParams appBarParams = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, met)
        );
>>>>>>> actionbar_fix
        AppBarLayout appbar = new AppBarLayout(this);
        appbar.setLayoutParams(appBarParams);

        // CollapsingToolbarLayout
        collapsingToolbarLayout = new CollapsingToolbarLayout(this);
        AppBarLayout.LayoutParams collapParams = new AppBarLayout.LayoutParams(
                AppBarLayout.LayoutParams.MATCH_PARENT,
                AppBarLayout.LayoutParams.MATCH_PARENT
        );
        
        // Viendo el xml de ejemplos anteriores la solucion simple era 
        // agregar estos 2 flags para que funcionara
        // este codigo es equivalente a app:layout_scrollFlags="scroll|exitUnitCollapsed"
        collapParams.setScrollFlags(
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
        );
        collapsingToolbarLayout.setContentScrim(
                getResources().getDrawable(
                        R.mipmap.ic_launcher
                )
        );
        collapsingToolbarLayout.setLayoutParams(collapParams);
        collapsingToolbarLayout.setTitle("holaaaa");

        // ImageView
        CollapsingToolbarLayout.LayoutParams imgParams = new CollapsingToolbarLayout.LayoutParams(
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT
        );

        imgParams.setCollapseMode(
                CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
        );

        ImageView img = new ImageView(this);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setFitsSystemWindows(true);
        img.setBackgroundDrawable(
                getResources().getDrawable(
                        R.mipmap.ic_launcher
                )
        );
        img.setLayoutParams(imgParams);

        //Toolbar
        int toolbarHeight = Utils.getActionBarSize(this);
        CollapsingToolbarLayout.LayoutParams toolbarParams = new CollapsingToolbarLayout.LayoutParams(
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,
                toolbarHeight
        );
        toolbarParams.setCollapseMode(
                CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        );
        Toolbar toolbar = new Toolbar(this);//(Toolbar) findViewById(R.id.toolbar);
        toolbar.setLayoutParams(toolbarParams);

        // NestedScrollView
        CoordinatorLayout.LayoutParams nestedScrollParams = new CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
<<<<<<< HEAD
        toolbar.setPadding(0,80,0,0);
        toolbar.setMinimumHeight(90);

        ////////////////////////////////////////////////////////////////
=======
        nestedScrollParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
>>>>>>> actionbar_fix
        NestedScrollView nestedScroll = new NestedScrollView(this);
        nestedScroll.setLayoutParams(nestedScrollParams);

        // Linear Layout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Adding views
        cluno.addView(nestedScroll);

        nestedScroll.addView(
                linearLayout,
                NestedScrollView.LayoutParams.MATCH_PARENT,
                NestedScrollView.LayoutParams.WRAP_CONTENT
        );

        for (int i = 0; i < 10; i++) {
            ImageView testImage = new ImageView(this);
            testImage.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            linearLayout.addView(
                    testImage,
                    CardView.LayoutParams.MATCH_PARENT,
                    CardView.LayoutParams.WRAP_CONTENT
            );
        }
        // Rama del ActionBar
        cluno.addView(appbar);
        appbar.addView(collapsingToolbarLayout);
        collapsingToolbarLayout.addView(img);
        collapsingToolbarLayout.addView(toolbar);

        dynamicToolbarColor();
        toolbarTextAppernce();

        setContentView(cluno);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        actionBar.collapseActionView();
    }


    private void dynamicToolbarColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(
                getResources(),
                R.mipmap.ic_launcher
        );

        Palette.from(bitmap).generate(
                new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        collapsingToolbarLayout.setContentScrimColor(
                                palette.getMutedColor(
                                        Color.DKGRAY
                                )
                        );
                        collapsingToolbarLayout.setStatusBarScrimColor(
                                palette.getMutedColor(
                                        Color.BLUE
                                )
                        );
                    }
                }
        );
    }


    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(
                R.style.collapsedappbar
        );
        collapsingToolbarLayout.setExpandedTitleTextAppearance(
                R.style.expandedappbar
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 0, 0, "Option1").setShortcut('3', 'c');
        menu.add(0, 1, 0, "Option2").setShortcut('3', 'c');
        menu.add(0, 2, 0, "Option3").setShortcut('4', 's');

        SubMenu sMenu = menu.addSubMenu(0, 3, 0, "SubMenu"); //If you want to add submenu
        sMenu.add(0, 4, 0, "SubOption1").setShortcut('5', 'z');
        sMenu.add(0, 5, 0, "SubOption2").setShortcut('5', 'z');

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                // code for option1
                return true;
            case 1:
                // code for option2
                return true;
            case 2:
                // code for option3
                return true;
            case 4:
                // code for subOption1
                return true;
            case 5:
                // code for subOption2
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
