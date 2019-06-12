package com.food.vpman.nutrientsapi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String JsonUrl="https://api.edamam.com/api/nutrition-data?app_id=7e3991a9&app_key=429f60e87d412043f45a283662452e26&ingr=1%20large%20apple";
    EditText editText;
    private int splash=2000;
    String query;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.edit_search);


        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()
                            [DRAWABLE_RIGHT].getBounds().width())) {

                        if (editText.getText().length() != 0) {
                            query = editText.getText().toString();


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                    intent.putExtra("search", query);
                                    startActivity(intent);

                                  //  getActivity().overridePendingTransition(R.animator.activity_in, R.animator.activity_out);
                                }
                            }, splash);
                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Text to search item", Toast.LENGTH_LONG).show();
                        }


                        return true;
                    }
                }
                return false;
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (editText.getText().length() != 0) {



                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                query = editText.getText().toString();
                                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                intent.putExtra("search", query);
                                startActivity(intent);

                                //getActivity().overridePendingTransition(R.animator.activity_in, R.animator.activity_out);
                            }
                        }, splash);


                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a text", Toast.LENGTH_LONG).show();


                    }
                }


                return false;
            }
        });


       // loadWall();
    }


    public void loadWall() {




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {




                //   view.setAdapter(adapter1);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, JsonUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject obj = new JSONObject(response);
                                    Toast.makeText(MainActivity.this,String.valueOf(obj.getDouble("calories")),Toast.LENGTH_LONG).show();
                                    Toast.makeText(MainActivity.this,String.valueOf(obj.getDouble("totalWeight")),Toast.LENGTH_LONG).show();

                                    JSONObject object=obj.getJSONObject("totalNutrients");
                                    JSONObject object1=object.getJSONObject("FAT");
                                    String quanitiy= String.valueOf(object1.getDouble("quantity"));
                                    Toast.makeText(MainActivity.this, quanitiy, Toast.LENGTH_SHORT).show();
                                  /*  //    Log.e("mil gaya",String.valueOf(obj));

                                    JSONArray wallArray = obj.getJSONArray("hits");
                                    for (int i = 0; i < wallArray.length(); i++) {
                                        JSONObject wallObj = wallArray.getJSONObject(i);
                                        //JSONObject object=wallObj.getJSONObject("src");
                                        Wall wall = new Wall(wallObj.getString("largeImageURL"), wallObj.getString("webformatURL"));
                                        walls.add(wall);

                                    }
                                    Random random = new Random();
                                    int n = random.nextInt(walls.size() - 1);

                                    //   imageView=view.findViewById(R.id.imgView1);

                                    // Glide.with(getActivity()).load(walls.get(n).getOriginal()).into(imageView);

                                    // walList.add(walls.get(n).getOriginal());
                                    // pos++;

                                    if (pos<0)
                                    {


                                    }
                                    else if (pos < walList.size()) {
                                        Glide.with(getActivity()).load(walList.get(pos)).into(imageView);
                                        setWall = walList.get(pos);
                                    }
                                    else {
                                        n = random.nextInt(walls.size() - 1);
                                        setWall = walls.get(n).getOriginal();
                                        walList.add(walls.get(n).getOriginal());
                                        Glide.with(getActivity()).load(walls.get(n).getOriginal()).into(imageView);
                                        // imageView1.setVisibility(View.GONE);

                                    }

                                    // setWall = walls.get(n).getOriginal();

                                    imageView.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
                                            return gestureDetector.onTouchEvent(event);
                                        }

                                    });
*/

                                } catch (JSONException e) {
                                    Log.e("Nhi mila ", String.valueOf(e));
                                    e.getStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.e("Nhi mila ", String.valueOf(error));

                            }
                        });
                RequestQueue requestQueue = (RequestQueue) Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);

            }
        },splash);



    }
}
