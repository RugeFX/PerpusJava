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
import dao.BukuDAO;
import dao.PinjamanDAO;
import dao.StatusDao;
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
import models.Pinjaman;
import models.PinjamanAttributes;
import models.PostResource;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "PinjamanController", urlPatterns = {"/PinjamanController"})
public class PinjamanController extends HttpServlet {

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
        PinjamanDAO pd = new PinjamanDAO();   
     
        switch(reqMethod){
            case "GET":
                // Make a new List based on the Buku model
                List<Pinjaman> pinjamanList = new ArrayList<>(); 
                Pinjaman pnjm = new Pinjaman();
                // Insert the buku data from the DAO
                if(page == null){
                    try {
                        pinjamanList = pd.getAllPinjaman();
                        String pinjamanJSON = gson.toJson(pinjamanList);
                        System.out.println("PinjamanJSON : " + pinjamanJSON);
                        out.println(pinjamanJSON);
                    } catch (Exception e) {
                        PostResource pr = new PostResource("NO", null);
                        out.println(gson.toJson(pr));
                    }
                    return;
                }
                if(page.equals("show")){
                    try {
                        pnjm = pd.getDtPinjaman(request.getParameter("idpinjaman"));
                        String pinjamanJSON = gson.toJson(pnjm);
                        System.out.println("PinjamanJSON : " + pinjamanJSON);
//                        PostResource pr = new PostResource("OK", pnjm);
                        out.println(pinjamanJSON);
                    } catch (Exception e) {
                        System.out.println("Error show pinjaman : " + e);
                        PostResource pr = new PostResource("NO", null);
                        out.println(gson.toJson(pr));
                    }
                    return;
                }
                // Converts the bukuList into a JSON String and then send it to the response
                if(page.equals("attributes")){
                    AnggotaDAO ad = new AnggotaDAO();
                    BukuDAO bd = new BukuDAO();
                    StatusDao sd = new StatusDao();
                    try{
                        PostResource pr = new PostResource("OK", new PinjamanAttributes(ad.getAllAnggota(), sd.getAllStatus(), bd.getAllBuku()));
                        out.println(gson.toJson(pr));
                    }catch(SQLException ex){
                        System.out.println("Error pinjaman attr : " + ex);
                        PostResource pr = new PostResource("NO", null);
                        out.println(gson.toJson(pr));
                    }
                    
                    
                }
                break;
                
            case "POST":
                // Reads all of the form body from the request
                String resBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                System.out.println("resBody : " + resBody);
                
                // Parse the JSONString into a JSON Object named Petugas
                String data = null;
                try{
                    Pinjaman jsonPinjaman = gson.fromJson(resBody, Pinjaman.class);
                    // Transfers the data via DAO
                    if (page.equals("insert")) {
                         try{
                            pd.insertPinjaman(jsonPinjaman);
                            PostResource pr = new PostResource("OK", null);
                            data = gson.toJson(pr);
                            out.println(data);
                            return;
                        }catch(SQLException ex){
                            System.out.println(ex);
                             PostResource pr = new PostResource("NO", null);
                            data = gson.toJson(pr);
                            out.println(data);
                        }
                    }
                    if (page.equals("insertanggota")) {
                         try{
                            pd.insertPinjamanAnggota(jsonPinjaman);
                            PostResource pr = new PostResource("OK", null);
                            data = gson.toJson(pr);
                            out.println(data);
                            return;
                        }catch(SQLException ex){
                            System.out.println(ex);
                             PostResource pr = new PostResource("NO", null);
                            data = gson.toJson(pr);
                            out.println(data);
                        }
                    }
                    if(page.equals("update")){
                       try{
                            pd.updatePinjaman(jsonPinjaman);
                        }catch(SQLException ex){
                            System.out.println(ex);
                        }
                    }
                     if(page.equals("delete")){
                        try {
                            pd.hapus(request.getParameter("idpinjaman"));
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
                    PostResource pr = new PostResource("OK", jsonPinjaman);
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
