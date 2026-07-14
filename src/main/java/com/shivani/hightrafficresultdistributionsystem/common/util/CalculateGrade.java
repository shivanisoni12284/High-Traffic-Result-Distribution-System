package com.shivani.hightrafficresultdistributionsystem.common.util;

import com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade;
import org.springframework.stereotype.Component;

import static com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade.*;

@Component
public class CalculateGrade {

    public Grade gradeEnum(int marks){

        if(marks>= 91 && marks <=100){
            return A1;
        }
        else if (marks >= 81 && marks <=90) {
            return A2 ;
        }
        else if (marks >= 71 && marks  <=80) {
            return B1;
        }
        else if (marks >= 61 && marks <=70) {
            return B2;
        }
        else if (marks >= 51 && marks <=60) {
            return C1;
        }
        else if (marks >= 41 && marks <=50) {
            return C2;
        }
        else if (marks >= 33 && marks <=40) {
             return D;
        }
        return E;

    }
}
