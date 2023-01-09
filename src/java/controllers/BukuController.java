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
import java.io.BufferedReader;
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
        
        // Object initiation
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        BukuDAO bd = new BukuDAO();   
     
        switch(reqMethod){
            case "GET":
                // Make a new List based on the Buku model
                List<Buku> bukuList = new ArrayList<>(); 
                // Insert all of the buku data from the DAO with the getAllBuku method
                bukuList = bd.getAllBuku();
                // Converts the bukuList into a JSON String and then send it to the response
                String bukuJSON = gson.toJson(bukuList);
                System.out.println("BukuJSON : " + bukuJSON);
                out.println(bukuJSON);   
                break;
                
            case "POST":
                // Reads all of the form body from the request
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                }
                finally {
                    reader.close();
                }
                
                // Convert it into a complete string
                String JSONString = sb.toString();
                
                // Parse the JSONString into a JSON Object named buku
                JsonParser jParser = new JsonParser();
                JsonElement JSONObj = jParser.parse(JSONString);
                JsonObject buku = JSONObj.getAsJsonObject();
                
                //TODO: Process the data with DAO
                buku.get("the name of json property");
                
                // Make a new PostResource and send it as a response
                PostResource pr = new PostResource("OK", buku);
                String data = gson.toJson(pr);
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
