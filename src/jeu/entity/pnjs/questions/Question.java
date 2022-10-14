package jeu.entity.pnjs.questions;

import jeu.entity.pnjs.*;

public class Question{

    // The attribute is the question
    private String question;
    // The attribute is the answer to the question
    private String answer;

    /*
    * Construct needed to build a question associated with an answer
    * @param iQuestion The question
    * @param iAnswer  The answer to the question
    */
    public Question(String iQuestion,  String iAnswer){
        this.question = iQuestion;
        this.answer = iAnswer;
    }

    /*
    * @return String the question
    */
    public String getQuestion(){
        return this.question;
    }

    /*
    * @return String the answer
    */
    public String getAnswer(){
        return this.answer;
    }

    /*
    *His primary function is to check if the answer is correct
    * @param str the possible answer that the user give
    * @return boolean , return if the answer given is the answer to the question
    */
    public boolean checkAnswer(String str){
        return (this.answer.equalsIgnoreCase(str));
    }
}