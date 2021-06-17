package com.neusoft.dms.dept.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Nodes {
	private int id = 0;
    private int pId = 0; //父级菜单的 id
    private String text = "";
    private String href = "/dms/daily/setting/dept/?deptId=";
    private HashMap<String,Boolean> state = null;
    private List<Nodes> nodes = null;
    //可根据需要添加其他属性
    
    public Nodes(){
    	state = new HashMap<String,Boolean>();
    	state.put("checked", false);
    	state.put("disabled", false);
    	state.put("expanded", true);
    	state.put("selected", false);
    }
    
	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    
    public int getpId() {
        return pId;
    }
 
    public void setpId(int pId) {
        this.pId = pId;
    }
    
    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	public void setState(HashMap<String,Boolean> state) {
		this.state = state;
	}

	public HashMap<String,Boolean> getState() {
		return state;
	}
	
	public List<Nodes> getNodes() {
		return nodes;
	}

	public void setNodes(List<Nodes> nodes) {
		this.nodes = nodes;
	}
	
	public void setNodes() {
		nodes = new ArrayList<Nodes>();
	}
    
    /*
	 public static void main(String[] args) {
	 
       Menu m3 = new Menu();
       m3.setId("13");
       m3.setpId("12");
       m3.setName("third");

       Menu m2 = new Menu();
       m2.setId("12");
       m2.setpId("11");
       m2.setName("second");
       m2.getMenus().add(m3);

       Menu m1 = new Menu();
       m1.setId("1");
       m1.setpId("0");
       m1.setName("first");
       m1.getMenus().add(m2);


       Menu m6 = new Menu();
       m6.setId("23");
       m6.setpId("22");
       m6.setName("third");

       Menu m5 = new Menu();
       m5.setId("22");
       m5.setpId("21");
       m5.setName("second");
       m5.getMenus().add(m6);

       Menu m4 = new Menu();
       m4.setId("21");
       m4.setpId("0");
       m4.setName("first");
       m4.getMenus().add(m5);

       List<Menu> m = new ArrayList<Menu>();
       m.add(m1);
       m.add(m4);

       String jsonString = JSON.toJSONString(m);
       //zTree里面节点一定要用nodes所以要将menus替换为nodes,也可以该前面Menu.java为 Nodes.java
       System.out.println(jsonString.replaceAll("menus", "nodes"));
     }
	 */
    
    /*
     <!DOCTYPE html>
	<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="text/javascript" src="jquery-1.4.2.js"></script>
        <script type="text/javascript" src="jquery.ztree-2.6.js"></script>
        <link rel="stylesheet" href="./demo.css" type="text/css" />
        <link rel="stylesheet" href="./zTreeStyle.css" type="text/css" />
        <script type="text/javascript">
            var zTreeObj;
            var setting = {
                showLine:true
            };
            var testNodes = [{"nodes":[{"nodes":[{"nodes":[],"name":"third","id":"13","target":""}],"name":"second","id":"12","target":""}],
            		"name":"first","id":"1","target":""},
            		{"nodes":[{"nodes":[{"nodes":[],"name":"third","id":"23","target":""}],
            		"name":"second","id":"22","target":""}],"name":"first","id":"21","target":""}];           
            $(document).ready(function(){
                zTreeObj = $("#treeDemo").zTree(setting, testNodes);
            });
        </script>
        <title>ztree测试用例</title>
     </head>
     <body>
        <div>
            <ul id="treeDemo" class="tree"></ul>
        </div>
     </body>
	 </html>
     */
}
