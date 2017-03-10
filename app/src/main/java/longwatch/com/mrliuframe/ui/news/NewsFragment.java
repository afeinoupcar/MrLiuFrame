package longwatch.com.mrliuframe.ui.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.longwatch.util.ui.BaseFragment;

import longwatch.com.mrliuframe.R;

/**
 * Created by a on 2017/2/9.
 */

public class NewsFragment extends BaseFragment{
    private TabLayout mTabLayout;
    private ViewPager mVp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View parent) {
        mTabLayout=(TabLayout)parent.findViewById(R.id.newsfra_tablayout);
        mVp=(ViewPager)parent.findViewById(R.id.newsfra_vp);
    }

    @Override
    protected void initData(Bundle bundle) {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final String[] arr = {"推荐", "热点", "本地", "视频", "社会", "娱乐", "科技", "汽车", "体育", "财经","军事"};
        mVp.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return arr[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Override
            public Fragment getItem(int position) {
                NewsContentFragment mNewsContentFragment = new NewsContentFragment();
                Bundle b=new Bundle();
                b.putInt("page",position);
                mNewsContentFragment.setArguments(b);
                return mNewsContentFragment;
            }

            @Override
            public int getCount() {
                return arr.length;
            }
        });
        mTabLayout.setupWithViewPager(mVp);
    }
}
