package com.example.internapplication.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class List {
    int page;
    int per_page;
    int total;
    int total_pages;
    ArrayList<Data> data;

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public class Data implements Serializable{
        int id;
        String email;
        String first_name;
        String last_name;
        String avatar;

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getAvatar() {
            return avatar;
        }
    }
}
