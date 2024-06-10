package Servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


import Beans.*;


public class TempoAttesa extends HttpServlet{
	
	private Gson g;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g = new Gson();
		
		
		//inserimento di prova di alcuni pazienti nel file dei pazienti (DA ELIMINARE PERCHE LEGGERO DAL FILE DEL MART)
		try {
			List<Paziente> pazienti=new ArrayList<Paziente>();
			
			Paziente p=new Paziente();
			p.setCodiceIdentificativo("B123");
			p.setCodiceColore(CodiceColore.BIANCO);
			pazienti.add(p);
			
			p=new Paziente();
			p.setCodiceIdentificativo("V123");
			p.setCodiceColore(CodiceColore.VERDE);
			pazienti.add(p);
			
			p=new Paziente();
			p.setCodiceIdentificativo("A123");
			p.setCodiceColore(CodiceColore.AZZURRO);
			pazienti.add(p);
			
			p=new Paziente();
			p.setCodiceIdentificativo("A124");
			p.setCodiceColore(CodiceColore.ARANCIONE);
			pazienti.add(p);
			
			p=new Paziente();
			p.setCodiceIdentificativo("R123");
			p.setCodiceColore(CodiceColore.ROSSO);
			pazienti.add(p);
			
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("pazienti.bin"));
			
			oos.writeObject(pazienti);
			oos.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		
		String risposta=null;
		
		String codiceIdentificativo=(String)request.getParameter("codiceIdentificativo");
		
		try {
			
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("pazienti.bin"));
			List<Paziente> pazienti=(List<Paziente>) ois.readObject();
			ois.close();
			
			for(Paziente p:pazienti) {
				if(p.getCodiceIdentificativo().equals(codiceIdentificativo)) {
					if(p.getCodiceColore().equals(CodiceColore.ROSSO)) {
						risposta="Tempo stimato 2 minuti";
					}
					if(p.getCodiceColore().equals(CodiceColore.ARANCIONE)) {
						risposta="Tempo stimato 12 minuti";
					}
					if(p.getCodiceColore().equals(CodiceColore.AZZURRO)) {
						risposta="Tempo stimato 22 minuti";
					}
					if(p.getCodiceColore().equals(CodiceColore.VERDE)) {
						risposta="Tempo stimato 1 ora e 22 minuti";
					}
					if(p.getCodiceColore().equals(CodiceColore.BIANCO)) {
						risposta="Tempo stimato 3 ore e 40 minuti";
					}
				}
			}
			
			if(risposta==null) {
				risposta="Nessuna informazione disponibile";
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("risposta", risposta);
		
		/* Infine inoltro la richiesta alla veria e propria applicazione ******************************************************/
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/app.jsp");
		rd.forward(request, response);
		return;
		
	}
	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		
	
	}
	

}
