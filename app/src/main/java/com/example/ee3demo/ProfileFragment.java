package com.example.ee3demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ProfileFragment extends Fragment {
    private TextView textView;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        String testUrl = "https://ujoniobvifxkbhhfopwu.supabase.co/rest/v1/customer?apikey=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVqb25pb2J2aWZ4a2JoaGZvcHd1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzk4ODU2NDEsImV4cCI6MjA1NTQ2MTY0MX0.aWD395KJsOXsEZKoV-xWx512ypVkSMkMPhFVOa7IYhc";
        // Initialize TextView
        textView = view.findViewById(R.id.profile_text);
        
        // Initialize RequestQueue
        requestQueue = Volley.newRequestQueue(requireContext());
        
        // Create GET request
        String url = testUrl; // Test API endpoint
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display response in TextView
                    textView.setText(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Display error message
                    textView.setText("Error: " + error.getMessage());
                }
            });

        // Add request to queue
        requestQueue.add(stringRequest);
        
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (requestQueue != null) {
            requestQueue.cancelAll(this);
        }
    }
} 