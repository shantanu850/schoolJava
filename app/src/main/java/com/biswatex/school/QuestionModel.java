package com.biswatex.school;

public class QuestionModel {
    private String id;
    private String question;
    private String examID;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String ans;
    private String type;
    private String field;
    private String marks;

    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }

    public String getexamID() {
        return examID;
    }
    public void setexamID(String examID) {
        this.examID = examID;
    }

    public String getquestion() {
        return question;
    }
    public void setquestion(String question) {
        this.question = question;
    }

    public  String getop1(){
        return  op1;
    }
    public void setop1(String op1){
        this.op1 = op1;
    }

    public  String getOp2(){
        return  op2;
    }
    public void setOp2(String op2){
        this.op2 = op2;
    }

    public  String getOp3(){
        return  op3;
    }
    public void setOp3(String op3){
        this.op3 = op3;
    }

    public  String getOp4(){
        return  op4;
    }
    public void setOp4(String op4){
        this.op4 = op4;
    }

    public  String getans(){
        return  ans;
    }
    public void setans(String ans){
        this.ans = ans;
    }

    public  String getType(){
        return  type;
    }
    public void setType(String type){
        this.type = type;
    }

    public  String getfield(){
        return  field;
    }
    public void setfield(String field){
        this.field = field;
    }

    public  String getMarks(){
        return  marks;
    }
    public void setMarks(String marks){
        this.marks = marks;
    }

}
