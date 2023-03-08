package edu.esprit.ressources;

import java.io.*;
import java.net.*;
import java.util.Base64;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.*;

public class ImgurUploader extends Application {

    private static final String IMGUR_API_URL = "https://api.imgur.com/3/image";
    public static final String ALBUM_API_URL = "https://api.imgur.com/3/album";
    private static final String IMGUR_ACCESS_TOKEN = "7e2478f228cf1355ed6bf564673529541e4e72c3";
    public static final int MAX_UPLOAD_ATTEMPTS = 3;

    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) {
        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.setOnAction(event -> chooseImage());

        imageView = new ImageView();

        VBox root = new VBox(10, chooseImageButton, imageView);
        root.setPrefSize(400, 400);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
//            imageView.setImage(image);
            return uploadImage(selectedFile);
        }
        return null;
    }

    private static String uploadImage(File imageFile) {
        try {
            URL url = new URL(IMGUR_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + IMGUR_ACCESS_TOKEN);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");

            String imageData = Base64.getEncoder().encodeToString(readImageFile(imageFile));
            String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imageData, "UTF-8");

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes("UTF-8"));
            outputStream.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            JSONObject response = new JSONObject(responseBuilder.toString());
            JSONObject dataObj = response.getJSONObject("data");
            String link = dataObj.getString("link");

            System.out.println("Image uploaded successfully!");
//            System.out.println(responseBuilder.toString());
            System.out.println("Link: " + link);
            return link;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static byte[] readImageFile(File imageFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(imageFile);
        byte[] imageData = new byte[(int) imageFile.length()];
        inputStream.read(imageData);
        inputStream.close();
        return imageData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
