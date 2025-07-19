package com.company.eterny.domain.player.controller;

import com.company.eterny.global.dto.CommonResponse;
import com.company.eterny.infrastructure.external.bser.dto.BserGameDto;
import com.company.eterny.infrastructure.external.bser.dto.NicknameDto;
import com.company.eterny.infrastructure.external.bser.service.BserExternalService;
import com.company.eterny.domain.player.entity.Player;
import com.company.eterny.domain.player.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 플레이어 관련 API 컨트롤러
 */
@Tag(name = "🎮 Player API", description = "이터널리턴 플레이어 검색 및 조회 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
@CrossOrigin(origins = "http://localhost:3000")
public class PlayerController {

    private final PlayerService playerService;
    private final BserExternalService bserExternalService;

    /**
     * 닉네임으로 플레이어 검색
     */
    @Operation(
        summary = "🔍 닉네임으로 플레이어 검색", 
        description = "BSER API에서 닉네임으로 플레이어를 검색하고 로컬 DB에 저장합니다.",
        tags = {"플레이어 검색"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "검색 성공",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CommonResponse.class),
                examples = @ExampleObject(
                    name = "검색 성공 예시",
                    value = """
                    {
                      "status": 200,
                      "message": "검색 성공",
                      "data": [
                        {
                          "userNum": 2560532,
                          "nickname": "Hide on bush",
                          "mmr": 4500,
                          "rank": 1
                        }
                      ]
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/search")
    public ResponseEntity<CommonResponse<List<NicknameDto>>> searchPlayer(
            @Parameter(
                description = "검색할 플레이어 닉네임", 
                required = true, 
                example = "Hide on bush",
                schema = @Schema(type = "string", minLength = 2, maxLength = 20)
            )
            @RequestParam("nickname") String nickname) {
        
        log.info("플레이어 검색 요청 - nickname: {}", nickname);
        
        try {
            List<NicknameDto> players = playerService.searchPlayer(nickname);
            
            if (players.isEmpty()) {
                return ResponseEntity.ok(
                    new CommonResponse<>(200, "검색 결과가 없습니다.", players)
                );
            }
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "검색 성공", players)
            );
            
        } catch (Exception e) {
            log.error("플레이어 검색 실패 - nickname: {}, error: {}", nickname, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "검색 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 유저 번호로 플레이어 상세 정보 조회
     */
    @Operation(
        summary = "👤 플레이어 상세 정보 조회", 
        description = "유저 번호로 플레이어의 상세 정보를 조회합니다.",
        tags = {"플레이어 조회"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "조회 성공",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "플레이어 정보 조회 성공",
                    value = """
                    {
                      "status": 200,
                      "message": "조회 성공",
                      "data": {
                        "userNum": 2560532,
                        "nickname": "Hide on bush",
                        "rank": 1,
                        "tier": "IMMORTAL",
                        "rp": 4500
                      }
                    }
                    """
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "플레이어를 찾을 수 없음")
    })
    @GetMapping("/{userNum}")
    public ResponseEntity<CommonResponse<Player>> getPlayerDetail(
            @Parameter(
                description = "플레이어 고유 번호", 
                required = true, 
                example = "2560532"
            )
            @PathVariable Long userNum) {
        
        log.info("플레이어 상세 정보 조회 요청 - userNum: {}", userNum);
        
        try {
            Optional<Player> player = playerService.getPlayerDetail(userNum);
            
            if (player.isPresent()) {
                return ResponseEntity.ok(
                    new CommonResponse<>(200, "조회 성공", player.get())
                );
            } else {
                return ResponseEntity.ok(
                    new CommonResponse<>(404, "플레이어를 찾을 수 없습니다.", null)
                );
            }
            
        } catch (Exception e) {
            log.error("플레이어 상세 정보 조회 실패 - userNum: {}, error: {}", userNum, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "조회 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 플레이어의 최근 매치 기록 조회
     */
    @Operation(
        summary = "⚔️ 플레이어 최근 매치 기록 조회", 
        description = "유저 번호로 플레이어의 최근 매치 기록을 조회합니다.",
        tags = {"게임 기록"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "매치 기록 조회 성공",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "매치 기록 조회 성공",
                    value = """
                    {
                      "status": 200,
                      "message": "매치 기록 조회 성공",
                      "data": [
                        {
                          "gameId": 12345,
                          "characterName": "Jackie",
                          "gameRank": 1,
                          "playerKill": 5,
                          "playerAssistant": 2,
                          "gameMode": "RANKED",
                          "startDtm": "2024-01-15T10:30:00Z"
                        }
                      ]
                    }
                    """
                )
            )
        )
    })
    @GetMapping("/{userNum}/matches")
    public ResponseEntity<CommonResponse<List<BserGameDto>>> getPlayerMatches(
            @Parameter(
                description = "플레이어 고유 번호", 
                required = true, 
                example = "2560532"
            )
            @PathVariable Long userNum) {
        
        log.info("플레이어 매치 기록 조회 요청 - userNum: {}", userNum);
        
        try {
            List<BserGameDto> matches = bserExternalService.getUserMatches(userNum);
            
            return ResponseEntity.ok(
                new CommonResponse<>(200, "매치 기록 조회 성공", matches)
            );
            
        } catch (Exception e) {
            log.error("매치 기록 조회 실패 - userNum: {}, error: {}", userNum, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "매치 기록 조회 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }

    /**
     * 플레이어 정보 새로고침
     */
    @Operation(
        summary = "🔄 플레이어 정보 새로고침", 
        description = "외부 API에서 최신 플레이어 정보를 가져와 로컬 DB를 업데이트합니다.",
        tags = {"플레이어 관리"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "정보 새로고침 성공",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "새로고침 성공",
                    value = """
                    {
                      "status": 200,
                      "message": "플레이어 정보 새로고침 성공",
                      "data": {
                        "userNum": 2560532,
                        "nickname": "Hide on bush",
                        "rank": 1,
                        "updatedAt": "2024-01-15T10:30:00Z"
                      }
                    }
                    """
                )
            )
        )
    })
    @PostMapping("/{userNum}/refresh")
    public ResponseEntity<CommonResponse<Player>> refreshPlayerInfo(
            @Parameter(
                description = "플레이어 고유 번호", 
                required = true, 
                example = "2560532"
            )
            @PathVariable Long userNum) {
        
        log.info("플레이어 정보 새로고침 요청 - userNum: {}", userNum);
        
        try {
            Optional<Player> updatedPlayer = playerService.refreshPlayerInfo(userNum);
            
            if (updatedPlayer.isPresent()) {
                return ResponseEntity.ok(
                    new CommonResponse<>(200, "플레이어 정보 새로고침 성공", updatedPlayer.get())
                );
            } else {
                return ResponseEntity.ok(
                    new CommonResponse<>(404, "플레이어 정보를 찾을 수 없습니다.", null)
                );
            }
            
        } catch (Exception e) {
            log.error("플레이어 정보 새로고침 실패 - userNum: {}, error: {}", userNum, e.getMessage());
            return ResponseEntity.ok(
                new CommonResponse<>(500, "새로고침 중 오류가 발생했습니다: " + e.getMessage(), null)
            );
        }
    }
}
