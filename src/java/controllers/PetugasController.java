/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dao.PetugasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Petugas;
import models.PostResource;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "PetugasController", urlPatterns = {"/PetugasController"})
public class PetugasController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response type and writer
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        
        // Put request http method and page params into variable
        String reqMethod = request.getMethod();
        String page = request.getParameter("page");
        System.out.println("Page : " + page);
        
        // Object initiation
        Gson gson = new Gson();
        PetugasDAO pd = new PetugasDAO();   
     
        switch(reqMethod){
            case "GET":
                // Make a new List based on the Buku model
                List<Petugas> petugasList = new ArrayList<>(); 
                Petugas petugas = new Petugas();
                // Insert the buku data from the DAO
                if(page == null){
                    petugasList = pd.getAllPetugas();
                    String petugasJSON = gson.toJson(petugasList);
                    System.out.println("PetugasJSON : " + petugasJSON);
                    out.println(petugasJSON);
                }
                if(page.equals("show")){
                    petugas = pd.getDtPetugas(request.getParameter("idpetugas"));
                    String petugasJSON = gson.toJson(petugas);
                    System.out.println("PetugasJSON : " + petugasJSON);
                    out.println(petugasJSON);
                }
                // Converts the bukuList into a JSON String and then send it to the response
                
                break;
                
            case "POST":
                // Reads all of the form body from the request
                String resBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                System.out.println("resBody : " + resBody);
                
                // Parse the JSONString into a JSON Object named Petugas
                String data = null;
                try{
                    Petugas jsonPetugas = gson.fromJson(resBody, Petugas.class);
                    // Transfers the data via DAO
                    if (page.equals("insert")) {
                         try{
                            pd.insertPetugas(jsonPetugas);
                        }catch(SQLException ex){
                            System.out.println(ex);
                        }
                    }else{
                       try{
                            pd.updatePetugas(jsonPetugas);
                        }catch(SQLException ex){
                            System.out.println(ex);
                        }
                    }
                    PostResource pr = new PostResource("OK", jsonPetugas);
                    data = gson.toJson(pr);
                }catch(JsonIOException | JsonSyntaxException jex){
                    System.out.println("Masuk error : " + jex);
                    
                }        
                out.println(data);                   
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
