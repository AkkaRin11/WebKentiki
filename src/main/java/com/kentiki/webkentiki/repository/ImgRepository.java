package com.kentiki.webkentiki.repository;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import com.kentiki.webkentiki.model.Img;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;


@Repository
public class ImgRepository{

    public String getRandomNameOfImg(){
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("Img").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            int nm = (int) (Math.random() * documents.size());
            return documents.get(nm).getId();

//            for (QueryDocumentSnapshot document : documents) {
//                System.out.println(document.getId() + " " + document.toObject(Img.class));
//            }

        } catch (Exception e){
            System.out.println(e);

            //Затычка
            return "1";
        }
    }

    public String getDateOfImg(String name) {
        String rightDate = "00.00.0000";


        try {
            Firestore db = FirestoreClient.getFirestore();


            DocumentSnapshot DS = db.collection("Img").document(name).get().get();
            Img r = DS.toObject(Img.class);
            rightDate = r.getDate();


            //System.out.println(r.getDate());


            //db.collection("Img").document("2").set(new Img("12.11.2004"));


//            ApiFuture<DocumentSnapshot> future =  db.collection("Img").document("1").get();
//
//            DocumentSnapshot document = future.get();
//            if (document.exists()) {
//                System.out.println("Document data: " + document.getData());
//            } else {
//                System.out.println("No such document!");
//            }

        } catch (Exception e){
            System.out.println(e);
        }

        return rightDate;
    }
}
