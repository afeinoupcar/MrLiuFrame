package longwatch.com.mrliuframe.ui.music;

import android.os.Bundle;
import android.view.View;

import com.longwatch.util.ui.BaseFragment;

import longwatch.com.mrliuframe.R;
import longwatch.com.mrliuframe.ui.ActivityLauncher;

/**
 * Created by a on 2017/2/23.
 */

public class TopMusicFragment extends BaseFragment implements View.OnClickListener {
//    private View oumei;
//    private View neidi;
//    private View gangtai;
//    private View hanguo;
//    private View riben;
//    private View minyao;
//    private View yaogun;
//    private View xiaoliang;
//    private View rege;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music_top;
    }

    @Override
    protected void initView(View parent) {
        parent.findViewById(R.id.musictop_fra_oumei).setOnClickListener(this);
        parent.findViewById(R.id.musictop_fra_neidi).setOnClickListener(this);
        parent.findViewById(R.id.musictop_fra_gangtai).setOnClickListener(this);
        parent.findViewById(R.id.musictop_fra_hanguo).setOnClickListener(this);
        parent.findViewById(R.id.musictop_fra_riben).setOnClickListener(this);
        parent.findViewById(R.id.musictop_fra_minyao).setOnClickListener(this);
        parent.findViewById(R.id.musictop_fra_yaogun).setOnClickListener(this);
        parent.findViewById(R.id.musictop_fra_xiaoliang).setOnClickListener(this);
        parent.findViewById(R.id.musictop_fra_rege).setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.musictop_fra_oumei:
        ActivityLauncher.viewMusicTopContent(getActivity(),3,"欧美");
        break;
    case R.id.musictop_fra_neidi:
        ActivityLauncher.viewMusicTopContent(getActivity(),5,"内地");
        break;
    case R.id.musictop_fra_gangtai:
        ActivityLauncher.viewMusicTopContent(getActivity(),6,"港台");
        break;
    case R.id.musictop_fra_hanguo:
        ActivityLauncher.viewMusicTopContent(getActivity(),16,"韩国");
        break;
    case R.id.musictop_fra_riben:
        ActivityLauncher.viewMusicTopContent(getActivity(),17,"日本");
        break;
    case R.id.musictop_fra_minyao:
        ActivityLauncher.viewMusicTopContent(getActivity(),18,"民谣");
        break;
    case R.id.musictop_fra_yaogun:
        ActivityLauncher.viewMusicTopContent(getActivity(),19,"摇滚");
        break;
    case R.id.musictop_fra_xiaoliang:
        ActivityLauncher.viewMusicTopContent(getActivity(),23,"销量");
        break;
    case R.id.musictop_fra_rege:
        ActivityLauncher.viewMusicTopContent(getActivity(),26,"热歌");
        break;

}
    }
}
