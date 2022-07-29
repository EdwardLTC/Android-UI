package com.edward.assigment.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;
import com.edward.assigment.adapter.ExpandableAdapter;
import com.edward.assigment.adapter.GridCustom;
import com.edward.assigment.databinding.FragmentSlideshowBinding;
import com.edward.assigment.model.ExpandableChild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<String> listDataGroup = new ArrayList<>();
        listDataGroup.add("Community resources");
        listDataGroup.add("help & support");
        listDataGroup.add("Setting & privacy");

        HashMap<String, List<String>> listDataChild = new HashMap<>();

        listDataChild.put(listDataGroup.get(0), new ArrayList<>(Arrays.asList("hong biet", "hong biet","hong biet")));
        listDataChild.put(listDataGroup.get(1), new ArrayList<>(Arrays.asList("Help center", "Support inbox","Report a problem","Safety","Terms & Policies")));
        listDataChild.put(listDataGroup.get(2), new ArrayList<>(Arrays.asList("Setting", "Device requests","Recent ad activity","Wifi & cellular performance")));

        binding.expandableListView.setAdapter(new ExpandableAdapter(requireContext(),listDataGroup,listDataChild));
        ArrayList<ExpandableChild> list = new ArrayList<>();
        list.add(new ExpandableChild("Groups", R.drawable.ic_baseline_group_24));
        list.add(new ExpandableChild("Feeds", R.drawable.ic_baseline_dynamic_feed_24));
        list.add(new ExpandableChild("Videos on watch", R.drawable.ic_baseline_ondemand_video_24));
        list.add(new ExpandableChild("Memories", R.drawable.ic_baseline_watch_later_24));
        list.add(new ExpandableChild("Dating", R.drawable.ic_baseline_heart_broken_24));
        list.add(new ExpandableChild("Gaming", R.drawable.ic_baseline_videogame_asset_24));

        binding.grid.setAdapter(new GridCustom(list,requireContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}