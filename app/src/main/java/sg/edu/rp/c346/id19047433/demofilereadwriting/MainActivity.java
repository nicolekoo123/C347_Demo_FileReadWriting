package sg.edu.rp.c346.id19047433.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    String folderLocation_I;
    Button btnWrite, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Folder creation
        folderLocation_I = getFilesDir().getAbsolutePath() + "/MyFolder";

        File folder = new File(folderLocation_I);
        if (folder.exists() == false){
            boolean result = folder.mkdir();
            if (result == true){
                Log.d("File Read/Write", "Folder created");
            }
        }

        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String folderLocation_I = getFilesDir().getAbsolutePath() + "/MyFolder";
                    File targetFile_I = new File(folderLocation_I, "data.txt");
                    FileWriter writer_I = new FileWriter(targetFile_I, true);
                    writer_I.write("text data" + "\n");
                    writer_I.flush();
                    writer_I.close();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to write!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String folderLocation_I = getFilesDir().getAbsolutePath() + "/MyFolder";
                File targetFile = new File(folderLocation_I, "data.txt");
                if (targetFile.exists() == true){
                    String data ="";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null){
                            data += line + "\n";
                            line = br.readLine();
                        }
                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                }
            }
        });

    }
}