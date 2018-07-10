package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoHelloController {

    @RequestMapping("/hello")
    public String Say(){
        int i=10;
        List<StudentInfo> studentList=new ArrayList<>();

        for(int j=0;j<=i;j++)
        {
            StudentInfo newS=new StudentInfo("Tom"+j,j);
            studentList.add(newS);
            j=PlusOne(MinusOne(PlusOne(j,MinusOne(j))),9);
            j=MinusOne(j);
            System.out.println(j);
        }
        return "Hey, this is my code!";
    }

    public int PlusOne(int pa,int d)
    {
        int j=1;
        j=j+pa;
        return j;
    }

    public int MinusOne(int pa)
    {
        int j=pa;
        j=j-1;
        return j;
    }
}
