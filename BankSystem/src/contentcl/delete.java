package contentcl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class delete extends JFrame implements ActionListener{
	
	public delete(String countname) throws IOException{
		
		File file=new File("Message.txt");
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException(); 
        //读文件  
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();  //建立字符串流
        StringBuffer sb1=new StringBuffer();
        
        String moneystring="";
        
        temp=br.readLine();
        String []message = new String[8];     //按~拆分 成5个字符串数组，按账号和密码进行信息验证
        while(temp!=null){
        	String sbstring = temp.toString();
        	int n = sbstring.length();            //测字符串长度
        	for (int i=0; i<8; i++)
        		message[i] = "";
        	
        	int k=0;
        	for (int i=0; i<n; i++)      //拆乘5个String
        	{
        		if(sbstring.charAt(i)=='~')
        		{
        			//System.out.println("@"+message[k]);
        			k++;
        		}
        		else 
        		{
        			message[k] += sbstring.charAt(i);
        		}
        	}
        	
        	if (message[2].equals(countname))   //找到该账户名
        	{
        		String newmessage="";
        		for (int i=0; i<8; i++)				//转化为规定格式文件 
        			newmessage += "";
        		sb1.append(newmessage+"\n");
        	}
        	else
        	{
        		sb1.append(temp+"\n");
        	}
        	temp=br.readLine();
        }
        /*
         * 说明：
         * 本来的想法是在原文件对象中覆盖内容，但是发现覆盖后文本为空， 无法解决
         * 但重新创建文件对象，则可以完成操作
         */
        File file1=new File("Message.txt");   //重新建立文件对象， 覆盖写入文本
        if(!file1.exists())
           file1.createNewFile();
        FileOutputStream out=new FileOutputStream(file1,false);  //false为重写操作
        out.write(sb1.toString().getBytes("gb2312"));
        out.close();

	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

