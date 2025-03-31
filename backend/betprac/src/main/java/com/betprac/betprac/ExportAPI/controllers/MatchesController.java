package com.betprac.betprac.ExportAPI.controllers;

import com.betprac.betprac.ExportAPI.dtos.MatchesDto;
import com.betprac.betprac.ExportAPI.services.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@CrossOrigin
public class MatchesController {

    @Autowired
    MatchesService matchesService;

    @GetMapping("/{date}")
    public List<MatchesDto> listMatchesByDate(@PathVariable String date) {
        return matchesService.listMatchesByDate(date);
    }
}
