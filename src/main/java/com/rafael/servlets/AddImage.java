package com.rafael.servlets;

import com.rafael.dao.ObjectDAO;
import com.rafael.deteobjetos.Conexion;
import com.rafael.model.ObjectCamera;
import com.rafael.model.TypeObject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

public class AddImage extends HttpServlet {

    private final int MAX_SIZE = 30000 * 1024;
    private final int MAX_MEM_SIZE = 4 * 1024;

    private boolean isMultipart;
    private String filePath;
    private File filef;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        filePath = request.getServletContext().getRealPath("/");
        isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            JSONObject obj = new JSONObject();
            obj.put("error", "El formulario no es multipart :(");
            out.print(obj);
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Tamaño maximo que sera guardado en la memoria
        factory.setSizeThreshold(MAX_MEM_SIZE);

        // Ruta donde guardaremos los archivos que cumplan con el tamaño minimo
        factory.setRepository(new File(filePath));

        // Creamos un manejador del archivo subido al servlet
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Establecemos el tamaño maximo de los archivos que guardaremos
        upload.setSizeMax(MAX_SIZE);

        try {
            JSONObject obj = new JSONObject();       
            ObjectCamera oc = new ObjectCamera();
            List<FileItem> fileItems = upload.parseRequest(request);
            String fileName = "No inicializada";
            for (FileItem file : fileItems) {
                //comprobamos si no es un campo normal
                //si no lo es entonces es un archivo y tenemos que procesarlo :)
                if (file.isFormField()) {
                    if(file.getFieldName().equals("id")){
                        int idObject = Integer.parseInt(file.getString());
                        oc.setType(new TypeObject(idObject));
                        obj.put("id_type", idObject);
                    }
                    continue;
                }
                fileName = file.getName();

                filef = new File(filePath + "imgs/" + fileName);
                file.write(filef);
                System.out.println(filef.getPath());
                obj.put("file", fileName);
            }
            
            oc.setName(fileName);
            oc.setRoute("./imgs/" + fileName);
           
            ObjectDAO oDao = new ObjectDAO();
            oDao.insertObject(oc);
            
            out.print(obj);
        } catch (Exception ex) {
            JSONObject obj = new JSONObject();
            obj.put("error", "Hubo un error");
            out.print(obj);
            Logger.getLogger(AddImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
