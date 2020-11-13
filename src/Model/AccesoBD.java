package Model;

import java.sql.*;

public class AccesoBD {
    private Connection con;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private ResultSet rsFilas;

    public Object [][] tablaInstrumentos;
    private int cont = 0;

    public AccesoBD() {
    }

    public void conectar() {
        try {
            System.out.println("Intentando conexión con BBDD...");
            //Carga driver mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/almacen";
            String usuario = "root";
            String contraseña = "";

            //Objeto conexión
            con = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión con éxito!");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarInstrumento(Instrumento instrumento) {
        String sql = "INSERT INTO instrumentos (num_serie, num_instrumentos, peso, fecha_recepcion, transportista, observaciones) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            System.out.println("Insertando instrumento...");
            //Objeto de tipo PreparedStatement para enviar sentencia SQL
            ps = con.prepareStatement(sql);

            //Se establece los parámetros designados en los valores de la consulta
            ps.setInt(1, instrumento.getNumSerie());
            ps.setInt(2, instrumento.getNumInstrumento());
            ps.setInt(3, instrumento.getPeso());
            ps.setString(4, String.valueOf(instrumento.getFechaRecepcion()));
            ps.setString(5, instrumento.getTransportista());
            ps.setString(6, instrumento.getObservaciones());

            //Ejecuta instrucción SQL
            ps.executeUpdate();
            System.out.println("Instrumento insertado correctamente!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                //Cerrar conexiones
                ps.close();
//                con.close();
                System.out.println("Conexión con BBDD cerrada.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void contarFilas() {
        try {
            rsFilas = st.executeQuery("SELECT * FROM instrumentos;");
            while(rsFilas.next()) {
                cont++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rsFilas.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void mostrarIntrumentos() {
        contarFilas();
        String sql = "SELECT * FROM instrumentos;";
        try {

            rs = st.executeQuery(sql);
            tablaInstrumentos = new Object[cont][6];

            for(int j=0; j<tablaInstrumentos.length; j++) {
                rs.next();
                for(int k=0; k<tablaInstrumentos[j].length; k++) {
                    tablaInstrumentos[j][k] = rs.getString(k+2);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                st.close();
                rs.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
