package com.betprac.betprac.ExportAPI.controllers;

import com.betprac.betprac.ExportAPI.dtos.OddsDto;
import com.betprac.betprac.ExportAPI.services.MatchOddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odds")
@CrossOrigin
public class MatchOddsController {

    @Autowired
    MatchOddsService matchOddsService;

    @GetMapping("/{id}")
    public List<OddsDto> filterMatchById(@PathVariable Long id){
        return matchOddsService.filterMatchById(id);
    }
}
