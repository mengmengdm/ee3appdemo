package com.example.ee3demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.Size;

public class ScanFragment extends Fragment {
    private TextView resultTextView;
    private DecoratedBarcodeView barcodeView;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                resultTextView.setText("Scan result: " + result.getText());
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);

        resultTextView = view.findViewById(R.id.text_scan_result);
        barcodeView = view.findViewById(R.id.barcode_scanner);

        // Set scanner frame size based on screen dimensions
        DisplayMetrics displayMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        
        int frameWidth = (int) (screenWidth * 0.8); // 80% of screen width
        int frameHeight = (int) (screenHeight * 0.5); // 50% of screen height
        barcodeView.getBarcodeView().setFramingRectSize(new Size(frameWidth, frameHeight));

        // Check camera permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            startScanning();
        }

        return view;
    }

    private void startScanning() {
        barcodeView.decodeContinuous(callback);
    }

    @Override
    public void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning();
            } else {
                Toast.makeText(getActivity(), "Camera permission is required for scanning", Toast.LENGTH_LONG).show();
            }
        }
    }
} 