package com.example.daniel.barra_dinamica;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(android.R.style.Theme_Holo_Light_NoActionBar);

        DisplayMetrics met = getResources().getDisplayMetrics();
        CoordinatorLayout cluno = new CoordinatorLayout(this);
        cluno.setBackgroundColor(Color.RED);


        AppBarLayout appbar = new AppBarLayout(this);

        collapsingToolbarLayout = new CollapsingToolbarLayout(this);//(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        //////////////////////// params collapsingToolbarLayout ////////////////////////////////
        AppBarLayout.LayoutParams paramsbar = new AppBarLayout.LayoutParams(
                AppBarLayout.LayoutParams.MATCH_PARENT,
                AppBarLayout.LayoutParams.MATCH_PARENT
        );

        paramsbar.setScrollFlags(
                AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
        );

        collapsingToolbarLayout.setContentScrim(
                getResources().getDrawable(
                        R.mipmap.ic_launcher
                )
        );

        collapsingToolbarLayout.setLayoutParams(paramsbar);
        collapsingToolbarLayout.setTitle("holaaaa");


        ////////////////////////////////////////////////////////////////////////
        /////////////////////  parametros imagen  //////////////////////////////
        CollapsingToolbarLayout.LayoutParams paramsimg = new CollapsingToolbarLayout.LayoutParams(
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT
        );
        paramsimg.setCollapseMode(
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
        img.setLayoutParams(paramsimg);
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////  toolbar    ///////////////////////////////////////////////////
        CollapsingToolbarLayout.LayoutParams paramstoolbar = new CollapsingToolbarLayout.LayoutParams(
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,
                R.attr.actionBarSize
        );

        paramstoolbar.setCollapseMode(
                CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        );
        Toolbar toolbar = new Toolbar(this);//(Toolbar) findViewById(R.id.toolbar);
        toolbar.setMinimumHeight(50);
        toolbar.canShowOverflowMenu();
        toolbar.collapseActionView();
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setLogo(
                getResources().getDrawable(
                        R.mipmap.ic_launcher
                )
        );
        toolbar.setNavigationIcon(
                getResources().getDrawable(
                        R.mipmap.ic_launcher
                )
        );


        toolbar.setTitleTextColor(
                Color.BLACK
        );
        toolbar.setPadding(0,50,0,0);

        ////////////////////////////////////////////////////////////////
        NestedScrollView nestedScroll = new NestedScrollView(this);
        nestedScroll.setClipToPadding(false);
        FrameLayout frame = new FrameLayout(this);
        CardView cardView = new CardView(this);

        FrameLayout.LayoutParams paramscard = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        cardView.setCardElevation(530);
        cardView.setUseCompatPadding(true);

        LinearLayout ll = new LinearLayout(this);
        ll.setBackgroundDrawable(
                getResources().getDrawable(
                        R.mipmap.ic_launcher
                )
        );
        CoordinatorLayout.LayoutParams paramsflb = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
        );


        FloatingActionButton faboton = new FloatingActionButton(this);
        paramsflb.anchorGravity = Gravity.BOTTOM;

        faboton.setOnClickListener(this);
        ///////////////////////////////////////////////////////////////////////////////////////////);

        /////////////////////////////////////////////////////////////////////////////////7

        cluno.addView(faboton);

        cardView.addView(
                ll,
                CardView.LayoutParams.MATCH_PARENT,
                CardView.LayoutParams.WRAP_CONTENT
        );
        frame.addView(cardView);

        nestedScroll.addView(
                frame,
                NestedScrollView.LayoutParams.MATCH_PARENT,
                NestedScrollView.LayoutParams.WRAP_CONTENT
        );

        cluno.addView(
                nestedScroll,
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT
        );



        collapsingToolbarLayout.addView(
                toolbar,
                Toolbar.LayoutParams.MATCH_PARENT,
                met.heightPixels * 10 / 100
                );


        appbar.addView(
                collapsingToolbarLayout,
                AppBarLayout.LayoutParams.MATCH_PARENT,
                AppBarLayout.LayoutParams.MATCH_PARENT
        );

        cluno.addView(
                appbar,
                AppBarLayout.LayoutParams.MATCH_PARENT,
                met.heightPixels * 43 / 100
        );

        dynamicToolbarColor();
        toolbarTextAppernce();

        setContentView(cluno);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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


    @Override
    public void onClick(View v) {

    }


}
