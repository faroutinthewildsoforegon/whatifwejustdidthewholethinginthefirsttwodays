package com.example.server3;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerRequest implements Serializable {
    ArrayList<String> arguments;

    ServerRequest() {
        arguments = new ArrayList<>();
    }

    public String argumentAt(int index){
        if(index < 0 || index >= arguments.size())
            return null;
        else
            return arguments.get(index);
    }

    public int requestLength(){
        return arguments.size();
    }

}
