package com.codecool.bookclub.googleapi;

import lombok.Data;

import java.util.List;

@Data
public class ReturnResults {

    private int totalItems;
    private List<GoogleApiBook> items;
}
