package com.java.dserialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class User {
    public final int id;
    public final String name;



    @JsonCreator
    public User(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException {
       String s = "{\n" +
               "    \"id\": 1,\n" +
               "    \"itemName\": \"theItem\",\n" +
               "    \"owner\": {\n" +
               "        \"id\": 2,\n" +
               "        \"name\": \"theUser\"\n" +
               "    }\n" +
               "}";

        Item item = new ObjectMapper().readValue(s, Item.class);
       System.out.println(item);

       s = "{\n" +
               "    \"id\": 1,\n" +
               "    \"itemName\": \"theItem\",\n" +
               "    \"createdBy\": 2\n" +
               "}";

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Item.class, new ItemDeserializer());
        mapper.registerModule(module);

        Item readValue = mapper.readValue(s, Item.class);

       System.out.println(readValue);

    }
}
 class Item {
    public int id;
    public String itemName;
    public User owner;

    public Item(){}

     public Item(int id, String itemName, User user) {

         this.id = id;
         this.itemName = itemName;
         this.owner = user;

     }



     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getItemName() {
         return itemName;
     }

     public void setItemName(String itemName) {
         this.itemName = itemName;
     }

     public User getOwner() {
         return owner;
     }

     public void setOwner(User owner) {
         this.owner = owner;
     }

     @Override
     public String toString() {
         return "Item{" +
                 "id=" + id +
                 ", itemName='" + itemName + '\'' +
                 ", owner=" + owner +
                 '}';
     }
 }