package com.example.xqlim.secondlife.HistoryFolder;

import com.example.xqlim.secondlife.R;

import java.util.ArrayList;
import java.util.List;

class Person {
    String name;
    String age;
    int photoId;

    Person(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }


    private List<Person> persons;


}