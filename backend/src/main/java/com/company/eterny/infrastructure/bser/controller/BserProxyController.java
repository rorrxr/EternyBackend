package com.company.eterny.infrastructure.bser.controller;

import com.company.eterny.infrastructure.external.bser.dto.BserGameDto;
import com.company.eterny.infrastructure.external.bser.dto.BserRankDto;
import com.company.eterny.infrastructure.external.bser.dto.NicknameDto;
import com.company.eterny.infrastructure.bser.service.BserService;
import com.company.eterny.domain.player.entity.Player;
import com.company.eterny.domain.player.service.PlayerService;
import com.company.eterny.global.dto.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @deprecated 기존 BserProxyController - 하위호환을 위해 유지
 * 새로운 컨트롤러들을 사용하세요: PlayerController, RankController, MatchController
 */
@Deprecated
@Tag(name = "BSER 전적 API (Deprecated)", description = "이터널리턴 외부 API 프록시 컨트롤러 - 하위호환용")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class BserProxyController {

    private final BserService bserService;
    private final PlayerService playerService;

    @Operation(summary = "닉네임으로 유저 검색 (레거시)", description = "외부 BSER API에서 닉네임으로 유저를 검색하고 DB에 저장합니다.")
    @GetMapping("/bser/players/search")
    public CommonResponse<List<NicknameDto>> searchPlayer(@RequestParam("nickname") String nickname) {
        // TODO: MVP에서는 임시로 빈 리스트 반환 - deprecated 컨트롤러
        List<NicknameDto> players = java.util.List.of();
        return new CommonResponse<>(200, "Success", players);
    }

    @Operation(summary = "플레이어 상세 조회 (레거시)", description = "userNum으로 DB에 저장된 플레이어 상세 정보를 조회합니다.")
    @GetMapping("/bser/players/{userNum}")
    public CommonResponse<Player> getPlayerDetail(@PathVariable Long userNum) {
        // TODO: MVP에서는 임시로 null 반환 - deprecated 컨트롤러
        Optional<Player> player = Optional.empty();
        return player.map(value -> new CommonResponse<>(200, "Success", value))
                .orElseGet(() -> new CommonResponse<>(404, "Player not found", null));
    }

    @Operation(summary = "플레이어 게임 기록 조회 (레거시)", description = "외부 BSER API에서 해당 userNum의 게임 전적을 조회합니다.")
    @GetMapping("/bser/players/{userNum}/matches")
    public CommonResponse<List<BserGameDto>> getPlayerMatches(@PathVariable Long userNum) {
        List<BserGameDto> matches = bserService.getGamesByUser(userNum);
        return new CommonResponse<>(200, "Success", matches);
    }

    @Operation(summary = "랭킹 조회 (레거시)", description = "MMR 기준 전체 또는 특정 티어의 랭킹을 조회합니다.")
    @GetMapping("/bser/ranking")
    public CommonResponse<List<Player>> getRanking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) String tier) {

        Pageable pageable = PageRequest.of(page, size);
        // TODO: MVP에서는 임시로 빈 페이지 반환 - deprecated 컨트롤러
        Page<Player> ranking = Page.empty();
        return new CommonResponse<>(200, "Success", ranking.getContent());
    }

    @Operation(summary = "BSER API 닉네임 검색 (하위호환)", description = "BSER API에서 닉네임으로 유저를 검색합니다. DB 저장은 하지 않습니다.")
    @GetMapping("/bser/user/nickname")
    public CommonResponse<List<NicknameDto>> searchUserByNickname(@RequestParam("query") String query) {
        List<NicknameDto> users = bserService.getUserByNickname(query);
        return new CommonResponse<>(200, "Success", users);
    }

    @Operation(summary = "BSER API 전적 조회 (하위호환)", description = "BSER API에서 특정 유저의 게임 전적을 조회합니다.")
    @GetMapping("/bser/games/{userNum}")
    public CommonResponse<List<BserGameDto>> getGames(@PathVariable Long userNum) {
        List<BserGameDto> games = bserService.getGamesByUser(userNum);
        return new CommonResponse<>(200, "Success", games);
    }

    @Operation(summary = "BSER API 랭크 조회", description = "BSER API에서 특정 유저의 랭크 정보를 조회합니다.")
    @GetMapping("/bser/rank/{userNum}/{seasonId}/{mode}")
    public CommonResponse<BserRankDto> getRank(
            @PathVariable Long userNum,
            @PathVariable int seasonId,
            @PathVariable int mode) {
        BserRankDto rank = bserService.getRankByUser(userNum, seasonId, mode);
        return new CommonResponse<>(200, "Success", rank);
    }
}
