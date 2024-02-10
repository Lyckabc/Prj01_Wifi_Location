package model;

import apidto.RowInfoDto;
import apidto.WifiDto;
import model.vo.WifiVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DAO extends JdbcManager {

    // DB 커넥션 부분
    Connection coon ;  // db연결해주는 구분 인터페이스처럼
    PreparedStatement ps ;   // sql 실행시켜주는 구문
    ResultSet rs ; // 한줄씩읽는애

    public void saveAllWifiDetailList(List<RowInfoDto> lists) throws Exception {
        coon = null;
        ps = null;
        rs = null;

        int count = 0;// 배치가 가득찾는지 확인하는 용도.

        try {

        String sql = " insert into wifi "
                + " ( x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, "
                + " x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, "
                + " x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm) "
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";



            coon = createConnection();   // 디비연결

            coon.setAutoCommit(false);


            ps = coon.prepareStatement(sql);  // 객체자체를 prepareStatement 에 넣자 => 이뜻은  prepareStatement를 만들어준다.

            int basicBactchSize = 1000; // 배치사이즈는 500 /  밖에서 1000개씩 짜르기로했으니까 배치를 총 2번만들겠다.


            for (int i = 0; i < lists.size(); i++) {   // 처음에 0~ 999까지 돌면서

                RowInfoDto infoDto = lists.get(i);    // 1개씩( 16개)  RowInfoDto 객체하나
                ps.setString(1, infoDto.getMgrNo());
                ps.setString(2, infoDto.getWrdofc());
                ps.setString(3, infoDto.getMainNm());
                ps.setString(4, infoDto.getAdres1());
                ps.setString(5, infoDto.getAdres2());
                ps.setString(6, infoDto.getFloor());
                ps.setString(7, infoDto.getTy());
                ps.setString(8, infoDto.getMby());
                ps.setString(9, infoDto.getSvcSe());
                ps.setString(10, infoDto.getCmcwr());
                ps.setString(11, infoDto.getYear());
                ps.setString(12, infoDto.getDoor());
                ps.setString(13, infoDto.getRemars3());
                ps.setString(14, infoDto.getLat());
                ps.setString(15, infoDto.getLnt());
                ps.setString(16, infoDto.getDttm());

                ps.addBatch(); // 배치에 1개넣어주고
                count++; // 카운트값을 1 증가시켜주고

                if (basicBactchSize == count) {    //500 == count
                    ps.executeBatch(); // 배치를 실행하고 총2개날릴거 일단 1개
                    ps.clearBatch(); // 배치 청소
                    count = 0; // 카운트 초기화시켜주고
                }
            }
        /*
         18003개있는데 얘가 19번 반복할떄
         18번동안 실행안되다가 배치가 500씩 / 2번 날리는건데
         500 = 3 이 안되므로 그 나머지 3개에 대한부분을 마지막 횟수에서 처리해준다.
         */

            ps.executeBatch();
            coon.commit();
        } catch (Exception e) {
            coon.rollback();
        } finally {
            coon.setAutoCommit(true);
            closeStatement(ps);
            closeConnection(coon);
        }
    }
    /**
     * dao.search(latDouble,lntDouble);
     */

    public List<WifiVO> getNearestWifiList(Double lat, Double lnt) {

        List<WifiVO> list = new ArrayList<>();

        try {
            coon = createConnection();

            String sql = " SELECT *, " +
                " round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT) " +
                " -radians(?))+sin(radians(?))*sin(radians(LAT))), 4) " +
                " AS distance " +
                " FROM wi_db.wifi " +
                " ORDER BY distance " +
                " LIMIT 20;";

            ps = coon.prepareStatement(sql);
            ps.setDouble(1, lat);
            ps.setDouble(2, lnt);
            ps.setDouble(3, lat);
            rs = ps.executeQuery(); // 20개씩

            while (rs.next()) {
                WifiVO wifiVO = new WifiVO(
                        rs.getString("distance"),
                        rs.getString("X_SWIFI_MGR_NO"),
                        rs.getString("X_SWIFI_WRDOFC"),
                        rs.getString("X_SWIFI_MAIN_NM"),
                        rs.getString("X_SWIFI_ADRES1"),
                        rs.getString("X_SWIFI_ADRES2"),
                        rs.getString("X_SWIFI_INSTL_FLOOR"),
                        rs.getString("X_SWIFI_INSTL_TY"),
                        rs.getString("X_SWIFI_INSTL_MBY"),
                        rs.getString("X_SWIFI_SVC_SE"),
                        rs.getString("X_SWIFI_CMCWR"),
                        rs.getString("X_SWIFI_CNSTC_YEAR"),
                        rs.getString("X_SWIFI_INOUT_DOOR"),
                        rs.getString("X_SWIFI_REMARS3"),
                        rs.getString("LAT"),
                        rs.getString("LNT"),
                        rs.getString("WORK_DTTM")

                );

                list.add(wifiVO);


            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeResultSet(rs);
            closeStatement(ps);
            closeConnection(coon);
        }
        return list;
    }

 // 데이터 자꾸쌓이니까 삭제해줄 쿼리
    public int removeAllData(){

        Connection con = null;
        PreparedStatement ps = null;

        String removeOneQuery = "delete from wifi ";


        try {
            con = createConnection();
            // select 뺴고
            // insert,update,delete 트랜젝션
            con.setAutoCommit(false); // 트랜젝션시작

            ps = con.prepareStatement(removeOneQuery);

            int resultCount = ps.executeUpdate();
            con.commit();
            return resultCount;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            closeStatement(ps);
            closeConnection(con);
        }
    }
}

