package controll;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/process")
public class Processa extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private String action;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		action = req.getParameter("action");
		String urlIP = "177.11.40.243";
		
		if(action != null) {
			
			//cadAll
			if(action.equals("cadall")) {
				URL url = new URL("http://"+urlIP+":3516/cadall"); 
				
				String nome = req.getParameter("nome");
				String cpf = req.getParameter("cpf");
				String senha = req.getParameter("senha");
				String confirmarsenha = req.getParameter("confirmarsenha");
				String telefone = req.getParameter("telefone");
				String logradouro = req.getParameter("logradouro");
				String numero = req.getParameter("numero");
				String bairro = req.getParameter("bairro");
				String complemento = req.getParameter("complemento");
				String cidade = req.getParameter("cidade");
				String estado = req.getParameter("estado");
				
				if(senha.equals(confirmarsenha)) {
					//CPF
					String cpfNew = cpf.replace(".", "");
					String cpfNew1 = cpfNew.replace("-", "");
					//---------------------------------------
					
					//Telefone
					String tel1 = telefone.replace("(", "");
					String tel2 = tel1.replace(")", "");
					String tel3 = tel2.replace("-", "");
					String tel4 = tel3.replace(" ", "");
					//---------------------------------------
					
					if((cpfNew1.length() == 11) && (tel4.length() == 11)) {
					
						//Verificação de CPF
						int soma = 0, mult = 1, soma1 = 0, mult1 = 1, cont = 10, cont1 = 11;
						String[] cpfMult = cpfNew1.split("");
	
						for(int i = 0; i <= 8; i++){
							mult = Integer.parseInt(cpfMult[i]) * (cont-i);
							soma = soma + mult;
						}
						
						int digito1 = (soma * 10) % 11;
						int verifica1;
						
						if(digito1 > 9){
						    verifica1 = 0;
						}else {
						    verifica1 = digito1;
						}
	
						for(int i = 0; i <= 9; i++){
							mult1 = Integer.parseInt(cpfMult[i]) * (cont1-i);
							soma1 = soma1 + mult1;
						}
	
						int digito2 = (soma1 * 10) % 11;
						int verifica2;
	
						if(digito2 > 9){
						    verifica2 = 0;
						}else{
						    verifica2 = digito2;
						}
						
						if((Integer.parseInt(cpfMult[9]) == verifica1) && (Integer.parseInt(cpfMult[10]) == verifica2) && (!cpfNew1.equals("00000000000")) && (!cpfNew1.equals("11111111111")) && (!cpfNew1.equals("22222222222")) && (!cpfNew1.equals("33333333333")) && (!cpfNew1.equals("44444444444")) && (!cpfNew1.equals("55555555555")) && (!cpfNew1.equals("66666666666")) && (!cpfNew1.equals("77777777777")) && (!cpfNew1.equals("88888888888")) && (!cpfNew1.equals("99999999999"))){
							String jsonInputString = "{\"nome\": \""+nome+"\","
									+ "\"cpf\": \""+cpfNew1+"\","
									+ "\"senha\": \""+senha+"\","
									+ "\"telefone\": \""+tel4+"\","
									+ "\"logradouro\": \""+logradouro+"\","
									+ "\"numero\": \""+numero+"\","
									+ "\"bairro\": \""+bairro+"\","
									+ "\"complemento\": \""+complemento+"\","
									+ "\"cidade\": \""+cidade+"\","
									+ "\"estado\": \""+estado+"\"}";
							
							HttpURLConnection con = (HttpURLConnection) url.openConnection();
								
							//Essas propriedades
							con.setRequestMethod("POST");
				            con.setConnectTimeout(5000);
				            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				            con.setDoInput(true);
							con.setDoOutput(true);
				            //--------------------------
								
							String parametros = jsonInputString.toString();
							System.out.println(parametros);
							//Essas propriedades
				            OutputStream os = con.getOutputStream();
				            os.write(parametros.getBytes("UTF-8"));
				            os.close();
				            //---------------
							
							BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
							
							String apnd = "", linha = "";
								
							while((linha = br.readLine()) != null) apnd += linha;
							
							JSONObject ob = new JSONObject(apnd);
							
							if(ob.has("status")) {
								String teste = ob.getString("status");
								if(teste != null) {
									//RequestDispatcher dd = req.getRequestDispatcher("cad.jsp?wrong=wrong");
									//dd.forward(req, resp);
									resp.sendRedirect("login.jsp?result=wrong");
								}
							}else {
								resp.sendRedirect("login.jsp?result=right&cpf="+cpfNew1);
							}
						}else {
							resp.sendRedirect("login.jsp?result=wrongCpf");
						}
					}else {
						resp.sendRedirect("login.jsp?result=length");
					}
				}else {
					resp.sendRedirect("login.jsp?result=wrongpassword");
				}
			}
			
			//auth
			if(action.equals("auth")) {
				URL url = new URL("http://"+urlIP+":3516/auth");
				
				String cpf = req.getParameter("cpf");
				String senha = req.getParameter("senha");
				
				String jsonInputString = "{\"cpf\": \""+cpf+"\","
										+ "\"senha\": \""+senha+"\"}";
				
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				
				//Essas propriedades
				con.setRequestMethod("POST");
	            con.setConnectTimeout(5000);
	            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	            con.setDoInput(true);
				con.setDoOutput(true);
	            //--------------------------
					
				String parametros = jsonInputString.toString();
				System.out.println("Parametros "+parametros);
				//Essas propriedades
	            OutputStream os = con.getOutputStream();
	            os.write(parametros.getBytes("UTF-8"));
	            os.close();
	            //---------------
				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				String apnd = "", linha = "";
					
				while((linha = br.readLine()) != null) apnd += linha;
				System.out.println(apnd);
				
				String log = req.getParameter("log");
				
				JSONObject ob = new JSONObject(apnd);
				
				System.out.println(apnd);
				
				if(log.equals("true")) {
					if(ob.getBoolean("auth") == true) {
						HttpSession hs = req.getSession();
						hs.setAttribute("user", apnd);
						resp.sendRedirect("homepos.jsp");
					}else {
						resp.sendRedirect("login.jsp?result=wrongLog");
					}
				}else{
					HttpSession hs = req.getSession();
					hs.invalidate();
					resp.sendRedirect("home.jsp");
				}
			}
			
			//cadEvent
			if(action.equals("cadEvent")) {
				URL url = new URL("http://"+urlIP+":3516/cadEvent"); 
				
				String rua = req.getParameter("rua");
				String numero = req.getParameter("numero");
				String complemento = req.getParameter("complemento");
				String bairro = req.getParameter("bairro");
				String cidade = req.getParameter("cidade");
				String estado = req.getParameter("estado");
				
				String modalidade = req.getParameter("modalidade");
				String datainicio = req.getParameter("datainicio");
				String datafinal = req.getParameter("datafinal");
				String horaevento = req.getParameter("horaevento");
				String id_org = req.getParameter("id_org");
				String qtdParticipantes = req.getParameter("qtdParticipantes");
				
				String[] datainicio1 = datainicio.split("/");
				String datinfinal = datainicio1[2]+""+datainicio1[1]+""+datainicio1[0];
				
				String[] datafinal1 = datafinal.split("/");
				String datfinal = datafinal1[2]+""+datafinal1[1]+""+datafinal1[0];
				
				String jsonInputString = "{\"rua\": \""+rua+"\","
						+ "\"numero\": \""+numero+"\","
						+ "\"complemento\": \""+complemento+"\","
						+ "\"bairro\": \""+bairro+"\","
						+ "\"cidade\": \""+cidade+"\","
						+ "\"estado\": \""+estado+"\","
						+ "\"modalidade\": \""+modalidade+"\","
						+ "\"datainicio\": \""+datinfinal+"\","
						+ "\"datafinal\": \""+datfinal+"\","
						+ "\"horaevento\": \""+horaevento+"\","
						+ "\"id_org\": \""+id_org+"\","
						+ "\"qtdParticipantes\": "+qtdParticipantes+"}";
				
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				System.out.println("cidade "+cidade);
				//Essas propriedades
				con.setRequestMethod("POST");
	            con.setConnectTimeout(5000);
	            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	            con.setDoInput(true);
				con.setDoOutput(true);
	            //--------------------------
				
				String parametros = jsonInputString.toString();
				System.out.println("Parametros "+parametros);
				//Essas propriedades
	            OutputStream os = con.getOutputStream();
	            os.write(parametros.getBytes("UTF-8"));
	            os.close();
	            //---------------
				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				String apnd = "", linha = "";
					
				while((linha = br.readLine()) != null) apnd += linha;
				
				JSONObject ob = new JSONObject(apnd);
				System.out.println(apnd);
				
				if(ob.has("status")) {
					String teste = ob.getString("status");
					if(teste != null) {
						//RequestDispatcher dd = req.getRequestDispatcher("cad.jsp?wrong=wrong");
						//dd.forward(req, resp);
						resp.sendRedirect("cadEvent.jsp?result=wrong");
					}
				}else {
					resp.sendRedirect("homepos.jsp?result=right");
				}
			}
		}
	}
}
