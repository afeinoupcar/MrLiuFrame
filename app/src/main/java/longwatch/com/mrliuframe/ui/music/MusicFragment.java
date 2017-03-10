package longwatch.com.mrliuframe.ui.music;

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

public class MusicFragment extends BaseFragment{
    private TabLayout mTabLayout;
    private ViewPager mVp;
    private LocalMusicFragment localMusicFragment=new LocalMusicFragment();
    private TopMusicFragment topMusicFragment=new TopMusicFragment();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initView(View parent) {
        mTabLayout=(TabLayout)parent.findViewById(R.id.musicfra_tablayout);
        mVp=(ViewPager)parent.findViewById(R.id.musicfra_vp);
    }

    @Override
    protected void initData(Bundle bundle) {
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        final String[] arr = {"本地音乐", "排行榜"};
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
                if(position==0){
                    return localMusicFragment;
                }else{
                    return topMusicFragment;
                }
            }

            @Override
            public int getCount() {
                return arr.length;
            }
        });
        mTabLayout.setupWithViewPager(mVp);
    }
}
