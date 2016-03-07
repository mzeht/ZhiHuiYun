package com.wpmac.zhihuiyun.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.fragment.AreaFragmnet;
import com.wpmac.zhihuiyun.fragment.CompanyFragment;
import com.wpmac.zhihuiyun.fragment.DevicesFragment;
import com.wpmac.zhihuiyun.fragment.GitDicFrafment;
import com.wpmac.zhihuiyun.fragment.MeasFragment;
import com.wpmac.zhihuiyun.fragment.MetersFragment;
import com.wpmac.zhihuiyun.fragment.PointsFragment;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
         {
    private int mCurrentMenu;
    private CompanyFragment companyFragment;
    private GitDicFrafment gitDicFrafment;
    private AreaFragmnet areaFragmnet;
    private DevicesFragment devicesFragment;
    private MetersFragment metersFragment;
    private MeasFragment measFragment;
    private PointsFragment pointsFragment;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switchFragment(item.getItemId());
                item.setChecked(true);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        switchFragment(R.id.company_register);
    }

    private void replaceMainFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        if(fragment.isAdded()){
            Toast.makeText(getApplicationContext(),"isadded",Toast.LENGTH_SHORT).show();

        }else{

            manager.beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
        }
    }

    private void switchFragment(int menuId) {
        mCurrentMenu = menuId;
        ActionBar actionBar = getSupportActionBar();
        switch (menuId) {
            case R.id.company_register:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.company_register);
                }
                if (companyFragment == null) {
                    companyFragment = CompanyFragment.getInstance();
                }
                replaceMainFragment(companyFragment);
                break;
            case R.id.get_dic:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.get_dic);
                }
                if (gitDicFrafment == null) {
                    gitDicFrafment = GitDicFrafment.getInstance();
                }
                replaceMainFragment(gitDicFrafment);
                break;
            case R.id.area_data:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.area_data);
                }
                if (areaFragmnet == null) {
                    areaFragmnet = AreaFragmnet.getInstance();
                }
                replaceMainFragment(areaFragmnet);
                break;
            case R.id.dev_data:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.dev_data);
                }
                if (devicesFragment == null) {
                    devicesFragment = DevicesFragment.getInstance();
                }
                replaceMainFragment(devicesFragment);
                break;
            case R.id.meter_data:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.meter_data);
                }
                if (metersFragment == null) {
                    metersFragment = MetersFragment.getInstance();
                }
                replaceMainFragment(metersFragment);
                break;
            case R.id.meas_data:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.meas_data);
                }
                if (measFragment == null) {
                    measFragment = MeasFragment.getInstance();
                }
                replaceMainFragment(measFragment);
                break;
            case R.id.point_data:
                if (actionBar != null) {
                    actionBar.setTitle(R.string.point_data);
                }
                if (pointsFragment == null) {
                    pointsFragment = PointsFragment.getInstance();
                }
                replaceMainFragment(pointsFragment);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.company_register) {
//            // Handle the camera action
//        } else if (id == R.id.get_dic) {
//
//        } else if (id == R.id.area_data) {
//
//        } else if (id == R.id.dev_data) {
//
//        } else if (id == R.id.meter_data) {
//
//        } else if (id == R.id.meas_data) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }


    // 双击退出函数
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            exitByDoubleClick(); // 调用双击退出函数
        }
        return true;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitByDoubleClick() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            List<Fragment> list = getSupportFragmentManager().getFragments();
            finish();
            System.exit(0);
        }
    }


}
