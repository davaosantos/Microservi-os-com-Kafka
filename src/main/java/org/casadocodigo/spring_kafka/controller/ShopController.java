package org.casadocodigo.spring_kafka.controller;


import lombok.RequiredArgsConstructor;
import org.casadocodigo.spring_kafka.client.KafkaClient;
import org.casadocodigo.spring_kafka.dto.ShopDTO;
import org.casadocodigo.spring_kafka.model.Shop;
import org.casadocodigo.spring_kafka.model.ShopItem;
import org.casadocodigo.spring_kafka.repository.ShopRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopRepository shopRepository;

    private final KafkaClient kafkaClient;

    @PostMapping
    public ShopDTO saveShop(@RequestBody ShopDTO shopDTO) {
        shopDTO.setIdentifier(UUID.randomUUID().toString());
        shopDTO.setDateShop(LocalDate.now());
        shopDTO.setStatus("PENDING");
        Shop shop = Shop.convert(shopDTO);
        for (ShopItem shopItem : shop.getItems()) {
            shopItem.setShop(shop);
        }

        shopDTO = ShopDTO.convert(shopRepository.save(shop));
        kafkaClient.sendMessage(shopDTO);
        return shopDTO;
    }

    @GetMapping
    public List<ShopDTO> getShop() {
        return shopRepository
                .findAll()
                .stream()
                .map(shop -> ShopDTO.convert(shop))
                .collect(Collectors.toList());
    }

}

