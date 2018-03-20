package in.leaf.abhi.rajasthanhackathon;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CategorySelectActivity extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerAdapter pagerAdapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_select_layout);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        pagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(viewPager);

    }
}
