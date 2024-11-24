package com.example.airport.serviceimplement;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class AirlaneSeriviceImpl {
    private AirlaneRepository airlaneRepository;
    private AirlaneMapper airlaneMapper;
    private FlightMapper flightMapper;

    @Override
    public AirlaneDto save(AirlaneDto airlane) {
        return airlaneMapper.toIdDto(airlaneRepository.save(airlaneMapper.toEntity(airlane)));
    }

    @Override
    public Optional<AirlaneDto> findById(int id) {
        return airlaneRepository.findById((long) id).map(airlaneMapper::toIdDto);
    }

    @Override
    public Optional<AirlaneDto> update(int id, AirlaneDto airlane) {
        return airlaneRepository.findById((long) id).map(oldAirline -> {
            oldAirline.setAirlaneCode(airlane.airlaneCode());
            oldAirline.setName(airlane.name());
            oldAirline.setOriginCountry(airlane.originCountry());
            oldAirline.setFlights(flightMapper.toListEntity(airlane.flights()));
            return airlaneMapper.toIdDto(airlaneRepository.save(oldAirline));
        });
    }

    @Override
    public List<AirlaneDto> findAll() {
        return airlaneMapper.toListIdDto(airlaneRepository.findAll());
    }

    @Override
    public List<AirlaneDto> findByName(String name) {
        Airlane a = new Airlane();
        a.setName(name);
        Example<Airlane> example = Example.of(a);
        return airlaneMapper.toListIdDto(airlaneRepository.findAll(example));
    }

    @Override
    public void deleteById(int id) {
        airlaneRepository.deleteById((long) id);
    }
}
