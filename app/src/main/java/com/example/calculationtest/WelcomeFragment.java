package com.example.calculationtest;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculationtest.databinding.FragmentSucceedBinding;
import com.example.calculationtest.databinding.FragmentWelcomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {


    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel;
        myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentWelcomeBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_welcomeFragment_to_questionFragment);
            }
        });
        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

}
