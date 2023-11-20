package com.example.codeforceapi;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.codeforceapi.ContestResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "VolleyExample";
    private static final String API_URL = "https://codeforces.com/api/contest.status?contestId=566&asManager=false&from=1&count=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = new ArrayList<>();
        HashMap<String, Map<String, Integer>> map = new HashMap<>();
        HashMap<String, Integer> innerMap = new HashMap<>();



        // Instantiate the RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a JsonObjectRequest or JsonArrayRequest based on your API response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response
                        try {
                            String status = response.getString("status");
                            JSONArray resultArray = response.getJSONArray("result");

                            // Process the result array
                            for (int i = 0; i < resultArray.length(); i++) {
                                JSONObject resultObject = resultArray.getJSONObject(i);

                                // Extract data as needed
                                int id = resultObject.getInt("id");
                                int contestId = resultObject.getInt("contestId");
                                long creationTimeSeconds = resultObject.getLong("creationTimeSeconds");
                                long relativeTimeSeconds = resultObject.getLong("relativeTimeSeconds");

                                JSONObject problemObject = resultObject.getJSONObject("problem");
                                int problemContestId = problemObject.getInt("contestId");
                                String problemIndex = problemObject.getString("index");


                                String problemName = problemObject.getString("name");

                                if (!map.containsKey(problemIndex)){
                                    innerMap.put(problemName, 1);
                                    map.put(problemIndex, innerMap);
                                    HashMap<String, Integer> innerMap = new HashMap<>();
                                }else{
                                    if(!map.get(problemIndex).containsKey(problemName)){
                                        innerMap.put(problemName, 1);
                                        map.put(problemIndex, innerMap);
                                        HashMap<String, Integer> innerMap = new HashMap<>();
                                    }
                                }
                                String problemType = problemObject.getString("type");
                                double problemPoints = problemObject.getDouble("points");
                                int problemRating = problemObject.getInt("rating");
                                if (!map.containsKey(problemIndex)){
                                    innerMap.put("rating", problemRating);
                                    map.put(problemIndex, innerMap);
                                    HashMap<String, Integer> innerMap = new HashMap<>();
                                }else{
                                    if(!map.get(problemIndex).containsKey(problemName)){
                                        innerMap.put("rating", problemRating);
                                        map.put(problemIndex, innerMap);
                                        HashMap<String, Integer> innerMap = new HashMap<>();
                                    }
                                }
                                JSONObject authorObject = resultObject.getJSONObject("author");
                                int authorContestId = authorObject.getInt("contestId");
                                JSONArray membersArray = authorObject.getJSONArray("members");
                                JSONObject memberObject = membersArray.getJSONObject(0); // Assuming there's only one member
                                String authorHandle = memberObject.getString("handle");

                                String programmingLanguage = resultObject.getString("programmingLanguage");
                                String[] words = programmingLanguage.split(" ");

                                if(words[0] == "GNU"){
                                    programmingLanguage = "C++";
                                }
                                else if (words[0] == "Python"){
                                    programmingLanguage = "Python";
                                }else if(words[0] == "Java"){
                                    programmingLanguage = "Java";
                                }else {
                                    programmingLanguage = "other";
                                }

                                if (!map.containsKey(problemIndex)){
                                    if(programmingLanguage != "other"){
                                        innerMap.put(programmingLanguage, 1);
                                        map.put(problemIndex, innerMap);
                                    }
                                    HashMap<String, Integer> innerMap = new HashMap<>();
                                }else{
                                    Map<String, Integer> innerMap = map.get(problemIndex);
                                    if (innerMap.containsKey(programmingLanguage)) {
                                        int count = innerMap.get(programmingLanguage);
                                        innerMap.put(programmingLanguage, count + 1);
                                        map.put(problemIndex, innerMap);
                                    } else {
                                        if(programmingLanguage != "other"){
                                            innerMap.put(programmingLanguage, 1);
                                            map.put(problemIndex, innerMap);
                                        }
                                    }
                                }

                                String verdict = resultObject.getString("verdict");
                                if (!map.containsKey(problemIndex)){
                                    if(verdict == "OK"){
                                        innerMap.put(verdict, 1);
                                        map.put(problemIndex, innerMap);
                                    }
                                    HashMap<String, Integer> innerMap = new HashMap<>();
                                }else{
                                    Map<String, Integer> innerMap = map.get(problemIndex);
                                    if (innerMap.containsKey(verdict)) {
                                        int count = innerMap.get(verdict);
                                        innerMap.put(verdict, count + 1);
                                        map.put(problemIndex, innerMap);
                                    } else {
                                        if(verdict == "OK"){
                                            innerMap.put(verdict, 1);
                                            map.put(problemIndex, innerMap);
                                        }
                                    }
                                }
                                String testset = resultObject.getString("testset");
                                int passedTestCount = resultObject.getInt("passedTestCount");
                                long timeConsumedMillis = resultObject.getLong("timeConsumedMillis");
                                long memoryConsumedBytes = resultObject.getLong("memoryConsumedBytes");

                                // Log all parameters
                                Log.d(TAG, "ID: " + id);
                                Log.d(TAG, "Contest ID: " + contestId);
                                Log.d(TAG, "Creation Time Seconds: " + creationTimeSeconds);
                                Log.d(TAG, "Relative Time Seconds: " + relativeTimeSeconds);
                                Log.d(TAG, "Problem Contest ID: " + problemContestId);
                                Log.d(TAG, "Problem Index: " + problemIndex);
                                Log.d(TAG, "Problem Name: " + problemName);
                                Log.d(TAG, "Problem Type: " + problemType);
                                Log.d(TAG, "Problem Points: " + problemPoints);
                                Log.d(TAG, "Problem Rating: " + problemRating);
                                Log.d(TAG, "Author Contest ID: " + authorContestId);
                                Log.d(TAG, "Author Handle: " + authorHandle);
                                Log.d(TAG, "Programming Language: " + programmingLanguage);
                                Log.d(TAG, "Verdict: " + verdict);
                                Log.d(TAG, "Testset: " + testset);
                                Log.d(TAG, "Passed Test Count: " + passedTestCount);
                                Log.d(TAG, "Time Consumed Millis: " + timeConsumedMillis);
                                Log.d(TAG, "Memory Consumed Bytes: " + memoryConsumedBytes);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Log.e(TAG, "Error: " + error.toString());
                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);

        for (Map.Entry<String, Map<String, Integer>> outerEntry : map.entrySet()) {
            String outerKey = outerEntry.getKey();
            Map<String, Integer> innersMap = outerEntry.getValue();

            String question_name = "";
            int rating = 0;
            int pythonCount = 0;
            int cppcount = 0;
            int javaCount = 0;
            int acceptance = 0;

            // Iterate over the inner Map
            for (Map.Entry<String, Integer> innerEntry : innersMap.entrySet()) {
                String innerKey = innerEntry.getKey();
                Integer value = innerEntry.getValue();
                if (innerKey == "rating"){
                    rating = value;
                }else if(innerKey == "OK"){
                    acceptance = value;
                } else if (innerKey == "Python") {
                    pythonCount = value;
                } else if (innerKey == "C++") {
                    cppcount = value;
                } else if ( innerKey == "Java") {
                    javaCount = value;
                } else {
                    question_name = innerKey;
                }

                Log.d(TAG, outerKey + " - " + innerKey + ": " + value);
            }
            items.add(new Item(question_name, outerKey, String.valueOf(pythonCount) + "Python", 1,
                    String.valueOf(cppcount) + "C++", 2, String.valueOf(javaCount) + "Java", 3,
                    String.valueOf(rating), String.valueOf(acceptance) ));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
    }

}