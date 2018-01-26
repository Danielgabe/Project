package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table (name= "CounterAvailable")
public class CounterAvailableModel extends Model {

@Column(name = "Counter")	
@JsonProperty("CounterAvailable")
private String CounterAvailable;

 

public String getCounterAvailable() {
	return CounterAvailable;
}

public void setCounterAvailable(String counterAvailable) {
	CounterAvailable = counterAvailable;
} 
}
