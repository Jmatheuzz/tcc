package com.tcc.api.controllers;

import com.tcc.api.dto.SaveVariationDTO;
import com.tcc.api.models.Variation;
import com.tcc.api.models.Word;
import com.tcc.api.repositories.VariationRepo;
import com.tcc.api.repositories.WordRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/variations")
@Tag(name = "Variações", description = "Serviços de variações de palavras")
public class VariationController {

    @Autowired
    private VariationRepo variationRepository;

    @Autowired
    private WordRepo wordRepo;

    @Operation(description = "Serviço para criar variação")
    @PostMapping
    public Variation createVariation(@RequestBody SaveVariationDTO data) throws BadRequestException {
        Optional<Word> word = wordRepo.findById(data.getWordId());
        if (word.isPresent()){
            Variation variation = Variation.builder()
                    .name(data.getName())
                    .video(data.getVideo())
                    .description(data.getDescription())
                    .word(word.get())
                    .build();
            return variationRepository.save(variation);
        }
        throw new BadRequestException("Palavra base não existe");
    }

    @Operation(description = "Serviço para listar variações")
    @GetMapping
    public List<Variation> getAllVariations() {
        return variationRepository.findAll();
    }

    @Operation(description = "Serviço para buscar detalhes de variaçõe")
    @GetMapping("/{id}")
    public Variation getVariationById(@PathVariable Long id) {
        return variationRepository.findById(id).orElseThrow(() -> new RuntimeException("Variation not found"));
    }

    @Operation(description = "Serviço para editar variaçõe")
    @PutMapping("/{id}")
    public Variation updateVariation(@PathVariable Long id, @RequestBody SaveVariationDTO data) throws BadRequestException {
        Optional<Word> word = wordRepo.findById(data.getWordId());
        if (word.isPresent()){
            Variation variation = Variation.builder()
                    .name(data.getName())
                    .video(data.getVideo())
                    .description(data.getDescription())
                    .word(word.get())
                    .build();
            return variationRepository.save(variation);
        }
        throw new BadRequestException("Palavra base não existe");
    }

    @Operation(description = "Serviço para apagar variaçõe")
    @DeleteMapping("/{id}")
    public void deleteVariation(@PathVariable Long id) {
        Variation variation = variationRepository.findById(id).orElseThrow(() -> new RuntimeException("Variation not found"));
        variationRepository.delete(variation);
    }
}

