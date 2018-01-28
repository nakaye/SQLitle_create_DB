import java.sql.*;

public class Main {
    private static final String DBURL = "klienci.db";
    public static void main(String[] args){
        System.out.print("Łacze z DB" + DBURL + "....");
        try(Connection con = DriverManager.getConnection("jdbc:sqlite:"+DBURL)){
            System.out.println("OK");


            String createSQL = "create table if not exists klienci("+
                                "idklienta integer primary key,"
                                +"nazwa text unique)";
            Statement st = con.createStatement();
            st.execute(createSQL);


            String insertSQL = "insert into klienci (nazwa) values ('zakład fryzjerski loczek')";
           // int rowcount = st.executeUpdate(insertSQL);
           // System.out.println("Dodano " + rowcount+" rekordów");


            ResultSet rs = st.executeQuery("SELECT  * FROM klienci");

            while (rs.next())
                System.out.println(rs.getString("nazwa"));

            String createSQL1 = "create table if not EXISTS zamowienia("
                                +"idzam integer primary key,"
                                +"idklienta integer not null,"
                                +"value integer not null)";


          //  st.execute(createSQL1);


       //     String insertSQL3="insert into zamowienia (idzam,idklienta,value) values ('1','1','3000')  ";
        //    String insertSQL4="insert into zamowienia (idzam,idklienta,value) values ('2','3','150')  ";
         //   String insertSQL5="insert into zamowienia (idzam,idklienta,value) values ('3','2','300')  ";
          //  String insertSQL6="insert into zamowienia (idzam,idklienta,value) values ('4','1','450')  ";

            // int rowCount =st.executeUpdate(insertSQL4);
           // int rowCount1 =st.executeUpdate(insertSQL5);
           // int rowCount2 =st.executeUpdate(insertSQL6);
           // System.out.println("Dodano : " + (rowCount+rowCount1+rowCount2) + " rekordow");

            System.out.println("================================================");
            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet tables = dbmd.getTables(null,null,null,null);
            printResultSetColumn(tables, "TABLE_NAME");
            System.out.println("================================================");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void printResultSetColumn(ResultSet rs , String ColName) throws SQLException {
        while (rs.next())
            System.out.println(rs.getString(ColName));

    }

}
