package com.swastikroy.textProcessor;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Swastik on 1/4/2017.
 */
@Named
public class LineCharacterStripper {

    public static LineCharacterStripper myInstance;

    private LineCharacterStripper(){

    };

    public static LineCharacterStripper getMyInstance(){
        if(myInstance == null){
            myInstance = new LineCharacterStripper();
        }
        return myInstance;
    }

    /**
     * Eliminates lines which do not begin with letters, numbers, parenthesis, single quote and double quote
     * @param lines
     * @return
     */
    public List<String> processLines(List<String> lines){
        List<String> response = new ArrayList<String>();
        Iterator<String> it = lines.listIterator();
        while(it.hasNext()){
            String line = it.next().trim();
            Boolean condition = false;
            char firstCharacter = line.charAt(0);
            int firstCharacterAscii = (int) firstCharacter;
            if(firstCharacterAscii >= 48 && firstCharacterAscii <=57){ //It's a number
                condition = true;
            }
            else if(firstCharacterAscii >= 65 && firstCharacterAscii <= 90){    //It's an uppercase alphabet
                condition = true;
            }
            else if(firstCharacterAscii >= 97 && firstCharacterAscii <= 122){   //It's a lowercase alphabet
                condition = true;
            }
            else if(firstCharacterAscii == 40 || firstCharacterAscii == 41){    //Parenthesis
                condition = true;
            }
            else if(firstCharacterAscii == 34 || firstCharacterAscii == 44){    //Double quotes or Single Quotes
                condition = true;
            }
            if(condition){
                response.add(line);
            }
        }
        return response;
    }
}
