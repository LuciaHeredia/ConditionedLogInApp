package com.example.loginapp.Fragments;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.loginapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogIn extends Fragment {
    private EditText tPassword;

    public LogIn() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_log_in, container, false);

        Button bLog = v.findViewById(R.id.button_log);
        tPassword = v.findViewById(R.id.pass);

        bLog.setOnClickListener(v1 -> {
            String password = tPassword.getText().toString().trim();

            if(checkPasswordValid(password))
                Navigation.findNavController(v).navigate(R.id.action_logIn_to_success);
            else
                tPassword.setError("Wrong Password.");

        });

        return v;
    }

    public boolean checkPasswordValid(String pass) {
        return isMuteModeOn() &&
                isAirplaneModeOn(requireActivity().getApplicationContext()) &&
                isDayIncludedInPassword(pass);
    }

    private boolean isMuteModeOn() {
        AudioManager audioManager = (AudioManager) requireActivity().getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT;
    }

    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    private boolean isDayIncludedInPassword(String pass) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        String dayOfTheWeek = sdf.format(new Date()).toLowerCase();
        return pass.contains(dayOfTheWeek);
    }

}
