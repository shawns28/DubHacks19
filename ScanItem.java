package com.example.binnahacks19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.api.services.vision.v1.model.AnnotateImageRequest;
//import com.google.api.services.vision.v1.model.AnnotateImageResponse;
//import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
//import com.google.api.services.vision.v1.model.EntityAnnotation;
//import com.google.api.services.vision.v1.model.Feature;
//import com.google.api.services.vision.v1.model.Feature.*;
//import com.google.api.services.vision.v1.model.Image;
//import com.google.api.services.vision.v1.model.Image.*;
//import com.google.api.services.vision.*;
//import com.google.api.services.vision.v1.*;
//import com.google.api.services.vision.v1.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class ScanItem extends AppCompatActivity {

    private EditText mEditText1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_item);

        mEditText1 = (EditText)findViewById(R.id.editText1);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmScreen(mEditText1.getText().toString());
            }
        });
    }

//    public void readImage(String fileName) {
//        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
//
//            // Reads the image file into memory
//            Path path = Paths.get(fileName);
//            byte[] data = Files.readAllBytes(path);
//            ByteString imgBytes = ByteString.copyFrom(data);
//
//            // Builds the image annotation request
//            List<AnnotateImageRequest> requests = new ArrayList<>();
//            Image img = Image.newBuilder().setContent(imgBytes).build();
//            Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
//            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
//                    .addFeatures(feat)
//                    .setImage(img)
//                    .build();
//            requests.add(request);
//
//            // Performs label detection on the image file
//            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
//            List<AnnotateImageResponse> responses = response.getResponsesList();
//
//            for (AnnotateImageResponse res : responses) {
//                if (res.hasError()) {
//                    System.out.printf("Error: %s\n", res.getError().getMessage());
//                    return;
//                }
//
//                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
//                    annotation.getAllFields().forEach((k, v) -> System.out.printf("%s : %s\n", k, v.toString()));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void openConfirmScreen(String textBoxUrl) {
        //readImage(textBoxUrl);
        Intent intent = new Intent(this, ConfirmScreen.class);
        ArrayList<String> itemList = getIntent().getStringArrayListExtra("ItemList");
        intent.putStringArrayListExtra("ItemList", itemList);
        startActivity(intent);
    }
}