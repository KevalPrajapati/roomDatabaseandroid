package com.kevu.kevalassign

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
public class Product( val productName:String, val desc:String,val photostring:String){
    @PrimaryKey(autoGenerate = true) var id=0
}