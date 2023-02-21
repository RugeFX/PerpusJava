/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dao.AnggotaDAO;
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
import models.Anggota;
import models.PostResource;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "AnggotaController", urlPatterns = {"/AnggotaController"})
public class AnggotaController extends HttpServlet {

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
        AnggotaDAO ad = new AnggotaDAO();   
     
        switch(reqMethod){
            case "GET":
                // Make a new List based on the Buku model
                List<Anggota> anggotaList = new ArrayList<>(); 
                Anggota ang = new Anggota();
                // Insert the buku data from the DAO
                if(page == null){
                    try {
                        anggotaList = ad.getAllAnggota();
                        String anggotaJSON = gson.toJson(anggotaList);
                        System.out.println("AnggotaJSON : " + anggotaJSON);
                        out.println(anggotaJSON); 
                    } catch (Exception ex) {
                        PostResource pr = new PostResource("NO", null);
                        out.println(gson.toJson(ex));
                    }
                    return;
                }
                
                if(page.equals("show")){
                    try {
                        ang = ad.getDtAnggota(request.getParameter("nik"));
                        String anggotaJSON = gson.toJson(ang);
                        System.out.println("AnggotaJSON : " + anggotaJSON);
                        out.println(anggotaJSON);
                    } catch (Exception e) {
                        PostResource pr = new PostResource("NO", null);
                        out.println(gson.toJson(pr));
                    }
                    return;
                }
                // Converts the bukuList into a JSON String and then send it to the response
                
                break;
                
            case "POST":
                // Reads all of the form body from the request
                String resBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                System.out.println("resBody : " + resBody);

                // Parse the JSONString into a JSON Object named Anggota
                String data = null;
                try{
                    Anggota jsonAnggota = gson.fromJson(resBody, Anggota.class);
                    // Transfers the data via DAO
                    if (page.equals("insert")) {
                         try{
                            ad.insertAnggota(jsonAnggota);
                            PostResource pr = new PostResource("OK", null);
                            data = gson.toJson(pr);
                            out.println(data);
                            return;
                        }catch(SQLException ex){
                            System.out.println(ex);
                            PostResource pr = new PostResource("NO", null);
                            data = gson.toJson(pr);
                            out.println(data);
                            return;
                        }
                    }
                    if(page.equals("update")){
                       try{
                           System.out.println("JSONAnggota : " + jsonAnggota.toString());
                            ad.updateAnggota(jsonAnggota);
                        }catch(SQLException ex){
                            System.out.println(ex);
                        }
                    }
                    if(page.equals("delete")){
                        try {
                            ad.hapus(request.getParameter("nik"));
                            PostResource pr = new PostResource("OK", null);
                            data = gson.toJson(pr);
                            out.println(data);
                            return;
                        } catch (Exception e) {
                            PostResource pr = new PostResource("NO", null);
                            data = gson.toJson(pr);
                            out.println(data);
                        }
                    }
                    PostResource pr = new PostResource("OK", jsonAnggota);
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
