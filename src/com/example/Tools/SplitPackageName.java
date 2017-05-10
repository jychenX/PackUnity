package com.example.Tools;

import com.example.UIFrame.UIFrame;

import javax.swing.JOptionPane;

/**
 * Created by jychen on 2016/10/30.
 */
public class SplitPackageName {

    public static String[] packNameArray(String nameString){
        String[] nameArray = null;
        if(nameString.contains(" ")){
            JOptionPane.showMessageDialog(UIFrame.fm,"输入的字符串包含空格，请仔细检查！");
            return null;
        }
        nameArray = nameString.split(",");
//        JOptionPane.showMessageDialog(UIFrame.fm,nameArray[0]+nameArray[1]);
        return  nameArray;
    }
}
