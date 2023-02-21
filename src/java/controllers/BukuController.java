/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BukuDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Buku;
import com.google.gson.*;
import dao.GenreDAO;
import dao.KategoriDao;
import dao.PenerbitDAO;
import java.sql.SQLException;
import java.util.stream.Collectors;
import models.Attributes;
import models.Genre;
import models.Kategori;
import models.Penerbit;
import models.PostResource;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "BukuController", urlPatterns = {"/BukuController"})
public class BukuController extends HttpServlet {

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
        BukuDAO bd = new BukuDAO(); 
        KategoriDao kd = new KategoriDao();
        PenerbitDAO pd = new PenerbitDAO();
        GenreDAO gd = new GenreDAO();
        
     
        switch(reqMethod){
            case "GET":
                // Make a new List based on the Buku model
                List<Buku> bukuList = new ArrayList<>(); 
                Buku buku = new Buku();
                
//                Penerbit penerbit = new Penerbit();
//                Kategori kategori = new Kategori();
//                Genre genre = new Genre();
                // Insert the buku data from the DAO
                if(page == null){
                    try {
                        bukuList = bd.getAllBuku();
                        System.out.println("BUKU : " + bukuList);
                        String bukuJSON = gson.toJson(bukuList);
                        System.out.println("BukuJSON : " + bukuJSON);
                        out.println(bukuJSON);
                    } catch (Exception e) {
                        PostResource pr = new PostResource("NO", null);
                        out.println(gson.toJson(pr));
                    }
                    return;
                }
                if(page.equals("show")){
                    try {
                        buku = bd.getBukuById(request.getParameter("kode"));
                        String bukuJSON = gson.toJson(buku);
                        System.out.println("BukuJSON SHOW : " + bukuJSON);
                        out.println(bukuJSON);
                    } catch (Exception e) {
                        PostResource pr = new PostResource("NO", null);
                        out.println(gson.toJson(pr));
                    }
                    return;
                }
                if (page.equals("terlaris")) {
                    try {
                        bukuList = bd.getBukuTerlaris();
                        System.out.println("BUKU : " + bukuList);
                        String bukuJSON = gson.toJson(bukuList);
                        System.out.println("BukuJSON : " + bukuJSON);
                        out.println(bukuJSON);
                    } catch (Exception e) {
                        System.out.println("ada error nih");
                        PostResource pr = new PostResource("NO", null);
                        out.println(gson.toJson(pr));
                    }
                    return;
                }
                if (page.equals("attributes")) {
                    try {
                        Attributes attr = new Attributes(gd.getAllGenre(),pd.getAllPenerbit(), kd.getAllKategori());
                        System.out.println("Attr : " + attr);
                        out.print(gson.toJson(new PostResource("OK", attr)));
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
                
                try{
                    Buku jsonBuku = gson.fromJson(resBody, Buku.class);
                    // Transfers the data via DAO
                    if (page.equals("insert")) {
                        try{
                            bd.insertBuku(jsonBuku);
                            PostResource pr = new PostResource("OK", null);
                            out.println(gson.toJson(pr));
                        }catch(SQLException ex){
                            System.out.println(ex);
                            PostResource pr = new PostResource("NO", null);
                            out.println(gson.toJson(pr));
                        }
                    }
//                    else if(page.equals("showupdate")){
//                        try{
//                            String kode = request.getParameter("kode");
//                            response.sendRedirect("/PerpusJava/admin/pages/forms/tambahbuku.html?kode=" + kode);
//                        }catch(IOException ex){
//                            System.out.println("Error " + ex);
//                        }
//                    }
                    else if(page.equals("update")){
                        try{
                            bd.updateBuku(jsonBuku);
                            out.println(gson.toJson(new PostResource("OK", jsonBuku)));
                        }catch(SQLException ex){
                            System.out.println(ex);
                            out.println(gson.toJson(new PostResource("Error " + ex, null)));
                        }
                    }else if(page.equals("delete")){
                        System.out.println("masuk delet");
                        try{
                            bd.hapus(request.getParameter("kode"));
                            out.println(gson.toJson(new PostResource("OK", null)));
                        }catch(SQLException ex){
                            System.out.println(ex);
                            out.println(gson.toJson(new PostResource("Error " + ex, null)));
                        }
                    }
                }catch(JsonIOException | JsonSyntaxException jex){
                    System.out.println("Masuk error : " + jex);
                }                       
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
