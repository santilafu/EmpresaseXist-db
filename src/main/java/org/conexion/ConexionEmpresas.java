package org.conexion;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class ConexionEmpresas {

    public static void main(String[] args) {
        try {
            // Registrar el driver de eXist-db
            String driver = "org.exist.xmldb.DatabaseImpl";
            Class<?> cl = Class.forName(driver);
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);

            // Conectar a la colección empresas
            String uri = "xmldb:exist://localhost:8080/exist/xmlrpc/db/empresas";
            Collection col = DatabaseManager.getCollection(uri, "admin", "1234");

            if (col != null) {
                System.out.println("¡Conexión exitosa!");

                // Leer el documento datos.xml
                XMLResource resource = (XMLResource) col.getResource("datos.xml");

                if (resource != null) {
                    System.out.println("Contenido del documento:");
                    System.out.println(resource.getContent().toString());
                } else {
                    System.out.println("No se encontró el documento datos.xml");
                }

                col.close();
            } else {
                System.out.println("No se pudo conectar a la colección");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}