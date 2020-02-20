package com.example.calculationtest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class MyViewModel extends AndroidViewModel {
    SavedStateHandle handle;
    private static String key_high_score = "KEY_HIGH_SCORE";
    private static String key_left_number = "KEY_LEFT_NUMBER";
    private static String key_right_number = "KEY_RIGHT_NUMBER";
    private static String key_operator = "KEY_OPERATOR";
    private static String key_answer = "KEY_ANSWER";
    private static String key_save = "save_shp_data_name";
    private static String key_current = "KEY_CURRENT";
    boolean flag = false;
    public MyViewModel(@NonNull Application application,SavedStateHandle handle) {
        super(application);
        if (!handle.contains(key_high_score)){
            SharedPreferences shp = getApplication().getSharedPreferences(key_save, Context.MODE_PRIVATE);
            handle.set(key_high_score,shp.getInt(key_high_score,0));
            handle.set(key_left_number,0);
            handle.set(key_right_number,0);
            handle.set(key_answer,0);
            handle.set(key_current,0);
            handle.set(key_operator,'+');
        }
        this.handle = handle;
    }

    public MutableLiveData<Integer> getLeftNumber(){
        return handle.getLiveData(key_left_number);
    }
    public MutableLiveData<Integer> getRightNumber(){
        return handle.getLiveData(key_right_number);
    }
    public MutableLiveData<Integer> getCurrentNumber(){
        return handle.getLiveData(key_current);
    }
    public MutableLiveData<String> getOperator(){
        return handle.getLiveData(key_operator);
    }
    public MutableLiveData<Integer> getAnswer(){
        return handle.getLiveData(key_answer);
    }
    public MutableLiveData<Integer> getHighScore(){
        return handle.getLiveData(key_high_score);
    }

    void generator(){
        int level = 20;
        Random random = new Random();
        int x,y;
        x = random.nextInt(level)+1;
        y = random.nextInt(level)+1;
        if (x%2==0){
            getOperator().setValue("+");
            if (x>y){
                getAnswer().setValue(x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x-y);
            }else {
                getAnswer().setValue(y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y-x);
            }
        }else {
            getOperator().setValue("-");
            if (x>y){
                getAnswer().setValue(x-y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);
            }else {
                getAnswer().setValue(y-x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x);
            }
        }
    }

    void save(){
        SharedPreferences shp = getApplication().getSharedPreferences(key_save,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(key_high_score,getHighScore().getValue());
        editor.apply();
    }

    void answerCorrect(){
        getCurrentNumber().setValue(getCurrentNumber().getValue()+1);
        if (getCurrentNumber().getValue()>getHighScore().getValue()){
            getHighScore().setValue(getCurrentNumber().getValue());
            flag = true;
            generator();
        }
    }
}
