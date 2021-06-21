package contentcl;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ratecalculate extends JFrame implements ActionListener{
	JButton jb1, jb2;  //��ť
	JLabel jlb0,jlb1, jlb2,jlb3,jlb4;
	JTextField jtf0,jtf1,jtf2;
	JPanel jp1,jp2,jp3,jp4,jp5;
	public ratecalculate(String countname) throws IOException{
		

		
		jb1 = new JButton("ȷ��");
		jb2 = new JButton("����");
		//���ð�ť����
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		jlb0 = new JLabel("�����˺�");
		jlb1 = new JLabel("ȡ�����");
		jlb2 = new JLabel("ȡ���·�");
		jlb3 = new JLabel("ȡ��ʱ��");
		jlb3.setFont(new   java.awt.Font("Dialog",   1,   20));   //�����������ͣ��Ӵ֣���СΪ20
		//�ı���Ϣ

		jtf0 = new JTextField(13);
		jtf1 = new JTextField(13);
		jtf2 = new JTextField(13);

		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		
		jp1.add(jlb1);//ȡ����ݼ����ı���
		jp1.add(jtf1);
		
		jp2.add(jlb2);//ȡ���·ݼ����ı���
		jp2.add(jtf2);
		
		jp3.add(jlb3);
		
		jp4.add(jb1);
		jp4.add(jb2);
		
		jp5.add(jlb0);
		jp5.add(jtf0);

		
		this.add(jp3);
		this.add(jp5);
		this.add(jp1);
		this.add(jp2);
		this.add(jp4);
	
		
		
		
		ImageIcon im=new ImageIcon("dneglu.png");
		JLabel lb11=new JLabel(im);
		lb11.setBounds(0,0,im.getIconWidth(),im.getIconHeight());
		this.getLayeredPane().add(lb11,new Integer(Integer.MIN_VALUE));
		JPanel imagePanel=(JPanel)this.getContentPane();
		imagePanel.setOpaque(false);
        
        //���ò���
        this.setTitle("��Ϣ����");
        this.setLayout(new GridLayout(10, 1));
        this.setSize(500, 400);   //���ô����С
        this.setLocationRelativeTo(null);//����Ļ�м���ʾ(������ʾ)  
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //���ý��رյ�ǰ����
        
        this.setVisible(true);  //���ÿɼ�
        this.setResizable(false);	//���ò��������С
		
        
        //����Ϣ ֱ�������û��� ����
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand()=="ȷ��")
		{	
			
			String[] message = null;
			try {
				message = new UserMessage().read(jtf0.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        String y=jtf1.getText();
	        String m=jtf2.getText();
	        String ye=message[5] ;
	        String mo=message[6];
	        String ra=message[7];
	        String money=message[4];
	        int year1=Integer.parseInt(y);
	        int month1=Integer.parseInt(m);
	        int year2=Integer.parseInt(ye);
	        int month2=Integer.parseInt(mo);
	        int rate=Integer.parseInt(ra);
	        int moneysum=Integer.parseInt(money);
	        int  ratesum=(((year1-year2)*12+month1-month2)/12)*rate*moneysum/1000;
			JOptionPane.showMessageDialog(null, "�˻�����Ϊǧ��֮:"+ra+",���ʱ��Ϊ��"+ye+"��"+mo+"�·�"+"��ʼ��"+y+"��"+m+"�·ݽ���"+"������ϢΪ"+ratesum+"Ԫ.","��ѯ��Ϣ�ɹ�:",JOptionPane.WARNING_MESSAGE);
		}
		
		else if (e.getActionCommand()=="����")
		{
			clear();
		}
		
		
	}
	
	

		
		//����˺ź������
		private void clear() {
			// TODO Auto-generated method stub
			jtf1.setText("");    //����Ϊ��
		    jtf2.setText("");  
		   
				
		}
	
	
	
}
