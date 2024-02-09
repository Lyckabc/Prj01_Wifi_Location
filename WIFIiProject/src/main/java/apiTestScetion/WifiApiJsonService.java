package apiTestScetion;

import apidto.RowInfoDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import apidto.WifiDto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.IOException;


/**
 1. okHttp  o
 2. 공공사이트 url+key값 가져오기
 3. request ( url) 작업
 4. reponse ( was로부터 작업결과 들고오기)

 */
public class WifiApiJsonService {
    WifiApiParser parser = new WifiApiParser();

//public WifiDto requestApiAndResponseToDt
//    public WifiDto requestApiAndResponseToDto(int start, int end) throws Exception {
//
//        OkHttpClient client = new OkHttpClient();
//
//        String url = "http://openapi.seoul.go.kr:8088/72704743756468323636797857527a/json/TbPublicWifiInfo/"
//                + start + "/" + end;
//
//        URL urlRequest = new URL(url); // 내용물
//        Request request = new Request.Builder() // 박스
//                .url(urlRequest)
//                .get()
//                .build(); // 포장완료
//
//        Response response = client.newCall(request).execute(); // 포장된박스를-> Api 사이트로 보냄
//
////        return response.body().string(); // Json 문자열로 가져온것
//
//        String json = response.body().string(); // 문자열로 리턴하지말고 그냥 제이슨->파싱까지 (한번에묶어서작업)
//
//        return parser.parse(json); // 이부분에서 그 작업을 return parser.parse(json)
//
//
//    }
    public WifiDto requestApiAndResponseToDto(int start, int end) throws Exception {
        List<RowInfoDto> wifiDetails = new ArrayList<>();
        int totalcount = 0;


        try (BufferedReader br = new BufferedReader(new FileReader("D:\\ZeroBase\\Task\\ApiWifiJavaServletProject-main\\WIFIiProject\\src\\main\\webapp\\db\\wifi_data.csv"))) {
            String line;
            String dbRead = br.readLine(); // 첫 번째 줄은 헤더로 건너뜁니다.
            System.out.println(dbRead);
            while ((line = br.readLine()) != null) {
                RowInfoDto rowInfo = parseCsvLineToRowInfoDto(line);
                wifiDetails.add(rowInfo);
                totalcount++;
            }
        }

        WifiDto wifiDto = new WifiDto();
        wifiDto.setWifiDetails(wifiDetails);
        wifiDto.setTotalcount(totalcount);
        // ResultInfoDto 설정은 생략하거나 기본값 사용
        // wifiDto.setResult(...);

        return wifiDto;
    }

    private RowInfoDto parseCsvLineToRowInfoDto(String csvLine) {
        String[] values = csvLine.split(",");
        RowInfoDto rowInfo = new RowInfoDto();
        // CSV 형식과 RowInfoDto 필드에 맞게 파싱 로직 구현
        // 예시: rowInfo.setSomeField(values[0]); 등으로 각 필드를 설정
        return rowInfo;
    }
    /**
     *
     getTotalPageCount() => 18페이지   (1000개씩)
     @getTotalCount() => 17830개
     */

    public int getTotalPageCount() throws Exception {     //  17094-> 18set
        WifiDto dto = requestApiAndResponseToDto(0, 1);
        int totalCount = dto.getTotalcount();  // 총갯수


        int count = (totalCount / 1000); // 몫

        if ((totalCount % 1000) > 0) {  // 나머지1개라도존재한다면
            count++;
        }

        return count;
    }


    public int getTotalCount() throws Exception {    //  total Api count 17xxx개
        WifiDto dto = requestApiAndResponseToDto(0, 1); // json tpye 파싱후-> 일부
        int totalCount = dto.getTotalcount();  // 총갯수

        return totalCount;
    }


}
