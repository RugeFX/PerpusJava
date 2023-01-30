/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import dao.AnggotaDAO;
import dao.PetugasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Anggota;
import models.Petugas;
import models.PostResource;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "AuthController", urlPatterns = {"/AuthController"})
public class AuthController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        
        // Put request http method and page params into variable
        String reqMethod = request.getMethod();
        String page = request.getParameter("page");
        System.out.println("Page : " + page);
        
        // Object initiation
        Gson gson = new Gson();
        AnggotaDAO ad = new AnggotaDAO();  
        PetugasDAO pd = new PetugasDAO();
        Anggota ang = new Anggota();
        Petugas petugas = new Petugas();
        String data = null;
        
        HttpSession session = request.getSession();
        
        try {
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            Boolean cekAnggota = ad.getLogin(id, password);
            if (!cekAnggota) {
                Boolean cekPetugas = false;
                cekPetugas = pd.getLogin(id, password);
                System.out.println(cekPetugas);
                if (!cekPetugas) {
                    PostResource pr = new PostResource("NO", null);
                    data = gson.toJson(pr);
                    out.println(data);
                    return;
                }
                session.setAttribute("level", "0");
                petugas = pd.getDtPetugas(id);
                session.setAttribute("id", petugas.getIdpetugas());
                PostResource pr = new PostResource("OK", "Petugas");
                data = gson.toJson(pr);
                out.println(data);
                return;
            }
                session.setAttribute("level", "1");
                ang = ad.getDtAnggota(id);
                session.setAttribute("id", ang.getNik());   
                PostResource pr = new PostResource("OK", "Anggota");
                data = gson.toJson(pr);
                out.println(data);
        } catch (Exception ex) {
            PostResource pr = new PostResource("NO", null);
            data = gson.toJson(pr);
            out.println(data);
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
