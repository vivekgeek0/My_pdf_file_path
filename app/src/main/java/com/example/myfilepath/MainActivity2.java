package com.example.myfilepath;

import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv1, tv2;
        Button btn  ;

        tv1 = findViewById(R.id.textView_1);
        tv2 = findViewById(R.id.textView_2);
        btn = findViewById(R.id.button);

        tv1.setText(getIntent().getStringExtra("filename"));
        tv2.setText(getIntent().getStringExtra("filepath"));

        String ps = getIntent().getStringExtra("filename");
        String pn = getIntent().getStringExtra("filepath");

        printPdf(this,pn,ps);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // printPdf(pn,ps);


            }
        });


    }

    /*public static void printPdf(View.OnClickListener context, String filePath, String jobName) {
        PrintManager printManager = (PrintManager) context.toString();

        if (printManager != null) {
            PrintDocumentAdapter printAdapter = new WebViewPrintAdapter((Context) context, filePath);
            PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
        }
    }*/
    public static void printPdf(Context context, String filePath, String jobName) {
        PrintManager printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);

        if (printManager != null) {
            PrintDocumentAdapter printAdapter = new WebViewPrintAdapter(context, filePath);
            PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
        }
    }

    private static class WebViewPrintAdapter extends PrintDocumentAdapter {
        private final Context mContext;
        private final String mFilePath;

        public WebViewPrintAdapter(Context context, String filePath) {
            mContext = context;
            mFilePath = filePath;
        }

        @Override
        public void onWrite(PageRange[] pages, android.os.ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {
            // Implement printing logic here
        }

        @Override
        public void onFinish() {
            // Clean up resources here
            Toast.makeText(mContext,"This is a short toast message", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onStart() {
            // Initialize printing here
        }

        @Override
        public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {

        }
    }
}