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
import com.example.calculationtest.databinding.FragmentWelcomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {


    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;
        myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        myViewModel.generator();
        final FragmentQuestionBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_question,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        final StringBuilder builder = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button2:
                        builder.append(1);
                        break;
                    case R.id.button3:
                        builder.append(2);
                        break;
                    case R.id.button4:
                        builder.append(3);
                        break;
                    case R.id.button5:
                        builder.append(4);
                        break;
                    case R.id.button6:
                        builder.append(5);
                        break;
                    case R.id.button7:
                        builder.append(6);
                        break;
                    case R.id.button8:
                        builder.append(7);
                        break;
                    case R.id.button9:
                        builder.append(8);
                        break;
                    case R.id.button10:
                        builder.append(9);
                        break;
                    case R.id.button11:
                        builder.setLength(0);
                        break;
                    case R.id.button12:
                        builder.append(0);
                        break;
                }
                if (builder.length() == 0){
                    binding.textView7.setText("答错了");
                }else {
                    binding.textView7.setText(builder.toString());
                }
            }
        };

        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.button10.setOnClickListener(listener);
        binding.button11.setOnClickListener(listener);
        binding.button12.setOnClickListener(listener);
        binding.button13.setOnClickListener(listener);
        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(builder.toString()).intValue() == myViewModel.getAnswer().getValue()){
                    myViewModel.answerCorrect();
                    builder.setLength(0);
                    binding.textView7.setText("YES");
                }else {
                    NavController controller= Navigation.findNavController(v);
                    if (myViewModel.flag){
                        controller.navigate(R.id.action_questionFragment_to_succeedFragment);
                        myViewModel.flag = false;
                        myViewModel.save();
                    }else {
                        controller.navigate(R.id.action_questionFragment_to_loseFragment);
                    }
                }
            }
        });

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_question, container, false);
    }

}
