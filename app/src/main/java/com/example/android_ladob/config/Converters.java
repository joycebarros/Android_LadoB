package com.example.android_ladob.config;

import androidx.room.TypeConverter;

import com.example.android_ladob.model.Orders;
import com.example.android_ladob.model.ProductOrder;
import com.example.android_ladob.model.Products;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {

   private static ObjectMapper objectMapper;

    public static String listToJson(List<String> value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    public static ArrayList<String> jsonToList(String value) {
        objectMapper = new ObjectMapper();
        String[] arr = new String[0];
        try {
            arr = objectMapper.readValue(value, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(Arrays.asList(arr));
    }

    @TypeConverter
    public static String listToJson1(Products value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
        // Converter um tipo em uma string
    }

    @TypeConverter
    public static Products jsonToList1(String value) {
        objectMapper = new ObjectMapper();
        Products arr = new Products();
        try {
            arr = objectMapper.readValue(value, Products.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
        //Converter uma string em um tipo
    }


    @TypeConverter
    public static String listToJson2(Orders value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
        // Converter um tipo em uma string
    }

    @TypeConverter
    public static Orders jsonToList2(String value) {
        objectMapper = new ObjectMapper();
        Orders arr2 = new Orders();
        try {
            arr2 = objectMapper.readValue(value, Orders.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr2;
        //Converter uma string em um tipo
    }

    @TypeConverter
    public static String listToJson3(List<ProductOrder> value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
        // Converter um tipo em uma string
    }

    @TypeConverter
    public static List<ProductOrder> jsonToList3(String value) {
        objectMapper = new ObjectMapper();
        ProductOrder arr = new ProductOrder();
        try {
            arr = objectMapper.readValue(value, ProductOrder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<ProductOrder>) arr;
        //Converter uma string em um tipo
    }
}
