package com.example.savithakamath.sdcardpreferences;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {
    private EditText textBox;
    private static final int READ_BLOCK_SIZE=100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox=(EditText)findViewById(R.id.txtText);
        Button saveBtn=(Button)findViewById(R.id.btnSave);
        Button loadBtn=(Button)findViewById(R.id.btnLoad);
        saveBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String str = textBox.getText().toString();
                try {
                    File sdCard = Environment.getExternalStorageDirectory();
                    File directory = new File(sdCard.getAbsolutePath() + "/MyFiles11");
                    directory.mkdirs();
                    File file = new File(directory, "textfile.txt");
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter osw=new OutputStreamWriter(fOut);
                    osw.write(str);
                    osw.flush();
                    osw.close();
                    Toast.makeText(getBaseContext(),"file saved successfully !",Toast.LENGTH_SHORT).show();
                    textBox.setText("");


                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    File sdCard = Environment.getExternalStorageDirectory();
                    File directory = new File(sdCard.getAbsolutePath() + "/MyFiles11");
                    directory.mkdirs();
                    File file = new File(directory, "textfile.txt");
                    FileInputStream fIn=new FileInputStream(file);
                    InputStreamReader isr=new InputStreamReader(fIn);
                    char[] inputBuffer=new char[READ_BLOCK_SIZE];
                    String s="";
                    int charRead;
                    while((charRead=isr.read(inputBuffer))>0) {
                        String readString=String.copyValueOf(inputBuffer,0,charRead);
                        s+=readString;
                        inputBuffer=new char[READ_BLOCK_SIZE];
                    }

                    textBox.setText(s);
                    Toast.makeText(getBaseContext(),"file loaded successfully!",Toast.LENGTH_SHORT).show();
                } catch (IOException ioe) {
                    ioe.printStackTrace();

                }
            }
        });

    }



}
