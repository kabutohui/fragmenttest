package cn.edu.xidian.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    private RadioGroup mRg;
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mRg = findViewById(R.id.rg);

        //添加fragmment
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new fragment1());
        mFragments.add(new fragment2());
        mFragments.add(new fragment3());
        mFragments.add(new fragment4());

        //获取FragmentPagerAdapter
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        };

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(adapter.getCount() - 1);//设置fragment显示数量
        mViewPager.setCurrentItem(position);                            //设置初始显示页面
        RadioButton radioButton = (RadioButton) mRg.getChildAt(position);
        radioButton.setChecked(true);

        //监听mViewPager滑动事件
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton) mRg.getChildAt(position);
                radioButton.setChecked(true);
                mFragments.get(position).onAttach(MainActivity.this);
            }
        });

        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (!rb.isChecked()) {
                    return;
                }
                switch (checkedId) {
                    case R.id.rb_weixin:
                        position = 0;
                        break;

                    case R.id.rb_tongxinlu:
                        position = 1;
                        break;

                    case R.id.rb_faxian:
                        position = 2;
                        break;

                    case R.id.rb_wo:
                        position = 3;
                        break;

                    default:
                        position = 0;
                        break;

                }
                mViewPager.setCurrentItem(position);
            }
        });
    }

}
