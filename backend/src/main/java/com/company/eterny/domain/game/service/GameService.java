package com.company.eterny.domain.game.service;

import com.company.eterny.domain.game.dto.GameDto;
import com.company.eterny.domain.game.dto.PageResponse;
import com.company.eterny.domain.game.entity.Game;
import com.company.eterny.domain.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;

    private Pageable createPageable(GameDto.SearchRequest request) {
        Sort sort = createSort(request.getSortByOrDefault(), request.getSortDirectionOrDefault());
        return PageRequest.of(request.getPageOrDefault(), request.getSizeOrDefault(), sort);
    }

    private Sort createSort(String sortBy, String direction) {
        Sort.Direction sortDirection = "asc".equalsIgnoreCase(direction)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        return switch (sortBy) {
            case "gameRank" -> Sort.by(sortDirection, "gameRank");
            case "mmrGain" -> Sort.by(sortDirection, "mmrGain");
            case "kills" -> Sort.by(sortDirection, "kills");
            case "playTime" -> Sort.by(sortDirection, "playTime");
            default -> Sort.by(sortDirection, "gameStartedAt");
        };
    }


    public PageResponse<GameDto.Response> getPlayerGames(Long userNum, GameDto.SearchRequest request) {
        log.info("플레이어 게임 기록 조회: userNum={}", userNum);

        Pageable pageable = createPageable(request);
        Page<Game> gamePage = fetchGamePage(userNum, request, pageable);

        Page<GameDto.Response> dtoPage = gamePage.map(GameDto.Response::from);

        return PageResponse.of(dtoPage);
    }

    private Page<Game> fetchGamePage(Long userNum, GameDto.SearchRequest request, Pageable pageable) {
        if (request.getCharacterCode() != null) {
            return gameRepository.findByUserNumAndCharacterCodeOrderByGameStartedAtDesc(
                    userNum, request.getCharacterCode(), pageable);
        } else if (request.getTeamMode() != null) {
            return gameRepository.findByUserNumAndTeamModeOrderByGameStartedAtDesc(
                    userNum, request.getTeamMode(), pageable);
        } else if (request.getStartDate() != null && request.getEndDate() != null) {
            return gameRepository.findByUserNumAndGameStartedAtBetween(
                    userNum, request.getStartDate(), request.getEndDate(), pageable);
        } else if (request.getSeasonId() != null) {
            return gameRepository.findByUserNumAndSeasonIdOrderByGameStartedAtDesc(
                    userNum, request.getSeasonId(), pageable);
        } else {
            return gameRepository.findByUserNumOrderByGameStartedAtDesc(userNum, pageable);
        }
    }

}