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

import com.example.calculationtest.databinding.FragmentQuestionBinding;
import com.example.calculationtest.databinding.FragmentSucceedBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SucceedFragment extends Fragment {


    public SucceedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;
        myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        final FragmentSucceedBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_succeed,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_succeedFragment_to_welcomeFragment);
            }
        });
        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_succeed, container, false);
    }

}
