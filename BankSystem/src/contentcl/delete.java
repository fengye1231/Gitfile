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
        //���ļ�  
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();  //�����ַ�����
        StringBuffer sb1=new StringBuffer();
        
        String moneystring="";
        
        temp=br.readLine();
        String []message = new String[8];     //��~��� ��5���ַ������飬���˺ź����������Ϣ��֤
        while(temp!=null){
        	String sbstring = temp.toString();
        	int n = sbstring.length();            //���ַ�������
        	for (int i=0; i<8; i++)
        		message[i] = "";
        	
        	int k=0;
        	for (int i=0; i<n; i++)      //���5��String
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
        	
        	if (message[2].equals(countname))   //�ҵ����˻���
        	{
        		String newmessage="";
        		for (int i=0; i<8; i++)				//ת��Ϊ�涨��ʽ�ļ� 
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
         * ˵����
         * �������뷨����ԭ�ļ������и������ݣ����Ƿ��ָ��Ǻ��ı�Ϊ�գ� �޷����
         * �����´����ļ������������ɲ���
         */
        File file1=new File("Message.txt");   //���½����ļ����� ����д���ı�
        if(!file1.exists())
           file1.createNewFile();
        FileOutputStream out=new FileOutputStream(file1,false);  //falseΪ��д����
        out.write(sb1.toString().getBytes("gb2312"));
        out.close();

	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

