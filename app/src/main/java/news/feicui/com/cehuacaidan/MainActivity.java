package news.feicui.com.cehuacaidan;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageRequest;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView tv;
    private ImageView im;
    private NwetworkUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolb);
        setSupportActionBar(toolbar);
        toolbar.setTitle("质感设计测试");
        tv = (TextView) findViewById(R.id.tv);
        im = (ImageView) findViewById(R.id.im);
        util = new NwetworkUtil(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        navigationView = (NavigationView) findViewById(R.id.nv);


        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        toggle.syncState();//将开关同步设置
        drawerLayout.addDrawerListener(toggle);

        util.getStringResuit("http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20",tv);
        util.setImagePic(im,"http://p3.so.qhimg.com/t01656137f980df0dc3.jpg");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String str="";
        switch (item.getItemId()){
            case R.id.it1:
                str="查看";
                break;
            case R.id.it2:
                str="增加";
                break;
            case R.id.it3:
                str="修改";
                break;
            case R.id.it4:
                str="删除";
                break;
        }
        Snackbar.make(navigationView,str,Snackbar.LENGTH_SHORT).setAction("myActivity",null).show();
         drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
