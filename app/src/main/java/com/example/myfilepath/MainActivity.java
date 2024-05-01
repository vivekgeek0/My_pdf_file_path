package com.example.myfilepath;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FileAdapter fileAdapter;
    private ArrayList<File> fileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        Button refreshButton = findViewById(R.id.refreshButton);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //fileAdapter = new FileAdapter(fileList, this);
        fileAdapter = new FileAdapter(fileList,getApplicationContext());
        recyclerView.setAdapter(fileAdapter);

        // Set onClickListener for refresh button
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFileList();
            }
        });

        // Initial file list
        updateFileList();
    }

    private void updateFileList() {
        String directoryPath = "/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Documents"; // Change this to the desired directory
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                // Sort files by last modified time
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File file1, File file2) {
                        return Long.compare(file2.lastModified(), file1.lastModified());
                    }
                });

                // Clear and update fileList
                fileList.clear();
                fileList.addAll(Arrays.asList(files));

                // Notify adapter of data set change
                fileAdapter.notifyDataSetChanged();
            }
        }
    }
}



/*
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private FileAdapter fileAdapter;
    private ArrayList<File> fileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        Button refreshButton = findViewById(R.id.refreshButton);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fileAdapter = new FileAdapter(fileList, this);
        recyclerView.setAdapter(fileAdapter);

        // Set onClickListener for refresh button
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFileList();
            }
        });

        // Initial file list
        updateFileList();
    }

    private void updateFileList() {
        String directoryPath = "/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Documents"; // Change this to the desired directory
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                // Sort files by name
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File file1, File file2) {
                        return file1.getName().compareToIgnoreCase(file2.getName());
                    }
                });

                // Sort files by last modified time
                Arrays.sort(files, new Comparator<File>() {
                    @Override
                    public int compare(File file1, File file2) {
                        return Long.compare(file2.lastModified(), file1.lastModified());
                    }
                });

                // Clear and update fileList
                fileList.clear();
                for (File file : files) {
                    fileList.add(file);
                }

                // Notify adapter of data set change
                fileAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Open With")) {
            openFileWith(item);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void openFileWith(MenuItem item) {
        // Get the position of the selected item in the RecyclerView
        int position = ((RecyclerView.ViewHolder) recyclerView.findContainingViewHolder(item.getActionView())).getAdapterPosition();

        // Get the selected file
        File selectedFile = fileList.get(position);

        // Create an intent to open the file
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(selectedFile), "application/pdf"); // Change the MIME type as per your file type

        // Verify that the intent resolves to an activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Open File with"));
        } else {
            Toast.makeText(this, "No suitable application found to open the file", Toast.LENGTH_SHORT).show();
        }
    }

}
*/

