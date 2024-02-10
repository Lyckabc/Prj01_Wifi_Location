package apiTestScetion;

// JSON 처리를 위한 Gson 라이브러리
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// HTTP 요청을 위한 OkHttp 라이브러리
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// 기본 Java I/O 및 네트워킹
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.net.URL;
import java.io.File;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;


// 데이터 모델 클래스
import apidto.WifiDto;
import apidto.RowInfoDto;

// 예외 처리
;


/**
 1. okHttp  o
 2. 공공사이트 url+key값 가져오기
 3. request ( url) 작업
 4. reponse ( was로부터 작업결과 들고오기)

 */
public class WifiApiJsonService {
    WifiApiParser parser = new WifiApiParser();

//public WifiDto requestApiAndResponseToDto
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
    // Gson 인스턴스 생성
    private final Gson gson = new Gson();

    public WifiDto requestApiAndResponseToDto(int start, int end) throws Exception {
        // JSON 파일의 경로를 시스템에 맞게 설정해야 합니다.
        File jsonFile = new File("D:\\ZeroBase\\Task\\ApiWifiJavaServletProject-main\\WIFIiProject\\src\\main\\webapp\\db\\wifi_data.json");

        // JSON 파일을 읽고 파싱
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8)) {
            // JSON 배열 구조를 가정, RowInfoDto 리스트로 파싱
            Type listType = new TypeToken<List<RowInfoDto>>(){}.getType();
            List<RowInfoDto> wifiDetails = gson.fromJson(reader, listType);

            // WifiDto 객체 생성 및 필드 설정
            WifiDto wifiDto = new WifiDto();
            wifiDto.setWifiDetails(wifiDetails);
            wifiDto.setTotalcount(wifiDetails.size()); // 총 개수 설정

            return wifiDto;
        } catch (Exception e) {
            throw new RuntimeException("JSON 파일을 읽는 중 오류가 발생했습니다.", e);
        }
    }

    // 기존의 getTotalPageCount 및 getTotalCount 메서드는 변경 없이 유지합니다.

    // 필요한 경우 추가적인 메서드 구현...


    /*
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
    /*
     */
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
