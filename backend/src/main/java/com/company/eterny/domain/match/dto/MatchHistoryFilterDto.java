package com.company.eterny.domain.match.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 매치 히스토리 필터 조건 DTO
 */
@Data
@Schema(description = "매치 히스토리 필터 조건")
public class MatchHistoryFilterDto {
    
    @Schema(description = "유저 번호", required = true, example = "123456")
    private Long userNum;
    
    @Schema(description = "시즌", example = "31")
    private Integer season;
    
    @Schema(description = "게임 모드", example = "normal")
    private String gameMode;
    
    @Schema(description = "캐릭터 ID", example = "1")
    private Integer characterId;
    
    @Schema(description = "팀 모드 (1: 솔로, 2: 듀오, 3: 스쿼드)", example = "1")
    private Integer teamMode;
    
    @Schema(description = "커서 (페이징용)", example = "")
    private String cursor;
    
    @Schema(description = "시작 날짜 (YYYY-MM-DD)", example = "2024-01-01")
    private String startDate;
    
    @Schema(description = "종료 날짜 (YYYY-MM-DD)", example = "2024-12-31")
    private String endDate;
    
    @Schema(description = "최소 게임 순위", example = "1")
    private Integer minRank;
    
    @Schema(description = "최대 게임 순위", example = "10")
    private Integer maxRank;
}
