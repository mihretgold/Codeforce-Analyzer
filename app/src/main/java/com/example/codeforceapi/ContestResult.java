package com.example.codeforceapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ContestResult {

    private int id;
    private int contestId;
    private long creationTimeSeconds;
    private long relativeTimeSeconds;
    private int problemContestId;
    private String problemIndex;
    private String problemName;
    private String problemType;
    private double problemPoints;
    private int problemRating;
    private int authorContestId;
    private String authorHandle;
    private String programmingLanguage;
    private String verdict;
    private String testset;
    private int passedTestCount;
    private long timeConsumedMillis;
    private long memoryConsumedBytes;

    // Constructor
    public ContestResult(int id, int contestId, long creationTimeSeconds, long relativeTimeSeconds,
                         int problemContestId, String problemIndex, String problemName, String problemType,
                         double problemPoints, int problemRating, int authorContestId, String authorHandle,
                         String programmingLanguage, String verdict, String testset, int passedTestCount,
                         long timeConsumedMillis, long memoryConsumedBytes) {
        this.id = id;
        this.contestId = contestId;
        this.creationTimeSeconds = creationTimeSeconds;
        this.relativeTimeSeconds = relativeTimeSeconds;
        this.problemContestId = problemContestId;
        this.problemIndex = problemIndex;
        this.problemName = problemName;
        this.problemType = problemType;
        this.problemPoints = problemPoints;
        this.problemRating = problemRating;
        this.authorContestId = authorContestId;
        this.authorHandle = authorHandle;
        this.programmingLanguage = programmingLanguage;
        this.verdict = verdict;
        this.testset = testset;
        this.passedTestCount = passedTestCount;
        this.timeConsumedMillis = timeConsumedMillis;
        this.memoryConsumedBytes = memoryConsumedBytes;
    }

    // Getters for all fields...

    public static ContestResult fromJson(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        int contestId = jsonObject.getInt("contestId");
        long creationTimeSeconds = jsonObject.getLong("creationTimeSeconds");
        long relativeTimeSeconds = jsonObject.getLong("relativeTimeSeconds");

        JSONObject problemObject = jsonObject.getJSONObject("problem");
        int problemContestId = problemObject.getInt("contestId");
        String problemIndex = problemObject.getString("index");
        String problemName = problemObject.getString("name");
        String problemType = problemObject.getString("type");
        double problemPoints = problemObject.getDouble("points");
        int problemRating = problemObject.getInt("rating");

        JSONObject authorObject = jsonObject.getJSONObject("author");
        int authorContestId = authorObject.getInt("contestId");
        JSONArray membersArray = authorObject.getJSONArray("members");
        JSONObject memberObject = membersArray.getJSONObject(0); // Assuming there's only one member
        String authorHandle = memberObject.getString("handle");

        String programmingLanguage = jsonObject.getString("programmingLanguage");
        String verdict = jsonObject.getString("verdict");
        String testset = jsonObject.getString("testset");
        int passedTestCount = jsonObject.getInt("passedTestCount");
        long timeConsumedMillis = jsonObject.getLong("timeConsumedMillis");
        long memoryConsumedBytes = jsonObject.getLong("memoryConsumedBytes");

        return new ContestResult(id, contestId, creationTimeSeconds, relativeTimeSeconds,
                problemContestId, problemIndex, problemName, problemType, problemPoints,
                problemRating, authorContestId, authorHandle, programmingLanguage, verdict,
                testset, passedTestCount, timeConsumedMillis, memoryConsumedBytes);
    }

    public static List<ContestResult> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<ContestResult> contestResults = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ContestResult contestResult = fromJson(jsonObject);
            contestResults.add(contestResult);
        }
        return contestResults;
    }
}
