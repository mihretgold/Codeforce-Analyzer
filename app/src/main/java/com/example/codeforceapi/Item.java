package com.example.codeforceapi;

public class Item {
    String questionName;
    String questionId;
    String python;
    int pythonImage;
    String cpp;
    int cppImage;
    String java;
    int javeImage;
    String rating;
    String acceptance;

    public Item(String questionName, String questionId, String python, int pythonImage, String cpp, int cppImage, String java, int javeImage, String rating, String acceptance) {
        this.questionName = questionName;
        this.questionId = questionId;
        this.python = python;
        this.pythonImage = pythonImage;
        this.cpp = cpp;
        this.cppImage = cppImage;
        this.java = java;
        this.javeImage = javeImage;
        this.rating = rating;
        this.acceptance = acceptance;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getPython() {
        return python;
    }

    public void setPython(String python) {
        this.python = python;
    }

    public int getPythonImage() {
        return pythonImage;
    }

    public void setPythonImage(int pythonImage) {
        this.pythonImage = pythonImage;
    }

    public String getCpp() {
        return cpp;
    }

    public void setCpp(String cpp) {
        this.cpp = cpp;
    }

    public int getCppImage() {
        return cppImage;
    }

    public void setCppImage(int cppImage) {
        this.cppImage = cppImage;
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public int getJaveImage() {
        return javeImage;
    }

    public void setJaveImage(int javeImage) {
        this.javeImage = javeImage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }
}
