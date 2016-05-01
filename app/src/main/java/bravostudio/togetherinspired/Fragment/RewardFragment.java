package bravostudio.togetherinspired.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import bravostudio.togetherinspired.Adapter.RewardAdapter;
import bravostudio.togetherinspired.R;

/**
 * Created by jouvyap on 4/27/16.
 */
public class RewardFragment extends Fragment {

    public RewardFragment(){

    }

    public static RewardFragment newInstance(){
        RewardFragment rewardFragment = new RewardFragment();
        return rewardFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        gridview.setAdapter(new RewardAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
