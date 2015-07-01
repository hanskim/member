package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.MemberBean;
import util.DBmanager;
 
 
public class MemberDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "";
    MemberBean bean = new MemberBean();
    /*
     * ���� ���ô� ������ �̱��� + DBCP �����Դϴ�. 
     �ٸ�, �̰͸� �ؼ��� ������ ���� ���࿡�� 
     DB������ �ȵ˴ϴ�. �׷���
     * DBmanager �� ������� ��а��� DBmanager �� 
     ����ϴٰ� ������Ʈ�� �ϼ��Ǹ� ö���ϴ� ������� �ϰڽ��ϴ�.
     */
    private static MemberDao memberDao = new MemberDao();
 
    private MemberDao() {
        // ���� �׽�Ʈ�� ������ ������Ʈ�� �ϼ��Ǹ� �Ⱦ� �� �κ�
        conn = DBmanager.getConnection();
    }
 
    public static MemberDao getInstance() {
        return memberDao;
    }
 
    // ����� �۵����� ������ �� DBmanager �� �Ⱦ�� ����
    // �۵���. �̸� ������.
    public Connection getConnection() throws Exception {
        Connection conn = null;
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
        conn = ds.getConnection();
        return conn;
    }
    public int join(MemberBean bean){
        int result = 0;
        String sql 
            = "insert into member(MEMBERID,PASSWORD,NAME,EMAIL,AGE)"
                +" values( ? , ? , ? , ? , ? )";
        try{
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, bean.getId());
            pstmt.setString(2, bean.getPassword());
            pstmt.setString(3, bean.getName());
            pstmt.setString(4, bean.getEmail());
            pstmt.setString(5, bean.getAge());
            result = pstmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("MemberDAO ���� ������ �߻� !!");
        }
        return result;
    }
    
    public List<MemberBean> getList() {
        List<MemberBean> list = new ArrayList<MemberBean>();
        try {
            stmt = conn.createStatement();
            sql = "select * from member";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                bean.setId(rs.getString("MEMBERID"));
                bean.setAge(rs.getString("AGE"));
                bean.setPassword(rs.getString("PASSWORD"));
                bean.setName(rs.getString("NAME"));
                bean.setEmail(rs.getString("EMAIL"));
 
                list.add(bean);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
 
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
        }
        return list;
 
    }
 
   
    
    
}
