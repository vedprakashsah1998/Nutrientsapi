package com.food.vpman.nutrientsapi;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

   private String JsonUrl="https://api.edamam.com/api/nutrition-data?app_id=7e3991a9&app_key=429f60e87d412043f45a283662452e26&ingr=1%20large%20";
    private int splash=2000;
    private String nn[]={"FAT","CHOCDF","SUGAR", "PROCNT", "CA","FE","VITA_RAE","VITC","VITB6A","TOCPHA","VITK1"};
    private int ids[]={R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5,R.id.tv6,R.id.tv7,
            R.id.tv8,R.id.tv9,R.id.tv10,R.id.tv11,R.id.tv12,R.id.tv13,R.id.tv14,
            R.id.tv15,R.id.tv16,R.id.tv17,R.id.tv18,R.id.tv19,R.id.tv20,R.id.tv21,R.id.tv22,
            R.id.tv23,R.id.tv24,R.id.tv25,R.id.tv26,R.id.tv27,R.id.tv28,R.id.tv29,
            R.id.tv30,R.id.tv31,R.id.tv32,R.id.tv33,R.id.tv34};
    List<NutrientsCategory> nc=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String query = getIntent().getExtras().getString("search");

         JsonUrl="https://api.edamam.com/api/nutrition-data?app_id=7e3991a9&app_key=429f60e87d412043f45a283662452e26&ingr=1%20large%20"+query;

        TextView textView=findViewById(R.id.tv);
        textView.setText(query);
        loadWall();
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
                                  //  Toast.makeText(Main2Activity.this,String.valueOf(obj.getDouble("calories")),Toast.LENGTH_LONG).show();
                                    //Toast.makeText(Main2Activity.this,String.valueOf(obj.getDouble("totalWeight")),Toast.LENGTH_LONG).show();

                                    TextView textView3=findViewById(R.id.tv36);
                                    textView3.setText(String.valueOf(obj.getDouble("calories")));

                                    TextView textView4=findViewById(R.id.tv38);
                                    textView4.setText(String.valueOf(obj.getDouble("totalWeight")));

                                    JSONObject object=obj.getJSONObject("totalNutrients");
                                   // JSONObject object1=object.getJSONObject("FAT");
                                    for (int i=0;i<nn.length;i++)
                                    {
                                        JSONObject object1=object.getJSONObject(nn[i]);
                                        NutrientsCategory nutrientsCategory=new NutrientsCategory(object1.
                                                getString("label"),object1.getString("unit")
                                                ,String.valueOf(object1.getDouble("quantity")));
                                      /*  if(String.valueOf(object1.getDouble("quantity")==))
                                        {

                                        }*/

                                        nc.add(nutrientsCategory);



                                    }
                                    int j=0;
                                    for (int i=0;i<ids.length;i=i+3)
                                    {
                                        TextView textView=findViewById(ids[i]);
                                        textView.setText(nc.get(j).getLabel());
                                        TextView textView1=findViewById(ids[i+2]);
                                        textView1.setText(nc.get(j).getUnit());
                                        TextView textView2=findViewById(ids[i+1]);
                                        if (nc.get(j).getQuantity().length()>6)
                                        {
                                            textView2.setText(nc.get(j).getQuantity().substring(0,6));
                                        }
                                        else
                                        {
                                            textView2.setText(nc.get(j).getQuantity());
                                        }

                                        j++;
                                    }




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
                RequestQueue requestQueue = (RequestQueue) Volley.newRequestQueue(Main2Activity.this);
                requestQueue.add(stringRequest);

            }
        },splash);



    }
}
