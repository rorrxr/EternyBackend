//package com.company.eterny.global.test;
//
//import com.company.eterny.global.dto.CommonResponse;
//import com.company.eterny.infrastructure.bser.service.BserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
///**
// * BSER API 테스트용 컨트롤러
// */
//@Tag(name = "BSER API Test", description = "BSER API 직접 테스트")
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/test")
//@CrossOrigin(origins = "*")
//public class BserApiTestController {
//
//    private final BserService bserExternalService;
//    private final RestTemplate restTemplate;
//
//    @Operation(summary = "BSER API 직접 호출 테스트", description = "원시 API 응답을 확인합니다.")
//    @GetMapping("/raw")
//    public ResponseEntity<CommonResponse<String>> testRawApi(
//            @RequestParam(defaultValue = "Hide on bush") String nickname) {
//
//        log.info("BSER API 원시 테스트 - nickname: {}", nickname);
//
//        try {
//            String url = "https://open-api.bser.io/v1/user/nickname?query=" + nickname;
//
//            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
//            headers.set("Accept", "application/json");
//            headers.set("x-api-key", "oakLXTNSgq9JApTJxKIn64NeJyC6W5Y77uuYCc08");
//
//            org.springframework.http.HttpEntity<Void> entity = new org.springframework.http.HttpEntity<>(headers);
//
//            ResponseEntity<String> response = restTemplate.exchange(
//                    url, org.springframework.http.HttpMethod.GET, entity, String.class
//            );
//
//            log.info("BSER API 원시 응답: {}", response.getBody());
//
//            return ResponseEntity.ok(
//                new CommonResponse<>(200, "API 호출 성공", response.getBody())
//            );
//
//        } catch (Exception e) {
//            log.error("BSER API 원시 테스트 실패: {}", e.getMessage(), e);
//            return ResponseEntity.ok(
//                new CommonResponse<>(500, "API 호출 실패: " + e.getMessage(), null)
//            );
//        }
//    }
//
//    @Operation(summary = "서비스 레이어 테스트", description = "BserExternalService를 통한 테스트")
//    @GetMapping("/service")
//    public ResponseEntity<CommonResponse<Object>> testService(
//            @RequestParam(defaultValue = "Hide on bush") String nickname) {
//
//        log.info("서비스 레이어 테스트 - nickname: {}", nickname);
//
//        try {
//            var result = bserExternalService.searchUserByNickname(nickname);
//
//            return ResponseEntity.ok(
//                new CommonResponse<>(200, "서비스 호출 성공", result)
//            );
//
//        } catch (Exception e) {
//            log.error("서비스 레이어 테스트 실패: {}", e.getMessage(), e);
//            return ResponseEntity.ok(
//                new CommonResponse<>(500, "서비스 호출 실패: " + e.getMessage(), null)
//            );
//        }
//    }
//
//    @Operation(summary = "API 상태 확인", description = "BSER API 상태를 확인합니다.")
//    @GetMapping("/health")
//    public ResponseEntity<CommonResponse<String>> testApiHealth() {
//
//        log.info("BSER API 상태 확인");
//
//        try {
//            boolean isHealthy = bserExternalService.isApiHealthy();
//
//            return ResponseEntity.ok(
//                new CommonResponse<>(200, "상태 확인 완료",
//                    isHealthy ? "BSER API 정상" : "BSER API 비정상")
//            );
//
//        } catch (Exception e) {
//            log.error("API 상태 확인 실패: {}", e.getMessage(), e);
//            return ResponseEntity.ok(
//                new CommonResponse<>(500, "상태 확인 실패: " + e.getMessage(), null)
//            );
//        }
//    }
//}
