package com.example.tarea_3_carlos_abad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView lblComentarios;
    RequestQueue requestQueue;
    //private static final String URL = "https://gorest.co.in/public/v1/users";
    private static final String URL = "https://gorest.co.in/public/v1/comments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);

        initUI1();
        jsonObjectRequest();
    }


    private void initUI1() { lblComentarios = findViewById(R.id.lblComentarios);
    }

    private void jsonObjectRequest(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            int size = jsonArray.length();
                            for (int i = 0; i<size; i++){
                                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                                String ID = jsonObject.getString("id");
                                lblComentarios.append("ID: "+ID+"\n");

                                String name = jsonObject.getString("post_id");
                                lblComentarios.append("ID DEL MENSAJE: "+name+"\n");

                                String email = jsonObject.getString("name");
                                lblComentarios.append("NOMBRE: "+email+"\n");

                                String gender = jsonObject.getString("email");
                                lblComentarios.append("EMAIL: "+gender+"\n");

                                String status = jsonObject.getString("body");
                                lblComentarios.append("COMENTARIOS: "+status+"\n\n");
/*id
ID del mensaje
nombre
email
comentario*/
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}