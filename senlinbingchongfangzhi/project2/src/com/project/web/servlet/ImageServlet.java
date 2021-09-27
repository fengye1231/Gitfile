package com.project.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/code")
public class ImageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("image/jpeg");
		
		BufferedImage bf = new BufferedImage(130, 53, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = bf.getGraphics();
		
		Color c = new Color((int)(Math.random()*50+175),(int)(Math.random()*50+175),(int)(Math.random()*50+175));
		
		g.setColor(c);
		g.fillRect(0, 0, 130, 53);
		
		g.setFont(new Font("����", Font.BOLD, 35));
		
		String str = "";
		
		for(int i = 0;i<4;i++){
			char c1 = (char)(Math.random()*26+65);
			
			str+=c1;
			
			Color color = new Color((int)(Math.random()*50+70),(int)(Math.random()*50+70),(int)(Math.random()*50+70));
			g.setColor(color);
			
			g.drawString(c1+"",12+24*i,(int)(Math.random()*33+22));
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("code", str);
		
		OutputStream out = resp.getOutputStream();
		ImageIO.write(bf, "jpg", out);
	}
}
