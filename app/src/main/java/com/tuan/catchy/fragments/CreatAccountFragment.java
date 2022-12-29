package com.tuan.catchy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.tuan.catchy.FragmentReplacerActivity;
import com.tuan.catchy.R;


public class CreatAccountFragment extends Fragment {

    private EditText nameET, emailET, passwordET, confirmPasswordET;
    private TextView loginTV;
    private Button signUpBT;
    private FirebaseAuth auth;

    public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public CreatAccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_creat_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        clickListener();
    }

    private void init(View view)
    {
        nameET = view.findViewById(R.id.nameET);
        emailET = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        confirmPasswordET = view.findViewById(R.id.confirmPassET);
        loginTV = view.findViewById(R.id.loginTv);
        signUpBT = view.findViewById(R.id.signUpBtn);

        auth = FirebaseAuth.getInstance();
    }

    private void clickListener()
    {
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentReplacerActivity) getActivity()).setFragment(new LoginFragment());
            }
        });

        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();

                if(name.isEmpty() || name.equals(" ")){
                    nameET.setError("Please input valid name");
                    return;
                }

                if(email.isEmpty() || !email.matches(EMAIL_REGEX)){
                    nameET.setError("Please input valid email");
                    return;
                }

                if(password.isEmpty() || password.length() <6){
                    nameET.setError("Please input valid password");
                    return;
                }
            }
        });
    }
}