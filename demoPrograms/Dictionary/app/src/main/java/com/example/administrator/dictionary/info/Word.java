package com.example.administrator.dictionary.info;


public class Word {

    /**
     * 单词属性
     */
    private String word;
    private String translate;
    private String number;


    /**
     * set方法
     *
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }


    public void setWord(String word) {
        this.word = word;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    /**
     * get方法
     *
     * @return
     */

    public String getNumber() {
        return number;
    }

    public String getWord() {
        return word;
    }

    public String getTranslate() {
        return translate;
    }

}
