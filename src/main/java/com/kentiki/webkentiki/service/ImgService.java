package com.kentiki.webkentiki.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.kentiki.webkentiki.model.Answer;
import com.kentiki.webkentiki.repository.ImgRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class ImgService {

    private ImgRepository imgRepository;

    public void putDate(String name, String date){
        try {
            Firestore db = FirestoreClient.getFirestore();

            Map<String, Object> docData = new HashMap<>();
            docData.put("date", date);

            db.collection("Img").document(name).set(docData);


//            for (QueryDocumentSnapshot document : documents) {
//                System.out.println(document.getId() + " " + document.toObject(Img.class));
//            }

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public String getImgName(String imgNumber){
        //imgRepository.setCurImg(numberOfImg);
        String str = "/img/" + imgNumber + ".jpg";

        return str;
    }

    public String getRightAnswer(String imgName) {
        return imgRepository.getDateOfImg(imgName);
    }

    public boolean isAnswerTrue(Answer answer, String imgName) {
        return (answer.day + "." + answer.month + "." + answer.year).equals(imgRepository.getDateOfImg(imgName));
    }

}
