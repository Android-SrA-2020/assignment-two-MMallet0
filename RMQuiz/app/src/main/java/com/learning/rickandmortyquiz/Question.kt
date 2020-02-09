package com.learning.rickandmortyquiz

/**
 * Created by Martin Mallet on 2020-01-14
 */

//a special class that automatically generate hashcode method and tostring() method
// kotlin has == and ===
// == compares the value of 2 variables
// === checks the reference of 2 objects

//By having the val keyword in the constructor, it creates and assign the properties
 data class Question (val resourceId: Int, val answer: Boolean){

}