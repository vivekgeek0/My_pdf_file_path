package com.example.myfilepath;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    private ArrayList<File> fileList;
    private Context context;

    public FileAdapter(ArrayList<File> fileList, Context context) {
        this.fileList = fileList;
        this.context = context;
    }

    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout file
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item_2, parent, false);
        return new FileViewHolder(view);
    }


    /*@NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new FileViewHolder(view);
    }*/

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        File file = fileList.get(position);



        holder.fileNameTextView.setText(file.getName());
        holder.fileLastModifiedTextView.setText(getFormattedDate(file.lastModified()));

        // Set click listener for each item

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile(file);
            }
        } */

        holder.fileNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MainActivity2.class);

                intent.putExtra("filename",file.getName());

                intent.putExtra("filepath",file.getAbsolutePath());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


                //openFile(file);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    static class FileViewHolder extends RecyclerView.ViewHolder {
        TextView fileNameTextView;
        TextView fileLastModifiedTextView;

        FileViewHolder(@NonNull View itemView) {
            super(itemView);
            fileNameTextView = itemView.findViewById(android.R.id.text1);
            fileLastModifiedTextView = itemView.findViewById(android.R.id.text2);
        }
    }

    private String getFormattedDate(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(timeInMillis);
    }

    private void openFile(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf"); // Change the MIME type as per your file type

        // Verify that the intent resolves to an activity
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(intent, "Open File with"));
        } else {
            // No suitable application found to open the file
        }
    }
}
