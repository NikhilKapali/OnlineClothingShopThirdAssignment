package com.example.thirdassignment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    EditText fname,lname,address,phone,username, password;
    RadioGroup gender;
    Button register;
    RadioButton male,female,other;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String getGender;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        fname= view.findViewById(R.id.et_fname);
        lname= view.findViewById(R.id.et_lname);
        address= view.findViewById(R.id.et_address);
        phone= view.findViewById(R.id.et_number);
        username= view.findViewById(R.id.et_username_res);
        password= view.findViewById(R.id.et_password_res);

        male=view.findViewById(R.id.rb_male);
        female=view.findViewById(R.id.rb_female);
        other=view.findViewById(R.id.rb_other);
        gender=view.findViewById(R.id.rg_gender);

        register=view.findViewById(R.id.btn_register);
        register.setOnClickListener(this);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rb_male){
                    getGender=male.getText().toString();
                }
                else if(checkedId==R.id.rb_female){
                    getGender=female.getText().toString();
                }

                else if(checkedId==R.id.rb_other){
                    getGender=other.getText().toString();
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register){
                if (validate()){
                    preferences=getActivity().getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                    editor=preferences.edit();
                    String getfname=fname.getText().toString();
                    String getlname=lname.getText().toString();
                    String getemail=address.getText().toString();
                    String getphone=phone.getText().toString();
                    String getusername=username.getText().toString();
                    String getpassword=password.getText().toString();

                    editor.putString("FNAME",getfname).commit();
                    editor.putString("LNAME",getlname).commit();
                    editor.putString("EMAIL",getemail).commit();
                    editor.putString("PHONE",getphone).commit();
                    editor.putString("GENDER",getGender).commit();
                    editor.putString("USERNAME",getusername).commit();
                    editor.putString("PASSWORD",getpassword).commit();
                    Toast.makeText(getActivity(), "Details of User Saved", Toast.LENGTH_SHORT).show();
                    Intent tent=new Intent(getActivity(),MainActivity.class);
                    startActivity(tent);
                }
                else{
                    Toast.makeText(getActivity(), "Something went wrong!! Please try again", Toast.LENGTH_SHORT).show();
                }
        }

    }
    private boolean validate(){
        if(TextUtils.isEmpty(fname.getText().toString())){
            fname.setError("Enter First name");
            fname.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(lname.getText().toString())){
            lname.setError("Enter Last name");
            lname.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(address.getText().toString())){
            address.setError("Enter Email");
            address.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(phone.getText().toString())){
            phone.setError("Enter Phone");
            phone.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(username.getText().toString())){
            username.setError("Enter Username");
            username.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Enter password");
            password.requestFocus();
            return false;
        }

        return true;
    }
}
